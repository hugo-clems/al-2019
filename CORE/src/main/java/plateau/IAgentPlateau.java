package plateau;

import agent.AbstractAgent;
import common.Direction;
import entites.AbstractEntite;

import java.util.Map;

public interface IAgentPlateau {

    Boolean deplacerAgent(AbstractAgent agent, Direction direction);

    Boolean ramasserEntite(AbstractAgent agent, AbstractEntite entite);

    Boolean deposerEntite(AbstractAgent agent, AbstractEntite entite);

    Map<Direction, Case> getVoisinage(AbstractAgent agent);

    Case getCase(AbstractAgent agent);


}
