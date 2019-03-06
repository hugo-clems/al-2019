package domaine;

import agent.AbstractAgentSocial;
import communication.AbstractMessage;

import java.util.ArrayList;
import java.util.List;

public class Noyau extends AbstractAgentSocial {

    private List<MessageAgentAuNoyau> messageRecus = new ArrayList<>();

    private  List<Connexion> connexionsPossibles = new ArrayList<>();

    public List<MessageAgentAuNoyau> recevoirMessage() {
        for (AbstractMessage msg : super.recevoirMessages()) {
            if ( msg instanceof MessageAgentAuNoyau){
                MessageAgentAuNoyau message = (MessageAgentAuNoyau) msg;
                messageRecus.add(message);
                connexionsPossibles.addAll(message.getCouplePossible());
            }
        }
        return messageRecus;
    }

    public List<MessageAgentAuNoyau> getMessageRecus() {
        return messageRecus;
    }

    public void setMessageRecus(List<MessageAgentAuNoyau> messageRecus) {
        this.messageRecus = messageRecus;
    }

    public List<Connexion> getconnexionsPossibles() {
        return connexionsPossibles;
    }

    public void setconnexionsPossibles(List<Connexion> connexionsPossibles) {
        this.connexionsPossibles = connexionsPossibles;
    }

    @Override
    public void actionTour() {

    }
}
