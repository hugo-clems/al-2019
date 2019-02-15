package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Communication.IMessage;
import MASInfrastructure.Etat.LifeCycle;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Un agent social.
 */
public abstract class AbstractAgentSocial extends AbstractAgent {

    /**
     * Constructeur par défaut.
     */
    public AbstractAgentSocial() {
        super();
    }

    /**
     * Envoie un message.
     * @param message le message à envoyer
     */
    public void envoyerMessage(IMessage message) {
        this.infra.sendMessage(message);
    }

    /**
     * Diffuse un message.
     * @param message le message à diffuser
     */
    public void diffuserMessage(IMessage message) {
        this.infra.sendMessageBroadcast(message);
    }

    /**
     * Reçois le dernier message.
     * @return le dernier message reçu
     */
    public Optional<IMessage> recevoirDernierMessage() {
        return this.infra.receiveMessage(this.reference);
    }

    /**
     * Reçois les messages.
     * @return les messages reçus
     */
    public ArrayList<IMessage> recevoirMessages() {
        return this.infra.receiveMessages(this.reference);
    }

}
