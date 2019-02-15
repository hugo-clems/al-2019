package agent;

import AmbientEnvironment.OCPlateforme.OCService;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Communication.IMessage;
import MASInfrastructure.Etat.LifeCycle;
import MASInfrastructure.Infrastructure;

public class AbstractAgentSocial extends AbstractAgent {

    /**
     * l'infrastructure d'infra
     */
    private Infrastructure infra = new Infrastructure();

    /**
     * Constructeur par défaut.
     * @param lifeCycle le cycle de vie de l'agent
     * @param myMailBoxManager le moyen de communication de l'agent
     */
    public AbstractAgentSocial(LifeCycle lifeCycle, ICommunication myMailBoxManager) {
        super(lifeCycle, myMailBoxManager);
    }

    /**
     * Diffuse un message à tous les agents sociaux.
     * @param message le message qui va être diffusé
     */
    public void diffuserMessage(IMessage message) {
        infra.sendMessageBroadcast(message);
    }

}
