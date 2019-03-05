package domaine;

import agent.AbstractAgent;
import agent.AbstractAgentSocial;
import communication.AbstractMessage;

import java.util.ArrayList;
import java.util.List;

public class Noyeau extends AbstractAgentSocial {

    private List<MessageAgentAuNoyeau> messageRecus = new ArrayList<>();

    private  List<Connexion> connexionsPossbile = new ArrayList<>();

    public List<MessageAgentAuNoyeau> recevoirMessage() {

        for (AbstractMessage msg : super.recevoirMessages()) {
            if ( msg instanceof  MessageAgentAuNoyeau){
                MessageAgentAuNoyeau message = (MessageAgentAuNoyeau) msg;
                messageRecus.add(message);
                connexionsPossbile.addAll(message.getCouplePossible());
            }
        }
        return messageRecus;

    }

    public List<MessageAgentAuNoyeau> getMessageRecus() {
        return messageRecus;
    }

    public void setMessageRecus(List<MessageAgentAuNoyeau> messageRecus) {
        this.messageRecus = messageRecus;
    }

    public List<Connexion> getConnexionsPossbile() {
        return connexionsPossbile;
    }

    public void setConnexionsPossbile(List<Connexion> connexionsPossbile) {
        this.connexionsPossbile = connexionsPossbile;
    }

    @Override
    public void actionTour() {

    }
}
