package agent;

import MASInfrastructure.Agent.InfraAgent;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;

public abstract class AbstractAgent extends InfraAgent {

    public AbstractAgent(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(null, lifeCycle, myMailBoxManager);
    }

}
