package demo;

import agent.AbstractAgentSitue;
import common.Direction;
import entites.AbstractEntite;
import plateau.Case;
import plateau.IAgentPlateau;
import plateau.IAgentite;

import java.util.Map;

public class AgentDemo extends AbstractAgentSitue {

    /**
     * Constructor
     *
     * @param plateau IAgentPlateau
     */
    public AgentDemo(IAgentPlateau plateau) {
        super(plateau);
    }

    private int count = 0;

    @Override
    public void actionTour() {
        Map<Direction, Case> environnement = this.detecter();
        if (this.getEntitePortee() != null) {
            count++;
            if (count >= 5) {
                count = 0;
                this.deposer(this.getEntitePortee());
                return;
            }
        }
        for (Case lCase : environnement.values()) {
            for (IAgentite agentite : lCase.getAgentites()) {
                if (agentite instanceof EntitePasiveDemo) {
                    if (this.ramasser((AbstractEntite) agentite))
                        return;
                }
            }
        }
        if (!this.avancer()) {
            this.faireDemiTour();
        }
    }

    private void faireDemiTour() {
        switch (this.getDirection()) {
            case N:
                this.setDirection(Direction.S);
                break;
            case SO:
                this.setDirection(Direction.NE);
                break;
            case S:
                this.setDirection(Direction.N);
                break;
            case NE:
                this.setDirection(Direction.SO);
                break;
            case NO:
                this.setDirection(Direction.SE);
                break;
            case E:
                this.setDirection(Direction.O);
                break;
            case O:
                this.setDirection(Direction.E);
                break;
            case SE:
                this.setDirection(Direction.NO);
                break;
        }
    }
}
