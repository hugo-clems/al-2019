package environnement2d;

import javax.swing.*;
import java.awt.*;

public class InfraControlPanel extends JPanel {
    private JButton start;
    private JButton stop;

    private static final int SPACE = 5;
    public InfraControlPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
    }

    public void setStart(JButton start) {
        this.start = start;
        this.add(this.start);
    }

    public void setStop(JButton stop) {
        this.stop = stop;
        this.add(this.stop);
    }
}
