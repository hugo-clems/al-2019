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
     * @param lifeCycle le cycle de vie de l'agent
     * @param myMailBoxManager le moyen de commuication de l'agent
     * @return l'agent qui a été créé
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
     * @param reference la référence de l'agent
     * @return le message reçu
     */
    public Optional<IMessage> recevoirMessage(InfraAgentReference reference) {
        return this.receiveMessage(reference);
    }

    /**
     * Reçois plusieurs messages.
     * @param reference la référence de l'agent
     * @return une list des messages reçue
     */
    public ArrayList<IMessage> recevoirMessages(InfraAgentReference reference) {
        return this.receiveMessages(reference);
    }

    /**
     * Change la stratégie de l'Ordonnanceur.
     * @param strategie la stratégie d'ordonnancement
     */
    public void changerStrategie(IStratOrdonnanceur strategie) {
        this.changerOrdonnancement(strategie);
    }

    /**
     * Ajoute un agent à l'Ordonnanceur.
     * @param agent l'agent à ajouter à l'ordonnanceur
     */
    public void ajouterAgentOrdonnanceur(InfraAgent agent) {
        this.ordagentAjoute(agent);
    }

    /**
     * Retire un agent de l'Ordonnanceur.
     * @param agent l'agent à retirer de l'ordonnanceur
     */
    public void retirerAgentOrdonnanceur(InfraAgent agent) {
        this.OrdagentRetire(agent);
    }

}
