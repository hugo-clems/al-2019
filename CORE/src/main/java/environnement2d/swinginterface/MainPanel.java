package environnement2d.swinginterface;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Panel principal.
 */
public class MainPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 5131738883703122146L;

    /**
     * Constructor.
     */
    public MainPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }


}

