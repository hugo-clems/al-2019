package plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Case {

    /**
     * Position de la case.
     */
    private Position position;

    /**
     * Liste des IAgentite contenus dans la case.
     */
    private List<IAgentite> agentites;

    /**
     * Constructeur par défaut.
     * @param position position de la case
     */
    public Case (Position position) {
        this.position = position;
        this.agentites = new ArrayList<IAgentite>();
    }


    /**
     * Get la liste des IAgentite de la case.
     * @return la liste des agentités de la case
     */
    public List<IAgentite> getAgentites() {
        return agentites;
    }

    /**
     * Get la position de la case.
     * @return la position de la case
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Méthode equals qui compare les positions de 2 cases.
     * @param o objet à tester
     * @return true si les 2 objets sont égaux et false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Case that = (Case) o;

        return position.equals(that.position);
    }

    /**
     * Méthode hashCode de la case.
     * @return le hashcode de la case
     */
    @Override
    public int hashCode() {
        return position.hashCode();
    }
}
