package common;

import MASInfrastructure.Agent.InfraAgent;
import MASInfrastructure.Agent.InfraAgentReference;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Communication.IMessage;
import MASInfrastructure.Etat.LifeCycle;
import MASInfrastructure.Infrastructure;
import MASInfrastructure.Ordonnanceur.IStratOrdonnanceur;

import java.util.ArrayList;
import java.util.Optional;

public abstract class InfraImpl extends Infrastructure {

    /**
     * Constructeur par défaut.
     */
    public InfraImpl() {
        super();
    }

    /**
     * Créer un nouvel agent.
     * @param lifeCycle
     * @param myMailBoxManager
     * @return
     */
    public InfraAgent creer(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        return this.creer(null, lifeCycle, myMailBoxManager);
    }

    /**
     * Envoie un message.
     * @param message le message à envoyer
     */
    public void envoyerMessage(IMessage message) {
        this.sendMessage(message);
    }

    /**
     * Diffuse un message.
     * @param message le message à diffuser
     */
    public void diffuserMessage(IMessage message) {
        this.sendMessageBroadcast(message);
    }

    /**
     * Reçois un message.
     * @param reference
     * @return
     */
    public Optional<IMessage> recevoirMessage(InfraAgentReference reference) {
        return this.receiveMessage(reference);
    }

    public ArrayList<IMessage> recevoirMessages(InfraAgentReference reference) {
        return this.receiveMessages(reference);
    }

    /**
     * Change la stratégie de l'Ordonnanceur.
     * @param strategie
     */
    public void changerStrategie(IStratOrdonnanceur strategie) {
        this.changerOrdonnancement(strategie);
    }

    /**
     * Ajoute un agent à l'Ordonnanceur.
     * @param agent
     */
    public void ajouterAgentOrdonnanceur(InfraAgent agent) {
        this.ordagentAjoute(agent);
    }

    /**
     * Retire un agent de l'Ordonnanceur.
     * @param agent
     */
    public void retirerAgentOrdonnanceur(InfraAgent agent) {
        this.OrdagentRetire(agent);
    }

}
