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
     * @param position
     */
    public Case (Position position) {
        this.position = position;
        this.agentites = new ArrayList<IAgentite>();
    }


    /**
     * Get la liste des IAgentite de la case.
     * @return
     */
    public List<IAgentite> getAgentites() {
        return agentites;
    }

    /**
     * Get la position de la case.
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Méthode equals qui compare les positions de 2 cases.
     * @param o
     * @return
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
     * @return
     */
    @Override
    public int hashCode() {
        return position.hashCode();
    }
}
