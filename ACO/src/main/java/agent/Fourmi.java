package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;
import entites.Nourriture;
import entites.Obstacle;
import entites.Pheromone;
import javafx.print.PageLayout;
import plateau.Case;
import plateau.IAgentite;
import plateau.Plateau;
import plateau.Plateau;
import plateau.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Fourmi extends AbstractAgentSitue {

    private final Position positionNid;
    private boolean estEnPhaseAller;
    private boolean nourritureTrouvee;
    private boolean estSurNid;
    private Plateau plateauAco;

    public Position getPositionNid() {
        return positionNid;
    }

    public Fourmi(Plateau plateauAco, String nom, Position positionNid, boolean estEnPhaseAller, boolean nourritureTrouvee, boolean estSurNid) {
        super(nom, plateauAco);
        this.positionNid = positionNid;
        this.estEnPhaseAller = estEnPhaseAller;
        this.nourritureTrouvee = nourritureTrouvee;
        this.estSurNid = estSurNid;
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

            boolean caseContientObstacle = false;
            boolean caseContientPheromone = false;
            boolean caseContientNourriture = false;

            for (IAgentite agentite:agentites) {
                // Si la case contient au moins un obstacle
                if(agentite instanceof Obstacle){
                    caseContientObstacle = true;
                }
                // Si la case contient de la phéromone
                if(agentite instanceof Pheromone){
                    caseContientPheromone = true;
                }
                // Si la case contient de la nourriture
                if(agentite instanceof Nourriture){
                    caseContientNourriture = true;
                }
            }

            if (caseContientObstacle){
                voisinnageObstacles.add(myCase);
            }
            else {
                listeDirectionsSansObstacle.add(myDirection);
            }

            if (caseContientPheromone) {
                voisinnagePheromones.add(myCase);
                listeDirectionsPheromone.add(myDirection);
            }

            if (caseContientNourriture) {
                voisinnageNourritures.add(myCase);
                listeDirectionsNourriture.add(myDirection);
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

    /*
        TODO FAIRE EN SORTE QUE QUAND ON SUIT LA PHEROMONE ON AILLE DANS LE SENS INVERSE DU NID
     */
    public void suivrePheromone(List<Direction> listeDirectionsPheromone){
        Direction direction = listeDirectionsPheromone.get(new Random().nextInt(listeDirectionsPheromone.size()));
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

                    //Si il n'y a plus de nourriture on supprime l'entite
                    if (quantiteNourriture == 0){
                        plateauAco.enleverAgentite(myCase.getPosition(),agentite);
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
    }

    public void revenirAuNid(){
        estEnPhaseAller = false;
    }

    public void seDeplacerAleatoirement(List<Direction> listeDirectionsSansObstacle){
        // On choisit une direction au hasard
        Direction direction = listeDirectionsSansObstacle.get(new Random().nextInt(listeDirectionsSansObstacle.size()));
        // Et on se déplace dans cette direction
        seDeplacerVers(direction);
//        chercher  Nourriture();

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
