package environnement2d.graphics;

import agent.AbstractAgent;
import entites.Obstacle;
import environnement2d.physics.ObjetTT;
import environnement2d.physics.Physique;
import environnement2d.swinginterface.MainPanel;
import environnement2d.swinginterface.SmartCursor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PlateauGraphic extends JComponent{
    /**
     * start.
     */
    private JButton start;

    /**
     * panel.
     */
    private MainPanel panel;

    /**
     * cursor.
     */
    private SmartCursor cursor;

    /**
     * Physique.
     */
    private Physique physics = new Physique();

    /**
     * Size d'un pixel.
     */
    private static final int PIXEL_SIZE = 32;

    /**
     * Taille d'une case.
     */
    private static int tailleCase = 32;

    public PlateauGraphic(MainPanel panel, final SmartCursor cursor) {
        this.panel = panel;
        this.cursor = cursor;
    }

    public void setStartButton(JButton startButton) {
        this.start = startButton;
    }

    /**
     * Centrer.
     *
     * @param g graphic
     */
    public final void centrage(final Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        int largeurAffichage = physics.getLignes() * PIXEL_SIZE,
                hauteurAffichage = physics.getColonnes() * PIXEL_SIZE;
        double diffX = this.getWidth() / (double) largeurAffichage,
                diffY = this.getHeight() / (double) hauteurAffichage;
        if (diffY > diffX) {
            g2.translate(0, Math.max(hauteurAffichage * diffX - this.getHeight(),
                    this.getHeight() - hauteurAffichage * diffX) / 2);
            g2.scale(diffX, diffX);
        } else {
            g2.translate(Math.max(largeurAffichage * diffY - this.getWidth(),
                    this.getWidth() - largeurAffichage * diffY) / 2, 0);
            g2.scale(diffY, diffY);
        }
    }

    /**
     * Retourner l'obstacle au point (x,y).
     *
     * @param x x
     * @param y y
     * @return obs
     */
    public final Obstacle getObstacleAt(final int x,
                                        final int y) {
        return null;
    }

    /**
     * Agent est dans le plateau.
     *
     * @param agent agent
     * @return boolean
     */
    public final boolean tankIsIn(final AbstractAgent agent) {
        return true;
    }

    /**
     * Ajouter un agent au plateau.
     *
     * @param agent agent
     */
    public final void addDTank(final AbstractAgent agent) {

    }

    /**
     * Get point.
     *
     * @param x x
     * @param y y
     * @return point2D
     */
    public final Point2D getPhysicPoint(final int x,
                                        final int y) {
        int virtual_width = physics.getLignes() * PIXEL_SIZE;
        int virtual_height = physics.getColonnes() * PIXEL_SIZE;
        int real_width = getWidth();
        int real_height = getHeight();
        double width_ratio = real_width / (double) virtual_width;
        double height_ratio = real_height / (double) virtual_height;
        double choosen_ratio = Math.min(width_ratio, height_ratio);
        double painting_width = choosen_ratio * virtual_width;
        double painting_height = choosen_ratio * virtual_height;
        int final_x = -1;
        int final_y = -1;
        if (width_ratio < height_ratio) { // cas on a colle sur les cotes
            final_x = (int) (x / choosen_ratio);
            double offset_height = Math.max(real_height - painting_height,
                    painting_height - real_height);
            final_y = (int) ((y - offset_height / 2) / choosen_ratio);
        } else {
            final_y = (int) (y / choosen_ratio);
            double offset_width = Math.max(real_width - painting_width,
                    painting_width - real_width);
            final_x = (int) ((x - offset_width / 2) / choosen_ratio);
        }
        return new Point(final_x / PIXEL_SIZE, final_y / PIXEL_SIZE);
    }

    /**
     * Paint highlight.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    public final void paintHighlight(final Graphics g,
                                     final int x,
                                     final int y) {
        g.setColor(Color.red);
        g.drawArc(x - 5, y - 4, PIXEL_SIZE, PIXEL_SIZE, 0, 360);
    }

    /**
     * Paint.
     *
     * @param g graphic
     */
    @Override
    public final void paintComponent(final Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        centrage(g);
        ObjetTT contenu;
        for (int i = 0; i < physics.getLignes(); i++) {
            for (int j = 0; j < physics.getColonnes(); j++) {
                int x = i * tailleCase;
                int y = j * tailleCase;
//                contenu = physics.detail(i, j);
//                contenu.paint(g, x, y);
//                if (highlightTank == contenu) {
                    paintHighlight(g, x, y);
//                }
            }
        }
    }
}
