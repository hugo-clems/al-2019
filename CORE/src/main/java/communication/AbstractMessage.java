package communication;

import MASInfrastructure.Agent.InfraAgentReference;
import MASInfrastructure.Communication.IMessage;

import java.util.ArrayList;

/**
 * C'est pas beau à cause d'INFRA !
 */
public class AbstractMessage implements IMessage {

    private InfraAgentReference emitter;

    private ArrayList<InfraAgentReference> receivers = new ArrayList<>();

    @Override
    public InfraAgentReference getEmitter() {
        return emitter;
    }

    @Override
    public void setEmitter(InfraAgentReference emitter) {
        this.emitter = emitter;
    }

    @Override
    public ArrayList<InfraAgentReference> getReceivers() {
        return receivers;
    }

    @Override
    public void setReceivers(ArrayList<InfraAgentReference> receivers) {
        this.receivers = receivers;
    }

    public void addReceiver(InfraAgentReference receiver) {
        this.receivers.add(receiver);
    }

}
