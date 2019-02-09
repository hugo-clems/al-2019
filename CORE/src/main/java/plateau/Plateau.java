package plateau;

import agent.AbstractAgent;
import agent.AbstractAgentSitue;
import common.Direction;
import entites.AbstractEntite;
import entites.Obstacle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Plateau implements IEntitePlateau, IDeveloppeurPlateau, IAgentPlateau {

    private String nom ;
    private Map<IAgentite, Case> listeAgentites ;
    private List<Case> cases ;

    @Override
    public Boolean placerAgentite(Position position, IAgentite agentite) {
        return null;
    }

    @Override
    public IAgentite enleverAgentite(Position position, IAgentite agentite) {
        return null;
    }

    @Override
    public Boolean deplacerAgent(AbstractAgent agent, Direction direction) {
        Case c = cases.get(0);
        switch (direction) {
            case N:
                c = findCase (getCase(agent), 0, 1);
                break;
            case NE:
                c = findCase (getCase(agent), 1, 1);
                break;
            case E:
                c = findCase (getCase(agent), 1, 0);
                break;
            case SE:
                c = findCase (getCase(agent), 1, -1);
                break;
            case S:
                c = findCase (getCase(agent), 0, -1);
                break;
            case SO:
                c = findCase (getCase(agent), -1, -1);
                break;
            case O:
                c = findCase (getCase(agent), -1, 0);
                break;
            case NO:
                c = findCase (getCase(agent), -1, 1);
                break;
        }

        //Vérification s'il n'y a pas d'obstacle sur la case où veut bouger l'agent
        for (IAgentite agentite : c.getAgentites()) {
            if (agentite instanceof Obstacle) {
                return false;
            }
        }

        //Déplacement de l'agent
        getCase(agent).getAgentites().remove(agent);
        c.getAgentites().add(agent);
        listeAgentites.replace(agent, c);
        return true;
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
    public Map <Direction, Case> getVoisinage(AbstractAgent agent) {
        Map <Direction, Case> map = new HashMap <Direction, Case>();
        for (Direction direction : Direction.values()) {
            map.put(direction, caseDirection(direction, getCase(agent)));
        }
        return map;
    }

    @Override
    public Case getCase(AbstractAgent agent) {
        return listeAgentites.get(agent);
    }

    private Case caseDirection(Direction direction, Case caseAgent) {
        Case c = cases.get(0);
        switch (direction) {
            case N:
                c = findCase (caseAgent, 0, 1);
                break;
            case NE:
                c = findCase (caseAgent, 1, 1);
                break;
            case E:
                c = findCase (caseAgent, 1, 0);
                break;
            case SE:
                c = findCase (caseAgent, 1, -1);
                break;
            case S:
                c = findCase (caseAgent, 0, -1);
                break;
            case SO:
                c = findCase (caseAgent, -1, -1);
                break;
            case O:
                c = findCase (caseAgent, -1, 0);
                break;
            case NO:
                c = findCase (caseAgent, -1, 1);
                break;
        }
        return c;
    }

    private Case findCase (Case caseAgent, int x, int y) {
        boolean trouve = false;
        int i = 0;
        Case c = cases.get(0);
        while (!trouve) {
            if (cases.get(i).getPosition().getX() == caseAgent.getPosition().getX() + x
                    && cases.get(i).getPosition().getY() == caseAgent.getPosition().getY() + y) {
                trouve = true;
                c = cases.get(i);
            }
            i++;
        }
        return c;
    }
}
