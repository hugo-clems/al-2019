package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;
import entites.Nourriture;
import entites.Obstacle;
import entites.Pheromone;
import plateau.Case;
import plateau.IAgentite;
import plateau.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Fourmi extends AbstractAgentSitue {

    private boolean transporteNourriture;
    private boolean suitPheromone;
    private final Position positionNid;



    public Position getPositionNid() {
        return positionNid;
    }

    public Fourmi(String nom, Direction directionInitiale, boolean transporteNourriture, boolean suitPheromone, Position position_nid) {
        super(nom, directionInitiale);
        this.transporteNourriture = transporteNourriture;
        this.suitPheromone = suitPheromone;
        this.positionNid=position_nid;
    }

    public void chercherNourriture(){

        // Si la fourmi ne transporte pas de nourriture && n'a pas trouvé de traces de phéromones
        if(!this.transporteNourriture){

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
            }

            if (!suitPheromone) {
                // Si dans le voisinnage de la fourmi, il n'y a ni phéromone, ni nourriture
                if (voisinnageNourritures.isEmpty() && voisinnagePheromones.isEmpty()) {
                    // On continue de se déplacer aléatoirement parmi les cases où il n'y a pas d'obstacles
                    seDeplacerAleatoirement(listeDirectionsSansObstacle);
                }
                else {
                    prendreNourritureOuSeDirigerVersPheromone();
                }
            }
            else {
                prendreNourritureOuSeDirigerVersPheromone();
            }

        }
    }

    public void revenirAuNid(){

    }

    public void seDeplacerAleatoirement(List<Direction> listeDirectionsSansObstacle){
        // On choisit une direction au hasard
        Direction direction = listeDirectionsSansObstacle.get(new Random().nextInt(listeDirectionsSansObstacle.size()));
        // Et on se déplace dans cette direction
        seDeplacerVers(direction);
//        chercherNourriture();

    }

    public void prendreNourritureOuSeDirigerVersPheromone(List<Case> voisinnageNourritures, List<Case> voisinnagePheromones, List<Direction> listeDirectionsNourriture, List<Direction> listeDirectionsPheromone){
        if (!voisinnageNourritures.isEmpty()) {
            // On se tourne vers une des cases qui contient de la nourriture
            Direction directionNourriture = listeDirectionsNourriture.get(new Random().nextInt(listeDirectionsNourriture.size()));
            seTournerVers(directionNourriture);
            // On ramasse la nourriture
            // Nourriture nourriture = ...; // TO DO
            //ramasser(nourriture);
            transporteNourriture = true;
            // Et on retourne vers le nid
            revenirAuNid();
        }
        // Sinon, si on trouve de la phéromone
        else if (!voisinnagePheromones.isEmpty()) {
            // On suit une des traces de phéromones
            Direction directionPheromone = listeDirectionsPheromone.get(new Random().nextInt(listeDirectionsPheromone.size()));
            seDeplacerVers(directionPheromone);
            suitPheromone = true;
        }
    }

    @Override
    public void actionTour() {

    }
}
