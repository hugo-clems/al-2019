package environnement2d;

import javax.swing.*;
import java.awt.*;

public class InfraControlPanel extends JPanel {
    private static final int SPACE = 5;
    public InfraControlPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
    }

    public void addButton(JButton button) {
        this.add(button);
    }
}
