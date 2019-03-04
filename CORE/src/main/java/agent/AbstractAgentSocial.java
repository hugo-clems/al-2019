package agent;

import MASInfrastructure.Agent.InfraAgent;
import MASInfrastructure.Agent.InfraAgentReference;
import MASInfrastructure.Annuaire.Annuaire;
import MASInfrastructure.Communication.IMessage;
import communication.AbstractMessage;
import plateau.IAgentPlateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

/**
 * Un agent social.
 */
public abstract class AbstractAgentSocial extends AbstractAgent {

    /**
     * L'annuaire d'agents.
     */
    private Annuaire annuaire;

    /**
     * Liste des agents présents dans l'annuaires.
     */
    private ConcurrentMap<InfraAgentReference, InfraAgent> agentsDansAnnuaire;

    /**
     * Constructeur par défaut.
     */
    public AbstractAgentSocial(IAgentPlateau plateau) {
        super(plateau);
        this.annuaire = Annuaire.getInstance();
        this.annuaire.addAgent(this);
        this.agentsDansAnnuaire = annuaire.getAgents();
    }

    /**
     * Envoie un message.
     * @param message le message à envoyer
     * @param destinataire le destinataire du message
     */
    public void envoyerMessage(AbstractMessage message, AbstractAgent destinataire) {
        message.setEmitter(this.reference);
        message.addReceiver(destinataire.reference);
        this.annuaire.sendMessage(message);
    }

    /**
     * Diffuse un message.
     * @param message le message à diffuser
     */
    public void diffuserMessage(AbstractMessage message) {
        message.setEmitter(this.reference);
        message.getReceivers().addAll(agentsDansAnnuaire.keySet());
        this.annuaire.sendMessageBroadcast(message);
    }

    /**
     * Reçois le dernier message.
     * @return le dernier message reçu
     */
    public AbstractMessage recevoirDernierMessage() {
        Optional<IMessage> messageRecu = this.annuaire.receiveMessage(this.reference);
        return messageRecu.isPresent() ? (AbstractMessage) messageRecu.get() : null;
    }

    /**
     * Reçois les messages.
     * @return les messages reçus
     */
    public List<AbstractMessage> recevoirMessages() {
        List<AbstractMessage> mesMessages = new ArrayList<>();

        ArrayList<IMessage> messagesRecus = this.annuaire.receiveMessages(this.reference);
        for (IMessage msg : messagesRecus) {
            mesMessages.add((AbstractMessage) msg);
        }

        return mesMessages;
    }

}
