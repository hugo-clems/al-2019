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
     * Smart Cursor.
     */
    private SmartCursor cursor;

    /**
     * PlateauPanel.
     */
    private PlateauPanel plateauPanel;
    /**
     * Constructor.
     */
    public MainPanel() {
        this.plateauPanel = new PlateauPanel(this, cursor);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(plateauPanel);
    }


}

