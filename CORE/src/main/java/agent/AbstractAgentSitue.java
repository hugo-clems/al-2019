package agent;

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
     */
    public AbstractAgentSitue() {
        super();
        this.nom = "";
        this.direction = Direction.N;
        this.entitePortee = null;
    }

    /**
     * Constructeur pour ajouter un nom à l'agent.
     * @param nom le nom de l'agent
     */
    public AbstractAgentSitue(String nom) {
        super();
        this.nom = nom;
        this.direction = Direction.N;
        this.entitePortee = null;
    }

    /**
     * Constructeur pour rajouter un nom et une Direction à l'agent.
     * @param nom le nom de l'agent
     * @param directionInitiale la direction initiale de l'agent
     */
    public AbstractAgentSitue(String nom, Direction directionInitiale) {
        super();
        this.nom = nom;
        this.direction = directionInitiale;
        this.entitePortee = null;
    }

    //region Getter/Setter
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public AbstractEntite getEntitePortee() {
        return entitePortee;
    }

    public void setEntitePortee(AbstractEntite entitePortee) {
        this.entitePortee = entitePortee;
    }
    //endregion

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

    @Override
    public String toString() {
        return "AbstractAgentSitue{" +
                "nom='" + nom + '\'' +
                ", direction=" + direction +
                ", entitePortee=" + entitePortee +
                '}';
    }
}
