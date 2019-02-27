package demo;

import agent.AbstractAgentSitue;
import plateau.IAgentPlateau;

public class AgentDemo extends AbstractAgentSitue {

    public AgentDemo(IAgentPlateau plateau) {
        super(plateau);
    }

    @Override
    public void actionTour() {
        this.avancer();
    }
}
