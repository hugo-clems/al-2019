package agent;

import MASInfrastructure.Agent.InfraAgent;
import MASInfrastructure.Agent.InfraAgentReference;
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
     */
    public AbstractAgent() {
        super(null, new LifeCycle(null), new Infrastructure());
        this.reference = this.getInfraAgentReference();
    }

    public AbstractAgent(IAgentPlateau plateau) {
        super(null, new LifeCycle(null), new Infrastructure());
        this.reference = this.getInfraAgentReference();
        this.plateau = plateau;
    }

    /**
     * Effectue l'action durant le tour de l'agent.
     */
    public abstract void actionTour();

}
