package environnement2d.physics;

import java.awt.*;

/**
 * @author Sarra Boussioud on 13/02/2019
 */
public interface ObjetTT {
    /**
     * Type physique.
     *
     * @return type
     */
    Physique.TypeP getType();

    /**
     * Paint.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    void paint(Graphics g, int x, int y);
}
