package environnement2d;

import strategie.Strategie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

        this.strategie = new Strategie(plateau.getAgents());
    }

    /**
     * Modifier le contenu du panel.
     *
     * @param panel panel a modifier
     */
    public final void setContent(final JPanel panel) {
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setMinimumSize(new Dimension(400,400));
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setVisible(true);
    }

    /**
     * initialisation de window avec les boutons et le main panel
     */
    public void start() {
        MainPanel panel = new MainPanel(this.plateau);
        panel.addButton(this.start);
        panel.addButton(this.pause);
        panel.addButton(this.stop);

        this.setContent(panel);
    }

    /**
     * Lancement de la strategie
     * @param e ActionEvent
     */
    private void startStrategie(ActionEvent e) {
        this.strategie.lancer();
    }

    /**
     * Arreter la strategie
     * @param e ActionEvent
     */
    private void stopStrategie(ActionEvent e) {
        this.strategie.arreter();
    }

    /**
     * Mettre en pause/reprendre la strategie
     *
     * @param e ActionEvent
     */
    private void pauseStrategie(ActionEvent e) {
        switch (pause.getText()){
            case "Pause":{
                this.strategie.pause();
                this.pause.setText("Reprendre");
                break;
            }
            case "Reprendre":{
                this.strategie.lancer();
                this.pause.setText("Pause");
                break;
            }
            default:
                break;
        }

    }
}

