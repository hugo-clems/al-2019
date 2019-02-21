package environnement2d.swinginterface;

import javax.swing.*;
import java.awt.*;

public class InfraControlPanel extends JPanel {
    private static final int SPACE = 5;
    public InfraControlPanel() {
        JButton start = new JButton();
        start.setText("Start");

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(start);
    }
}
