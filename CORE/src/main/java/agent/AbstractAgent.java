package agent;

import MASInfrastructure.Agent.InfraAgent;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import plateau.IAgentite;

public abstract class AbstractAgent extends InfraAgent implements IAgentite {

    public AbstractAgent(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(null, lifeCycle, myMailBoxManager);
    }

}
