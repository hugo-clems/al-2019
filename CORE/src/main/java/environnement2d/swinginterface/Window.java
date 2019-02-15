package environnement2d.swinginterface;

import entites.AbstractEntite;
import entites.Obstacle;
import plateau.Case;
import plateau.Plateau;
import plateau.Position;

import java.awt.*;

import javax.swing.*;

/**
 * Main class.
 */
public class Window {
    /**
     * Le main frame.
     */
    private JFrame frame;

    private PlateauToolsPanel plateauToolsPanel;
    private Plateau plateau;

    /**
     * Constructor.
     *
     * @throws HeadlessException when code that is dependent on a keyboard,
     *                           display, or mouse
     *                           is called in an environment that
     *                           does not support a keyboard, display, or mouse
     */
    public Window(PlateauToolsPanel plateauToolsPanel, Plateau plateau) throws HeadlessException {
        this.plateauToolsPanel = plateauToolsPanel;
        this. plateau = plateau;
        frame = new JFrame();
        frame.setTitle("AL2018-2019");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Getter frame.
     *
     * @return frame
     */
    public final JFrame getFrame() {
        return frame;
    }

    /**
     * Modifier le contenu du panel.
     *
     * @param panel panel a modifier
     */
    public final void setContent(final JPanel panel) {
        getFrame().setContentPane(panel);
        getFrame().pack();
        getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        getFrame().setVisible(true);
    }

    /**
     * Main.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        PlateauToolsPanel plateauToolsPanel = new PlateauToolsPanel();
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

        Window window = new Window(plateauToolsPanel, plateau);

        MainPanel panel = new MainPanel(window.plateauToolsPanel, window.plateau);

        window.setContent(panel);
    }
}

