package agent;

import AmbientEnvironment.OCPlateforme.OCService;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Communication.IMessage;
import MASInfrastructure.Etat.LifeCycle;
import MASInfrastructure.Infrastructure;

public class AbstractAgentSocial extends AbstractAgent {

    private Infrastructure infra = new Infrastructure();

    public AbstractAgentSocial(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
    }

    public void diffuserMessage(IMessage message) {
        infra.sendMessageBroadcast(message);
    }

}
