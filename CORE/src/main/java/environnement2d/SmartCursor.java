package environnement2d;

import agent.AbstractAgent;
import entites.Obstacle;
import plateau.IAgentPlateau;

import java.awt.*;

/**
 * @author Sarra Boussioud on 13/02/2019
 */
public class SmartCursor {

    private IAgentPlateau plateau;
    private AbstractAgent agent;

    /**
     * Les obstacles.
     */
    private Obstacle obstacle;

    /**
     * Verifier si cursor active.
     */
    private boolean active;

    /**
     * Activer cursor.
     */
    public final void activate(){
        active = true;
    }

    /**
     * Setter obstacle
     *
     * @param obstacle obstacle
     */
    public final void setObstacle(final Obstacle obstacle){
        this.obstacle = obstacle;
        activate();
    }

    /**
     * Getter obstacle.
     *
     * @return obstacle
     */
    public final Obstacle getObstacle(){
        return obstacle;
    }

    /**
     * Desactiver cursor.
     */
    public final void desactivate() {
        active = false;
    }

    /**
     * Set coordonnes d'un agent.
     *
     * @param x x
     * @param y y
     */
    public final void setCoordMovingAgent(
            final int x,
            final int y) {
        plateau.getCase(agent).getPosition().setX(x);  // TODO: 13/02/2019 setcase()
        plateau.getCase(agent).getPosition().setY(y);
    }


    /**
     * Paint.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    public final void paint(final Graphics2D g,
                            final int x,
                            final int y) {
        Graphics2D g2 = g;
        Composite comp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, (float) 0.5);
        g2.setComposite(comp);
//        agent.paint(g2, x, y);
    }


    }
