package environnement2d;

import entites.AbstractEntite;
import entites.Obstacle;
import plateau.Plateau;
import plateau.Position;
import strategie.Strategie;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Main class.
 */
public class Window {
    /**
     * Le main frame.
     */
    private JFrame frame;

    private PlateauComponent plateau;

    private JButton start;
    private JButton stop;
    private JButton pause;

    private Strategie strategie;

    /**
     * Constructor.
     *
     * @throws HeadlessException when code that is dependent on a keyboard,
     *                           display, or mouse
     *                           is called in an environment that
     *                           does not support a keyboard, display, or mouse
     */
    public Window(PlateauComponent plateau) throws HeadlessException {
        this.plateau = plateau;
        this.frame = new JFrame();
        this.frame.setTitle("AL2018-2019");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.start = new JButton();
        this.start.addActionListener(this::startStrategie);
        this.start.setText("Start");

        this.stop = new JButton();
        this.stop.addActionListener(this::stopStrategie);
        this.stop.setText("Stop");

        this.pause = new JButton();
        this.pause.addActionListener(this::pauseStrategie);
        this.pause.setText("Pause");

        this.strategie = new Strategie();
    }

    /**
     * Modifier le contenu du panel.
     *
     * @param panel panel a modifier
     */
    public final void setContent(final JPanel panel) {
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setVisible(true);
    }

    public void start() {
        MainPanel panel = new MainPanel(this.plateau);
        panel.addButton(this.start);
        panel.addButton(this.pause);
        panel.addButton(this.stop);

        this.setContent(panel);
    }

    private void startStrategie(ActionEvent e) {
        this.strategie.lancer();
    }

    private void stopStrategie(ActionEvent e) {
        this.strategie.arreter();
    }

    private void pauseStrategie(ActionEvent e) {
        this.strategie.pause();
        this.pause.setText("Reprendre");
    }
}

