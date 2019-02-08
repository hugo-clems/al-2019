package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;
import entites.AbstractEntite;
import plateau.Case;

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
     * L'entité portée par l'agent.
     */
    private AbstractEntite entitePortee;

    /**
     * Constructeur par défaut.
     * @param lifeCycle
     * @param myMailBoxManager
     */
    public AbstractAgentSitue(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = "";
        this.direction = Direction.N;
        this.entitePortee = null;
    }

    public AbstractAgentSitue(String nom, LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = nom;
        this.direction = Direction.N;
        this.entitePortee = null;
    }

    public AbstractAgentSitue(String nom, Direction directionInitiale, LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = nom;
        this.direction = directionInitiale;
        this.entitePortee = null;
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
        plateau.deplacerAgent(this, this.direction);
    }

    public Map<Direction, Case> detecter() {
        // TODO detecter
        return null;
    }

    /**
     * Dépose l'entité dans la direction choisie.
     */
    public void deposer() {
        // TODO deposer
    }

    public void ramasser(AbstractEntite entite) {
        plateau.ramasserEntite(this, entite);
    }

    public abstract void actionTour();

}
