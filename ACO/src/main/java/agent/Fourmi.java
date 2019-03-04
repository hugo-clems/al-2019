package agent;

import common.Direction;
import entites.Nourriture;
import entites.Obstacle;
import entites.Pheromone;
import plateau.Case;
import plateau.IAgentite;
import plateau.Plateau;
import plateau.Position;

import java.util.*;

public class Fourmi extends AbstractAgentSitue {

    private boolean transporteNourriture;
    private boolean suitPheromone;
    private final Position positionNid;

    private Map<Direction, Integer> poids;

    public Position getPositionNid() {
        return positionNid;
    }

    public Fourmi(String nom, Direction directionInitiale, boolean transporteNourriture, boolean suitPheromone, Position position_nid) {
        super(nom, directionInitiale);
        this.transporteNourriture = transporteNourriture;
        this.suitPheromone = suitPheromone;
        this.positionNid=position_nid;

        poids = createMap();

    }

    //New map
    private static Map<Direction, Integer> createMap() {
        Map<Direction,Integer> myMap = new HashMap<Direction,Integer>();
        myMap.put(Direction.O, 0);
        myMap.put(Direction.NO, 0);
        myMap.put(Direction.NO, 0);
        myMap.put(Direction.NE, 0);
        myMap.put(Direction.E, 0);
        myMap.put(Direction.SE, 0);
        myMap.put(Direction.S, 0);
        myMap.put(Direction.SO, 0);
        return myMap;
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

    public void seDeplacerAleatoirement(List<Direction> listeDirectionSansObstacle){
        // On choisit une direction au hasard
        Direction direction = listeDirectionSansObstacle.get(new Random().nextInt(listeDirectionSansObstacle.size()));
        // Et on se déplace dans cette direction
        seDeplacerVers(direction);

    }

    public void revenirAuNid() {
        //Les variables
        int rnd = 0; //Variable aleatoire pour calculer le deplacement
        int tmp = 0;
        Map<Direction, Integer> poidsTaux = new HashMap<Direction, Integer>();
        int posX = 0;
        int posY = 0;

        posX = this.plateau.getCase(this).getPosition().getX();
        posY = this.plateau.getCase(this).getPosition().getY();
        if (posX != getPositionNid().getX() && posY != getPositionNid().getY()) {
            initPoinds(this.getDirection());

            //On analyse le voisinnage
            Map<Direction, Case> voisinnage = detecter();

            for(Map.Entry<Direction, Case> entry : voisinnage.entrySet()) {
                // Pour chaque item dans la Map, on récupère la Direction et la Case
                Case myCase = entry.getValue();
                Direction myDirection = entry.getKey();

                // On récupère les agentités de la case
                List<IAgentite> agentites = myCase.getAgentites();

                for (IAgentite agentite : agentites) {
                    // Si la case contient au moins un obstacle
                    if (agentite instanceof Obstacle) {
                        poids.put(myDirection, 0);
                    } else if (agentite instanceof Pheromone) { // Si la case contient de la phéromone
                        //Remplacer le poids par poids * taux de pheromone
                        poids.put(myDirection, ((Pheromone) agentite).getTauxPheromone() * poids.get(myDirection));
                    } else if (agentite instanceof Nourriture) { // Si la case contient de la nourriture
                        poids.put(myDirection, 0);
                    }
                }
            }

            rnd = new Random().nextInt(calculerSomme());
            for(Map.Entry<Direction, Integer> entry : poids.entrySet()) {
                tmp = tmp + entry.getValue();
                if (rnd < tmp){
                    deposer(new Pheromone(5, (Plateau) this.plateau, this.toString()));
                    seDeplacerVers(entry.getKey());
                    break;
                }
            }
        }
    }

    /**
     * Calculer la somme total des poids
     */
    public int calculerSomme(){
        int somme = 0;
        int taux = 0;
        // On analyse le voisinnage
        Map<Direction, Case> voisinnage = detecter();
        for(Map.Entry<Direction, Case> entry : voisinnage.entrySet()) {
            Direction myDirection = entry.getKey();
            somme = somme + poids.get(myDirection);
        }
        return somme;
    }

    /**
     * Les poids par defaut selon la direction d'une fourmi
     * @param d la direction courante
     */
    public void initPoinds(Direction d){
            switch (d) {
                case NO:
                    poids.put(Direction.O, 20);
                    poids.put(Direction.NO, 20);
                    poids.put(Direction.N, 20);
                    poids.put(Direction.NE, 10);
                    poids.put(Direction.E, 5);
                    poids.put(Direction.SE, 5);
                    poids.put(Direction.S, 5);
                    poids.put(Direction.SO, 10);
                    break;
                case N:
                    poids.put(Direction.O, 10);
                    poids.put(Direction.NO, 20);
                    poids.put(Direction.N, 20);
                    poids.put(Direction.NE, 20);
                    poids.put(Direction.E, 10);
                    poids.put(Direction.SE, 5);
                    poids.put(Direction.S, 5);
                    poids.put(Direction.SO, 5);
                    break;
                case NE:
                    poids.put(Direction.O, 5);
                    poids.put(Direction.NO, 10);
                    poids.put(Direction.N, 20);
                    poids.put(Direction.NE, 20);
                    poids.put(Direction.E, 20);
                    poids.put(Direction.SE, 10);
                    poids.put(Direction.S, 5);
                    poids.put(Direction.SO, 5);
                    break;
                case E:
                    poids.put(Direction.O, 5);
                    poids.put(Direction.NO, 5);
                    poids.put(Direction.N, 10);
                    poids.put(Direction.NE, 20);
                    poids.put(Direction.E, 20);
                    poids.put(Direction.SE, 20);
                    poids.put(Direction.S, 10);
                    poids.put(Direction.SO, 5);
                    break;
                case SE:
                    poids.put(Direction.O, 5);
                    poids.put(Direction.NO, 5);
                    poids.put(Direction.N, 5);
                    poids.put(Direction.NE, 10);
                    poids.put(Direction.E, 20);
                    poids.put(Direction.SE, 20);
                    poids.put(Direction.S, 20);
                    poids.put(Direction.SO, 10);
                    break;
                case S:
                    poids.put(Direction.O, 10);
                    poids.put(Direction.NO, 5);
                    poids.put(Direction.N, 5);
                    poids.put(Direction.NE, 5);
                    poids.put(Direction.E, 10);
                    poids.put(Direction.SE, 20);
                    poids.put(Direction.S, 20);
                    poids.put(Direction.SO, 20);
                    break;
                case SO:
                    poids.put(Direction.O, 20);
                    poids.put(Direction.NO, 10);
                    poids.put(Direction.N, 5);
                    poids.put(Direction.NE, 5);
                    poids.put(Direction.E, 5);
                    poids.put(Direction.SE, 10);
                    poids.put(Direction.S, 20);
                    poids.put(Direction.SO, 20);
                    break;
                case O:
                    poids.put(Direction.O, 20);
                    poids.put(Direction.NO, 20);
                    poids.put(Direction.N, 10);
                    poids.put(Direction.NE, 5);
                    poids.put(Direction.E, 5);
                    poids.put(Direction.SE, 5);
                    poids.put(Direction.S, 10);
                    poids.put(Direction.SO, 20);
                    break;
            }
        }

    @Override
    public void actionTour() {

    }
}
