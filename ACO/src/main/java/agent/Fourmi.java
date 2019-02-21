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

        // Tant que la fourmi ne transporte pas de nourriture && n'a pas trouvé de traces de phéromones
        while(!transporteNourriture && !suitPheromone){

            // On analyse le voisinnage
            Map<Direction, Case> voisinnage = detecter();

            // On garde en mémoire ce qu'il y a dans les cases autour
            List<Case> voisinnageObstacles = new ArrayList<>();
            List<Case> voisinnagePheromones = new ArrayList<>();
            List<Case> voisinnageNourritures = new ArrayList<>();
            // Liste des direction dont la case est sans obstacle
            List<Direction> listeDirectionSansObstacle = new ArrayList<>();

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
                    listeDirectionSansObstacle.add(myDirection);
                }

                if (caseContientPheromone) {
                    voisinnagePheromones.add(myCase);
                }

                if (caseContientNourriture) {
                    voisinnageNourritures.add(myCase);
                }
            }

            // Si dans le voisinnage de la fourmi, il n'y a ni phéromone, ni nourriture
            if(voisinnageNourritures.isEmpty() && voisinnagePheromones.isEmpty()){
                // On continue de se déplacer aléatoirement parmi les cases où il n'y a pas d'obstacles
                seDeplacerAleatoirement(listeDirectionSansObstacle);
            }
            // Sinon, si on trouve de la nourriture
            else if(!voisinnageNourritures.isEmpty()){
                // On se tourne vers une des cases qui contient de la nourriture
                // Direction directionNourriture = ...;
                // seTournerVers(directionNourriture);
                // On ramasse la nourriture
                // ramasser(Nourriture nourriture);
                transporteNourriture = true;
                // Et on retourne vers le nid
                revenirAuNid();
            }
            // Sinon, si on trouve de la phéromone
            else if(!voisinnagePheromones.isEmpty()){
                // On suit une des traces de phéromones
                suitPheromone = true;
                // Direction direction = ...;
                // seDeplacerVers(direction);
            }

        }
    }

    public void revenirAuNid(){

    }

    public void seDeplacerAleatoirement(List<Direction> listeDirectionSansObstacle){
        // On choisit une direction au hasard
        Direction direction = listeDirectionSansObstacle.get(new Random().nextInt(listeDirectionSansObstacle.size()));
        // Et on se déplace dans cette direction
        seDeplacerVers(direction);

    }

    @Override
    public void actionTour() {

    }
}
