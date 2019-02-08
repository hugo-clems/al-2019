package plateau;

import entites.AbstractEntite;
import agent.AbstractAgentSitue;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Plateau implements IEntitePlateau, IDeveloppeurPlateau {

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
    public Boolean placerAgent(Point point, AbstractAgentSitue agent) {
        return null;
    }

    @Override
    public Boolean placerEntite(Point point, AbstractEntite agent) {
        return null;
    }

    @Override
    public AbstractEntite enleverEntite(Point point, AbstractEntite entite) {
        return null;
    }
}
