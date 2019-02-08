package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;
import java.util.Map;

/**
 * Un agent situé.
 */
public abstract class AbstractAgentSitue extends AbstractAgent {

    /**
     * Le nom de l'agent.
     */
    private String nom;

    /**
     * La direction de l'agent.
     */
    private Direction direction;

    /**
     * L'identifiant de l'entité portée par l'agent.
     */
    private int idEntitePortee;

    /**
     * Le plateau.
     */
    //private IAgentPlateau plateau;

    /**
     * Constructeur par défaut.
     * @param lifeCycle
     * @param myMailBoxManager
     */
    public AbstractAgentSitue(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = "";
        this.direction = Direction.N;
        this.idEntitePortee = 0;
    }

    public AbstractAgentSitue(String nom, LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = nom;
        this.direction = Direction.N;
        this.idEntitePortee = 0;
    }

    public AbstractAgentSitue(String nom, Direction directionInitiale, LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = nom;
        this.direction = directionInitiale;
        this.idEntitePortee = 0;
    }

    /**
     * Se tourne et se déplace vers une direction.
     * @param nouvelleDirection la direction vers laquel se tourner
     */
    public void seDeplacerVers(Direction nouvelleDirection) {
        this.seTournerVers(nouvelleDirection);
        this.avancer();
    }

    /**
     * Change la direction de l'agent.
     * @param nouvelleDirection la direction vers laquel se tourner
     */
    public void seTournerVers(Direction nouvelleDirection) {
        this.direction = nouvelleDirection;
    }

    /**
     * Se déplacer dans la direction de l'agent.
     */
    public void avancer() {
        // TODO avancer
    }

    //public Map<Direction, Case> detecter() {
    public Map<Direction, String> detecter() {
        // TODO detecter
        return null;
    }

    /**
     * Dépose l'entité dans la direction choisie.
     */
    public void deposer() {
        // TODO appel du plateau
        //plateau.deposer(this.direction);
    }

    public int ramasser(String className) {
        // TODO
        return 0;
    }

    /**
     * Renvoie la direction actuel de l'agent.
     * @return la direction de l'agent
     */
    public Direction getDirection() {
        return direction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
