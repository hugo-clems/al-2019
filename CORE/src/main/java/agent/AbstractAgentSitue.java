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
     * @param lifeCycle le cycle de vie de l'agent
     * @param myMailBoxManager le moyen de communication de l'agent
     */
    public AbstractAgentSitue(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = "";
        this.direction = Direction.N;
        this.entitePortee = null;
    }

    /**
     * Constructeur pour ajouter un nom à l'agent.
     * @param nom le nom de l'agent
     * @param lifeCycle le cycle de vie de l'agent
     * @param myMailBoxManager le moyen de communication de l'agent
     */
    public AbstractAgentSitue(String nom, LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = nom;
        this.direction = Direction.N;
        this.entitePortee = null;
    }

    /**
     * Constructeur pour rajouter un nom et une Direction à l'agent.
     * @param nom le nom de l'agent
     * @param directionInitiale la direction initiale de l'agent
     * @param lifeCycle le cycle de vie de l'agent
     * @param myMailBoxManager le moyen de communication de l'agent
     */
    public AbstractAgentSitue(String nom, Direction directionInitiale, LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
        this.nom = nom;
        this.direction = directionInitiale;
        this.entitePortee = null;
    }

    /**
     * Se tourne et se déplace vers une direction.
     * @param nouvelleDirection la direction vers laquelle l'agent va se tourner
     */
    public void seDeplacerVers(Direction nouvelleDirection) {
        this.seTournerVers(nouvelleDirection);
        this.avancer();
    }

    /**
     * Change la direction de l'agent.
     * @param nouvelleDirection la direction vers laquelle l'agent va se tourner
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

    /**
     * Permet à l'agent de détecter son environnement.
     * @return une Map contenant les 8 Directions avec les Cases
     */
    public Map<Direction, Case> detecter() {
        return plateau.getVoisinage(this);
    }

    /**
     * Dépose l'entité sur la case où se situe l'agent.
     */
    public void deposer(AbstractEntite entite) {
        plateau.deposerEntite(this, entite);
    }

    /**
     * Ramasse l'entité qui se trouve devant l'agent.
     * @param entite l'entité à ramasser
     */
    public void ramasser(AbstractEntite entite) {
        plateau.ramasserEntite(this, entite);
    }

    /**
     * Effectue l'action durant le tour de l'agent.
     */
    public abstract void actionTour();

}
