package plateau;

import entites.AbstractEntite;
import agent.AbstractAgentSitue;

import java.awt.*;

public interface IDeveloppeurPlateau {

    Boolean placerAgent(Point point, AbstractAgentSitue agent);
    Boolean placerEntite(Point point, AbstractEntite agent);
    AbstractEntite enleverEntite(Point point, AbstractEntite entite);
}
