package environnement2d.swinginterface;

import environnement2d.graphics.PlateauComponent;

import javax.swing.*;
import java.awt.*;

public class PlateauToolsPanel extends JPanel {
    private PlateauComponent plateauComponent;
    private static final int SPACE = 5;
    private JButton start;

    public PlateauToolsPanel(PlateauComponent plateauComponent) {
        this.plateauComponent = plateauComponent;
    }

    public JButton getStartButton() {
        return this.start;
    }
}
