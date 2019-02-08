package plateau;

import agent.AbstractAgent;
import agent.AbstractAgentSitue;
import common.Direction;
import entites.AbstractEntite;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Plateau implements IEntitePlateau, IDeveloppeurPlateau, IAgentPlateau {

    private String nom ;
    private Map<AbstractAgentSitue, Case> listeAgents ;
    private Map<AbstractEntite, Case> listeEntites ;
    private List<Case> cases ;


    public Boolean placerAgent() {
        return null;
    }


    public Boolean placerEntite() {
        return null;
    }

    public AbstractEntite enleverEntite() {
        return null;
    }

    @Override
    public Boolean placerAgentite(Point point, IAgentite agentite) {
        return null;
    }

    @Override
    public IAgentite enleverAgentite(Point point, IAgentite agentite) {
        return null;
    }

    @Override
    public Boolean deplacerAgent(AbstractAgent agent, Direction direction) {
        return null;
    }

    @Override
    public Boolean ramasserEntite(AbstractAgent agent, AbstractEntite entite) {
        return null;
    }

    @Override
    public Boolean deposerEntite(AbstractAgent agent, AbstractEntite entite) {
        return null;
    }

    @Override
    public Map<Direction, Case> getVoisinage(AbstractAgent agent) {
        return null;
    }

    @Override
    public Case getCase(AbstractAgent agent) {
        return null;
    }
}
