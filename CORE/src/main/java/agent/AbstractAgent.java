package agent;

import MASInfrastructure.Agent.InfraAgent;
import MASInfrastructure.Agent.InfraAgentReference;
import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import MASInfrastructure.Infrastructure;
import plateau.IAgentPlateau;
import plateau.IAgentite;

public abstract class AbstractAgent extends InfraAgent implements IAgentite {

    /**
     * L'infrastructure.
     */
    protected Infrastructure infra = new Infrastructure();

    /**
     * Référence de l'agent.
     */
    protected InfraAgentReference reference;

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
        this.reference = this.getInfraAgentReference();
    }

}
