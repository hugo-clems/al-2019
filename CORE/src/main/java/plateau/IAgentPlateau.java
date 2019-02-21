package plateau;

import agent.AbstractAgent;
import common.Direction;
import entites.AbstractEntite;

import java.util.Map;

public interface IAgentPlateau {

    /**
     * Déplace un agent d'une case en fonction de la Direction donnée.
     * @param agent l'agent à déplacer
     * @param direction la direction vers laquelle déplacer l'agent
     * @return true si l'agent s'est déplacé et false sinon
     */
    Boolean deplacerAgent(AbstractAgent agent, Direction direction);

    /**
     * Ramasse une entité qui se trouve devant l'agent.
     * @param agent l'agent qui ramasse l'entité
     * @param entite l'entité à ramasser
     * @return true si l'entité a été ramassée et false sinon
     */
    Boolean ramasserEntite(AbstractAgent agent, AbstractEntite entite);

    /**
     * Dépose une entité sur la même case que l'agent.
     * @param agent l'agent qui dépose l'entité
     * @param entite l'entité à déposer
     * @return true si l'agent a déposé l'agent et false sinon
     */
    Boolean deposerEntite(AbstractAgent agent, AbstractEntite entite);

    /**
     * Récupère le voisinnage de l'agent.
     * @param agent l'agent qui veut connaître son voisinage
     * @return une map contenant les cases par direction
     */
    Map<Direction, Case> getVoisinage(AbstractAgent agent);

    /**
     * Récupère la Case sur laquelle se trouve l'agent.
     * @param agent l'agent qui veut connaître sa case
     * @return la case sur laquelle se trouve l'agent
     */
    Case getCase(AbstractAgent agent);


}
