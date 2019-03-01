package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;
import entites.Nourriture;
import entites.Obstacle;
import entites.Pheromone;
import plateau.Case;
import plateau.IAgentite;
import plateau.Plateau;
import plateau.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Fourmi extends AbstractAgentSitue {

    private boolean transporteNourriture;
    private final Position positionNid;
    private boolean estEnPhaseAller;
    private boolean nourritureTrouvee;
    private boolean estSurNid;
    private Plateau plateauAco;

    public Position getPositionNid() {
        return positionNid;
    }

    public Fourmi(Plateau plateauAco, String nom, boolean transporteNourriture, Position positionNid, boolean estEnPhaseAller, boolean nourritureTrouvee, boolean estSurNid) {
        super(nom, plateauAco);
        this.transporteNourriture = transporteNourriture;
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

        List<Case> voisinnageNourritures = new ArrayList<>();
        List<Direction> listeDirectionsNourriture = new ArrayList<>();

        for(Map.Entry<Direction, Case> entry : voisinnage.entrySet()) {
            // Pour chaque item dans la Map, on récupère la Direction et la Case
            Case myCase = entry.getValue();
            Direction myDirection = entry.getKey();

            // On récupère les agentités de la case
            List<IAgentite> agentites = myCase.getAgentites();

            boolean caseContientNourriture = false;

            for (IAgentite agentite : agentites) {
                // Si la case contient de la nourriture
                if (agentite instanceof Nourriture) {
                    caseContientNourriture = true;
                }
            }
            if (caseContientNourriture) {
                voisinnageNourritures.add(myCase);
                listeDirectionsNourriture.add(myDirection);
            }
        }

//        Nourriture nourriture = ;
//        Direction direction = ; // direction de la nourriture

//        seTournerVers(direction);
//        ramasser(nourriture);

    }

    public void deposerNourriture(){

    }

    public void revenirAuNid(){

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

    public Plateau getPlateauAco() {
        return plateauAco;
    }

    public void setPlateauAco(Plateau plateauAco) {
        this.plateauAco = plateauAco;
    }

    public boolean isTransporteNourriture() {
        return transporteNourriture;
    }

    public void setTransporteNourriture(boolean transporteNourriture) {
        this.transporteNourriture = transporteNourriture;
    }
}
