package environnement2d.physics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Sarra Boussioud on 13/02/2019
 */
public class Physique {

    /**
     * Lignes.
     */
    private int lignes = 42;

    /**
     * Colonnes.
     */
    private int colonnes = 24;

    /**
     * Timer.
     */
    private Timer timer = new Timer(0, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });

    /**
     * Enum.
     */
    public enum TypeP {
        tank,
        vide,
        projectile,
        obstacle,
        mur
    }

    /**
     * Retourner le timer.
     *
     * @return timer
     */
    public final Timer getTimer() {
        return timer;
    }

    /**
     * Retourner le nombre de lignes total.
     *
     * @return lignes
     */
    public final int getLignes() {
        return lignes;
    }

    /**
     * Retourner le nombre de colonnes total.
     *
     * @return colonnes
     */
    public final int getColonnes() {
        return colonnes;
    }

}
