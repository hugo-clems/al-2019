package agent;

import common.Direction;
import entites.*;
import plateau.*;

import java.util.*;

import static common.Direction.*;

public class Fourmi extends AbstractAgentSitue {

    private final Position positionNid;
    private boolean estEnPhaseAller;
    private boolean nourritureTrouvee;
    private boolean estSurNid;
    private Boolean suitPheromoneAller;
    private IAgentPlateau iAgentPlateau;

    private Map<Direction, Integer> poids;

    public Position getPositionNid() {
        return positionNid;
    }

    public Fourmi(String nom, IAgentPlateau iAgentPlateau, Position positionNid) {
        super(nom, iAgentPlateau);
        this.iAgentPlateau = iAgentPlateau;
        this.positionNid = positionNid;
        estEnPhaseAller = true;
        nourritureTrouvee = false;
        estSurNid = true;
        suitPheromoneAller = false;
        poids = createMap();
    }

    //New map
    private static Map<Direction, Integer> createMap() {
        Map<Direction,Integer> myMap = new HashMap<Direction,Integer>();
        myMap.put(Direction.O, 0);
        myMap.put(Direction.NO, 0);
        myMap.put(Direction.N, 0);
        myMap.put(Direction.NE, 0);
        myMap.put(Direction.E, 0);
        myMap.put(Direction.SE, 0);
        myMap.put(Direction.S, 0);
        myMap.put(Direction.SO, 0);
        return myMap;
    }

    public void chercherNourriture(){

        System.out.println("[ " + this.getNom() + " ] : Je cherche");
        estSurNid = false;

        boolean caseSansObstacle = false;
        // Si la fourmi ne transporte pas de nourriture && n'a pas trouvé de traces de phéromones

        //Si la fourmis est sur le nid alors
        if (this.iAgentPlateau.getCase(this).getPosition() == positionNid){
            estSurNid = true;
            return;
        }

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

        for(Map.Entry<Direction, Case> entry : voisinnage.entrySet()) {
            // Pour chaque item dans la Map, on récupère la Direction et la Case
            Case myCase = entry.getValue();
            Direction myDirection = entry.getKey();

            // On récupère les agentités de la case
            List<IAgentite> agentites = myCase.getAgentites();
            if(agentites.size()==0){
                caseSansObstacle=true;
            }


            for (IAgentite agentite : agentites) {


                // Si la case contient au moins un obstacle
                if (agentite instanceof Obstacle) {
                    caseSansObstacle = false;
                    voisinnageObstacles.add(myCase);
                }else{
                    caseSansObstacle = true;
                }

                // Si la case contient de la phéromone
                if (agentite instanceof Pheromone) {

                    //Si la fourmi rencontre sa 1ere pheromone
                    if (!suitPheromoneAller) {
                        voisinnagePheromones.add(myCase);
                        listeDirectionsPheromone.add(myDirection);
                    } else {
                        //Si la fourmi suivait déjà de la pheromone alors elle pointe vers la direction de son dernier déplacement et ne doit pas revenir en arriere
                        if (directionOpposee(this.getDirection()) != myDirection) {
                            voisinnagePheromones.add(myCase);
                            listeDirectionsPheromone.add(myDirection);
                        }
                    }


                }
                // Si la case contient de la nourriture
                if (agentite instanceof Nourriture) {
                    voisinnageNourritures.add(myCase);
                    listeDirectionsNourriture.add(myDirection);
                }
            }

            if (caseSansObstacle){
                listeDirectionsSansObstacle.add(myDirection);
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

    public void suivrePheromone(List<Direction> listeDirectionsPheromone){
        Direction direction = listeDirectionsPheromone.get(new Random().nextInt(listeDirectionsPheromone.size()));
        if (direction != sensVersNid(this)) {
            seTournerVers(direction);
            seDeplacerVers(direction);
        } else {
            seTournerVers(directionOpposee(direction));
            seDeplacerVers(directionOpposee(direction));
        }
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

                    //se tourner vers la nourriture
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
                    estEnPhaseAller = false;
                    estSurNid = false;

                    System.out.println("[ " + this.getNom() + " ] : J'ai ramssé la nourriture");
                    break;
                }
            }

            if (aRamasseNourriture == true){
                break;
            }
        }
    }

