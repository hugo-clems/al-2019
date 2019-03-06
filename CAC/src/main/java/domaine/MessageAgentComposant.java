package domaine;


import communication.AbstractMessage;

import java.util.Set;

public class MessageAgentComposant extends AbstractMessage {

    /**
     * Les agents se communiquent les ports dont ils ont besoin
     */
     private Set<Port> portRequis;

    public MessageAgentComposant(Set<Port> portRequis) {
        this.portRequis = portRequis;
    }

    public Set<Port> getPortRequis() {
        return portRequis;
    }

    public void setPortRequis(Set<Port> portRequis) {
        this.portRequis = portRequis;
    }
}
