package plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Case {

    private Position position;
    private List<IAgentite> agentites;

    public Case (Position position) {
        this.position = position;
        this.agentites = new ArrayList<IAgentite>();
    }


    public List<IAgentite> getAgentites() {
        return agentites;
    }

    public Position getPosition() {
        return position;
    }

    public void setAgentites(List<IAgentite> agentites) {
        this.agentites = agentites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Case that = (Case) o;

        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }
}
