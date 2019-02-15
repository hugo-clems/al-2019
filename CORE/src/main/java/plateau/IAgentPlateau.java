package plateau;

import agent.AbstractAgent;
import common.Direction;
import entites.AbstractEntite;

import java.util.Map;

public interface IAgentPlateau {

    /**
     * Déplace un agent d'une case en fonction de la Direction donnée.
     * @param agent
     * @param direction
     * @return
     */
    Boolean deplacerAgent(AbstractAgent agent, Direction direction);

    /**
     * Ramasse une entité qui se trouve devant l'agent.
     * @param agent
     * @param entite
     * @return
     */
    Boolean ramasserEntite(AbstractAgent agent, AbstractEntite entite);

    /**
     * Dépose une entité sur la même case que l'agent.
     * @param agent
     * @param entite
     * @return
     */
    Boolean deposerEntite(AbstractAgent agent, AbstractEntite entite);

    /**
     * Récupère le voisinnage de l'agent.
     * @param agent
     * @return
     */
    Map<Direction, Case> getVoisinage(AbstractAgent agent);

    /**
     * Récupère la Case sur laquelle se trouve l'agent.
     * @param agent
     * @return
     */
    Case getCase(AbstractAgent agent);


}
