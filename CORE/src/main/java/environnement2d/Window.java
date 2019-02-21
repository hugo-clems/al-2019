package environnement2d;

import entites.AbstractEntite;
import entites.Obstacle;
import plateau.Plateau;
import plateau.Position;
import strategie.Strategie;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Main class.
 */
public class Window {
    /**
     * Le main frame.
     */
    private JFrame frame;

    private PlateauComponent plateau;

    private JButton start;
    private JButton stop;

    private Strategie strategie;

    /**
     * Constructor.
     *
     * @throws HeadlessException when code that is dependent on a keyboard,
     *                           display, or mouse
     *                           is called in an environment that
     *                           does not support a keyboard, display, or mouse
     */
    public Window(PlateauComponent plateau) throws HeadlessException {
        this.plateau = plateau;
        this.frame = new JFrame();
        this.frame.setTitle("AL2018-2019");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.start = new JButton();
        this.start.addActionListener(this::startStrategie);
        this.start.setText("Start");

        this.stop = new JButton();
        this.stop.addActionListener(this::stopStrategie);
        this.stop.setText("Stop");

        this.strategie = new Strategie();
    }

    /**
     * Modifier le contenu du panel.
     *
     * @param panel panel a modifier
     */
    public final void setContent(final JPanel panel) {
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setVisible(true);
    }

    /**
     * Main.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        Plateau plateau = new Plateau("NamePlateau", 40, 50);
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 40; y++) {
                Position p = new Position(x, y);
                //Ajout obstacles au bord
                if (x < 30 && y > 10 && y < 13) {
                    Obstacle o = new Obstacle("Obstacle" + x + y, plateau);
                    plateau.placerAgentite(p, o);
                }
                if (x == 2 && y == 3 || x == 30 && y == 39) {
                    AbstractEntite agent = new AbstractEntite("agent", plateau) {
                    };
                    plateau.placerAgentite(p, agent);
                }
            }
        }

        PlateauComponent pc = new PlateauComponent(plateau);

        Window window = new Window(pc);

        MainPanel panel = new MainPanel(window.plateau);
        panel.setStart(window.start);
        panel.setStop(window.stop);

        window.setContent(panel);
    }

    private void startStrategie(ActionEvent e) {
        this.strategie.lancer();
    }

    private void stopStrategie(ActionEvent e) {
        this.strategie.arreter();
    }
}

