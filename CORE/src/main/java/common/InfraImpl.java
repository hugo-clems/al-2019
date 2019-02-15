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

public class InfraImpl extends Infrastructure {

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
