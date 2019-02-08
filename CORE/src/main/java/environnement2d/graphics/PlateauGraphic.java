package environnement2d.graphics;

import environnement2d.swinginterface.MainPanel;

import javax.swing.*;

public class PlateauGraphic extends JComponent{
    private JButton start;
    private MainPanel panel;


    public PlateauGraphic(MainPanel panel) {
        this.panel = panel;
    }

    public void setStartButton(JButton startButton) {
        this.start = startButton;
    }
}
