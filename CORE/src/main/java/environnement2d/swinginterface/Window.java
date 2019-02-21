package environnement2d.swinginterface;

import plateau.Plateau;

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
        Plateau plateau = new Plateau("titre", 80, 100);
        Window window = new Window(plateauToolsPanel, plateau);

        MainPanel panel = new MainPanel(window.plateauToolsPanel, window.plateau);

        window.setContent(panel);
    }
}

