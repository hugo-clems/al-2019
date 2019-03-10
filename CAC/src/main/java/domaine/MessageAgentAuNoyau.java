package domaine;

import communication.AbstractMessage;

import java.util.HashMap;
import java.util.List;

public class MessageAgentAuNoyau extends AbstractMessage {

    private List<Connexion> couplePossible ;

    public MessageAgentAuNoyau(List<Connexion> couplePossible) {
        this.couplePossible = couplePossible;
    }

    public List<Connexion> getCouplePossible() {
        return couplePossible;
    }

    public void setCouplePossible(List<Connexion> couplePossible) {
        this.couplePossible = couplePossible;
    }
}
