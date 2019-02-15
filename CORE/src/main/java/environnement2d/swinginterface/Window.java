package environnement2d.swinginterface;

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

    /**
     * Constructor.
     *
     * @throws HeadlessException when code that is dependent on a keyboard,
     *                           display, or mouse
     *                           is called in an environment that
     *                           does not support a keyboard, display, or mouse
     */
    public Window() throws HeadlessException {
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
        Window window = new Window();
        SmartCursor cursor = new SmartCursor();
        MainPanel panel = new MainPanel();
        //PlateauPanel plateau = new PlateauPanel(panel, cursor);
        window.setContent(panel);
    }
}

