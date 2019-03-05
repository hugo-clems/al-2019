package domaine;

import communication.AbstractMessage;

import java.util.HashMap;
import java.util.List;

public class MessageAgentAuNoyeau extends AbstractMessage {

    private List<Connexion> couplePossible ;

    public MessageAgentAuNoyeau(List<Connexion> couplePossible) {
        this.couplePossible = couplePossible;
    }

    public List<Connexion> getCouplePossible() {
        return couplePossible;
    }

    public void setCouplePossible(List<Connexion> couplePossible) {
        this.couplePossible = couplePossible;
    }
}
