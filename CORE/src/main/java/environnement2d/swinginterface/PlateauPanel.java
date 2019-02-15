package environnement2d.swinginterface;

import environnement2d.graphics.PlateauGraphic;

import javax.swing.*;
import java.awt.*;

public class PlateauPanel extends JComponent {

    private PlateauGraphic plateauGraphic;

    private PlateauToolsPanel plateauToolsPanel;

    /**
     * Espace entre les boutons.
     */
    private static final int SPACE = 5;

    /**
     * Constructor.
     *
     * @param panel  main panel
     */
    public PlateauPanel(final MainPanel panel, final SmartCursor cursor) {
        plateauGraphic = new PlateauGraphic(panel, cursor);
        plateauToolsPanel = new PlateauToolsPanel(plateauGraphic);
        plateauGraphic.setStartButton(plateauToolsPanel.getStartButton());


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(plateauGraphic);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
        this.add(plateauToolsPanel);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));

    }

    public final PlateauGraphic getPlateauGraphic() {
        return plateauGraphic;
    }
}
