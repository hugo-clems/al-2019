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

}
