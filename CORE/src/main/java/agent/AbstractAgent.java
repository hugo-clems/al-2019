package agent;

import MASInfrastructure.Agent.InfraAgent;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import plateau.IAgentPlateau;
import plateau.IAgentite;

public abstract class AbstractAgent extends InfraAgent implements IAgentite {

    /**
     * L'interface du plateau.
     */
    public IAgentPlateau plateau;

    /**
     * Constructeur par défaut.
     * @param lifeCycle le cycle de vie de l'agent
     * @param myMailBoxManager le moyen de communication de l'agent
     */
    public AbstractAgent(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(null, lifeCycle, myMailBoxManager);
    }

}
