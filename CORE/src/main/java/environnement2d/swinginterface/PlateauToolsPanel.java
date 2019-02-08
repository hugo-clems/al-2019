package environnement2d.swinginterface;

import environnement2d.graphics.PlateauGraphic;

import javax.swing.*;
import java.awt.*;

public class PlateauToolsPanel extends JPanel {
    private PlateauGraphic plateauGraphic;
    private static final int SPACE = 5;
    private JButton start;

    public PlateauToolsPanel(PlateauGraphic plateauGraphic) {
        this.plateauGraphic = plateauGraphic;
        start = new JButton();
        start.setText("Start");

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(start);
    }

    public JButton getStartButton() {
        return this.start;
    }
}
