package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;
import entites.AbstractEntite;
import entites.Nourriture;
import entites.Obstacle;
import entites.Pheromone;
import javafx.print.PageLayout;
import plateau.*;
import plateau.Plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static common.Direction.*;

public class Fourmi extends AbstractAgentSitue {

    private final Position positionNid;
    private boolean estEnPhaseAller = true;
    private boolean nourritureTrouvee = false;
    private boolean estSurNid = true;
    private Boolean suitPheromoneAller = false;
    private IAgentPlateau iAgentPlateau;


    public Fourmi(String nom, IAgentPlateau iAgentPlateau, Position positionNid) {
        super(nom, iAgentPlateau);
        this.iAgentPlateau = iAgentPlateau;
        this.positionNid = positionNid;
    }



    public void chercherNourriture(){

        // Si la fourmi ne transporte pas de nourriture && n'a pas trouvé de traces de phéromones

        // On analyse le voisinnage
        Map<Direction, Case> voisinnage = detecter();

        // On garde en mémoire ce qu'il y a dans les cases autour
        List<Case> voisinnageObstacles = new ArrayList<>();
        List<Case> voisinnagePheromones = new ArrayList<>();
        List<Case> voisinnageNourritures = new ArrayList<>();
        // Liste des directions
        List<Direction> listeDirectionsSansObstacle = new ArrayList<>();
        List<Direction> listeDirectionsNourriture = new ArrayList<>();
        List<Direction> listeDirectionsPheromone = new ArrayList<>();

        for(Map.Entry<Direction, Case> entry : voisinnage.entrySet()){
            // Pour chaque item dans la Map, on récupère la Direction et la Case
            Case myCase = entry.getValue();
            Direction myDirection = entry.getKey();

            // On récupère les agentités de la case
            List<IAgentite> agentites = myCase.getAgentites();


            for (IAgentite agentite:agentites) {
                // Si la case contient au moins un obstacle
                if(agentite instanceof Obstacle){
                    voisinnageObstacles.add(myCase);
                }else {
                    listeDirectionsSansObstacle.add(myDirection);
                }
                
                // Si la case contient de la phéromone
                if(agentite instanceof Pheromone){

                    //Si la fourmi rencontre sa 1ere pheromone
                    if (!suitPheromoneAller){
                        voisinnagePheromones.add(myCase);
                        listeDirectionsPheromone.add(myDirection);
                    }else{
                        //Si la fourmi suivait déjà de la pheromone alors elle pointe vers la direction de son dernier déplacement et ne doit pas revenir en arriere
                        if (directionOpposee(this.getDirection()) != myDirection){
                            voisinnagePheromones.add(myCase);
                            listeDirectionsPheromone.add(myDirection);
                        }
                    }


                }
                // Si la case contient de la nourriture
                if(agentite instanceof Nourriture){
                    voisinnageNourritures.add(myCase);
                    listeDirectionsNourriture.add(myDirection);
                }
            }

            //si nourriture a proximité
            if(!voisinnageNourritures.isEmpty()){
                nourritureTrouvee = true;
            }else if(!voisinnagePheromones.isEmpty()){
                suivrePheromone(listeDirectionsPheromone);
            }else{
                seDeplacerAleatoirement(listeDirectionsSansObstacle);
            }

        }
    }


    public void suivrePheromone(List<Direction> listeDirectionsPheromone){
        Direction direction = listeDirectionsPheromone.get(new Random().nextInt(listeDirectionsPheromone.size()));
        seTournerVers(direction);
        seDeplacerVers(direction);
    }

    public void prendreNourriture(){
        // On analyse le voisinnage
        Map<Direction, Case> voisinnage = detecter();


        for(Map.Entry<Direction, Case> entry : voisinnage.entrySet()) {
            // Pour chaque item dans la Map, on récupère la Direction et la Case
            Case myCase = entry.getValue();
            Direction myDirection = entry.getKey();

            // On récupère les agentités de la case
            List<IAgentite> agentites = myCase.getAgentites();

            boolean aRamasseNourriture = false;

            for (IAgentite agentite : agentites) {
                // Si la case contient de la nourriture
                if (agentite instanceof Nourriture) {

                    //se tourner versla nourriture
                    seTournerVers(myDirection);

                    //enlever 1pv à la nourriture
                    int quantiteNourriture = ((Nourriture) agentite).getQuantite();
                    quantiteNourriture--;
                    ((Nourriture) agentite).setQuantite(quantiteNourriture);

                    //Si il n'y a plus de nourriture on ramasse l'entite
                    if (quantiteNourriture == 0){
                        this.ramasser((AbstractEntite) agentite);
                    }

                    aRamasseNourriture = true;
                    break;
                }
            }

            if (aRamasseNourriture == true){
                break;
            }
        }
    }


    public void deposerNourriture(){
        estEnPhaseAller = true;
        nourritureTrouvee = false;
    }

    public void revenirAuNid(){
        estEnPhaseAller = false;


        //Si la fourmi se contentait de suivre de la pheromone à l'aller, maintenant qu'elle retourne au nid elle ne suit plus la pheromone à la trace
        if (suitPheromoneAller){
            suitPheromoneAller = false;
        }

        //Si la fourmis est sur le nid alors
        if (this.iAgentPlateau.getCase(this).getPosition() == positionNid){
            estSurNid = true;
        }

    }

    public void seDeplacerAleatoirement(List<Direction> listeDirectionsSansObstacle){
        // On choisit une direction au hasard
        Direction direction = listeDirectionsSansObstacle.get(new Random().nextInt(listeDirectionsSansObstacle.size()));
        // Et on se déplace dans cette direction
        seTournerVers(direction);
        seDeplacerVers(direction);
    }


    /*
    public void evaporationPheromone(){

        Map<IAgentite, Case> agentities = plateauAco.getListeAgentites();

        for(Map.Entry<IAgentite, Case> entry : agentities.entrySet()) {
            // Pour chaque item dans la Map, on récupère l'entite
            IAgentite agentite = entry.getKey();

            if (agentite instanceof Pheromone) {
                int tauxPheromone = ((Pheromone) agentite).getTauxPheromone();
                tauxPheromone--;
                ((Pheromone) agentite).setTauxPheromone(tauxPheromone);
            }
        }

    }*/


    public Direction directionOpposee(Direction d){

        Direction directionOpposee = null;

        switch (d) {
            case NO:
                directionOpposee = SE;
                break;
            case N:
                directionOpposee = S;
                break;
            case NE:
                directionOpposee = SO;
                break;
            case E:
                directionOpposee = O;
                break;
            case SE:
                directionOpposee = NO;
                break;
            case S:
                directionOpposee = N;
                break;
            case SO:
                directionOpposee = NE;
                break;
            case O:
                directionOpposee = E;
                break;
        }

        return directionOpposee;
    }


    @Override
    public void actionTour() {

        if(estEnPhaseAller){
            if(nourritureTrouvee){
                prendreNourriture();
            }else{
                chercherNourriture();
            }
        }else{
            if(estSurNid){
                deposerNourriture();
            }else{
                revenirAuNid();
            }
        }

    }
}