    public void revenirAuNid(){
        //Les variables
        int rnd = 0; //Variable aleatoire pour calculer le deplacement
        int tmp = 0;
        Map<Direction, Integer> poidsTaux = new HashMap<Direction, Integer>();
        boolean pheromoneExiste = false;

        estEnPhaseAller = false;

        //Si la fourmi se contentait de suivre de la pheromone à l'aller, maintenant qu'elle retourne au nid elle ne suit plus la pheromone à la trace
        if (suitPheromoneAller){
            suitPheromoneAller = false;
        }

        //Si la fourmis est sur le nid alors
        if (this.iAgentPlateau.getCase(this).getPosition() == positionNid){
            estSurNid = true;
            estEnPhaseAller = true;
        } else if (!estSurNid && !estEnPhaseAller && !suitPheromoneAller) {
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
                        poids.replace(myDirection, 0);
                    } else if (agentite instanceof Pheromone) { // Si la case contient de la phéromone
                        //Remplacer le poids par poids * taux de pheromone
                        poids.replace(myDirection, ((Pheromone) agentite).getTauxPheromone() * poids.get(myDirection) / 10);
                    } else if (agentite instanceof Nourriture) { // Si la case contient de la nourriture
                        poids.replace(myDirection, 0);
                    }
                }
            }

            //Les fourmis priorise de se deplacer dans le sens vers le Nid
            poids.replace(sensVersNid(this), poids.get(sensVersNid(this)) * 40 );

            rnd = new Random().nextInt(calculerSomme());
            for(Map.Entry<Direction, Integer> entry : poids.entrySet()) {
                tmp = tmp + entry.getValue();
                if (rnd <= tmp){
                    //Verifier si il existe deja le pheromone dans la case
                    List<IAgentite> agentites = this.iAgentPlateau.getCase(this).getAgentites();
                    for (IAgentite agentite : agentites) {
                        if (agentite instanceof Pheromone) {
                            pheromoneExiste = true;
                            ((Pheromone) agentite).setTauxPheromone(((Pheromone) agentite).getTauxPheromone() + 20);
                        }
                    }
                    //On depose que du pheromone dans le cas il n'existe pas
                    if (!pheromoneExiste) {
                        AbstractEntite ent = new Pheromone(20, this.toString(), (IEntitePlateau) this.plateau);
                        this.setEntitePortee(ent);
                        deposer(ent);
                    }
                    seDeplacerVers(entry.getKey());
                    break;
                }
            }
        }

        //Si la fourmis est sur le nid alors
        if (this.iAgentPlateau.getCase(this).getPosition().equals(positionNid)){
            System.out.println("[ " + this.getNom() + " ] : Je suis sur le nid");
            estSurNid = true;
        }

    }

    /**
     * Detecter le sens pour rentrer au nid
     */
    public Direction sensVersNid(Fourmi f){
        int posX, posY, posNidX, posNidY;

        posX = f.iAgentPlateau.getCase(f).getPosition().getX();
        posY = f.iAgentPlateau.getCase(f).getPosition().getY();
        posNidX = f.getPositionNid().getX();
        posNidY = f.positionNid.getY();

        if (posX < posNidX) {
            if (posY < posNidY) {
                return Direction.SE;
            } else if (posY == posNidY) {
                return Direction.E;
            } else return Direction.NE;
        } else if (posX == posNidX) {
            if (posY < posNidY) {
                return Direction.S;
            } else return Direction.N;
        } else {
            if (posY < posNidY){
                return Direction.SO;
            } else if (posY == posNidY){
                return Direction.O;
            } else return Direction.NO;
        }
    }

    /**
     * Calculer la somme total des poids
     */
    public int calculerSomme(){
        int somme = 0;

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
                poids.replace(Direction.O, 20);
                poids.replace(Direction.NO, 20);
                poids.replace(Direction.N, 20);
                poids.replace(Direction.NE, 10);
                poids.replace(Direction.E, 5);
                poids.replace(Direction.SE, 5);
                poids.replace(Direction.S, 5);
                poids.replace(Direction.SO, 10);
                break;
            case N:
                poids.replace(Direction.O, 10);
                poids.replace(Direction.NO, 20);
                poids.replace(Direction.N, 20);
                poids.replace(Direction.NE, 20);
                poids.replace(Direction.E, 10);
                poids.replace(Direction.SE, 5);
                poids.replace(Direction.S, 5);
                poids.replace(Direction.SO, 5);
                break;
            case NE:
                poids.replace(Direction.O, 5);
                poids.replace(Direction.NO, 10);
                poids.replace(Direction.N, 20);
                poids.replace(Direction.NE, 20);
                poids.replace(Direction.E, 20);
                poids.replace(Direction.SE, 10);
                poids.replace(Direction.S, 5);
                poids.replace(Direction.SO, 5);
                break;
            case E:
                poids.replace(Direction.O, 5);
                poids.replace(Direction.NO, 5);
                poids.replace(Direction.N, 10);
                poids.replace(Direction.NE, 20);
                poids.replace(Direction.E, 20);
                poids.replace(Direction.SE, 20);
                poids.replace(Direction.S, 10);
                poids.replace(Direction.SO, 5);
                break;
            case SE:
                poids.replace(Direction.O, 5);
                poids.replace(Direction.NO, 5);
                poids.replace(Direction.N, 5);
                poids.replace(Direction.NE, 10);
                poids.replace(Direction.E, 20);
                poids.replace(Direction.SE, 20);
                poids.replace(Direction.S, 20);
                poids.replace(Direction.SO, 10);
                break;
            case S:
                poids.replace(Direction.O, 10);
                poids.replace(Direction.NO, 5);
                poids.replace(Direction.N, 5);
                poids.replace(Direction.NE, 5);
                poids.replace(Direction.E, 10);
                poids.replace(Direction.SE, 20);
                poids.replace(Direction.S, 20);
                poids.replace(Direction.SO, 20);
                break;
            case SO:
                poids.replace(Direction.O, 20);
                poids.replace(Direction.NO, 10);
                poids.replace(Direction.N, 5);
                poids.replace(Direction.NE, 5);
                poids.replace(Direction.E, 5);
                poids.replace(Direction.SE, 10);
                poids.replace(Direction.S, 20);
                poids.replace(Direction.SO, 20);
                break;
            case O:
                poids.replace(Direction.O, 20);
                poids.replace(Direction.NO, 20);
                poids.replace(Direction.N, 10);
                poids.replace(Direction.NE, 5);
                poids.replace(Direction.E, 5);
                poids.replace(Direction.SE, 5);
                poids.replace(Direction.S, 10);
                poids.replace(Direction.SO, 20);
                break;
        }
    }

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

    public void seDeplacerAleatoirement(List<Direction> listeDirectionsSansObstacle){
        // On choisit une direction au hasard
        Direction direction = listeDirectionsSansObstacle.get(new Random().nextInt(listeDirectionsSansObstacle.size()));
        // Et on se déplace dans cette direction
        seTournerVers(direction);
        seDeplacerVers(direction);
    }

    public void deposerNourriture(){
        estEnPhaseAller = true;
        nourritureTrouvee = false;
        System.out.println("[ " + this.getNom() + " ] : J'ai déposé la nourriture.");
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
