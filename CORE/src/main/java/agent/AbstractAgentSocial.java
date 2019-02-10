package agent;

import AmbientEnvironment.OCPlateforme.OCService;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Communication.IMessage;
import MASInfrastructure.Etat.LifeCycle;
import MASInfrastructure.Infrastructure;

public class AbstractAgentSocial extends AbstractAgent {

    /**
     *
     */
    private Infrastructure infra = new Infrastructure();

    /**
     * Constructeur par défaut.
     * @param lifeCycle
     * @param myMailBoxManager
     */
    public AbstractAgentSocial(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
    }

    /**
     * Diffuse un message à tous les agents sociaux.
     * @param message
     */
    public void diffuserMessage(IMessage message) {
        infra.sendMessageBroadcast(message);
    }

}
