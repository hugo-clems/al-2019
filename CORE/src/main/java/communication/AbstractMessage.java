package communication;

import MASInfrastructure.Agent.InfraAgentReference;
import MASInfrastructure.Communication.IMessage;

import java.util.ArrayList;

/**
 * C'est pas beau à cause d'INFRA !
 */
public abstract class AbstractMessage implements IMessage {
    /**
     * L'emetteur du message
     */
    private InfraAgentReference emitter;

    /**
     * Liste des recepteurs du message
     */
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
