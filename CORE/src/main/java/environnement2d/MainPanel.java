package environnement2d;


import plateau.Plateau;

import javax.swing.*;
import java.awt.*;

/**
 * Panel principal.
 * Contient le panel du plateau et le panel de controle de l'infra
 */
public class MainPanel extends JPanel {
    /**
     * Espace entre les boutons.
     */
    private static final int SPACE = 5;

    private InfraControlPanel infraControlPanel;


    /**
     * Constructor.
     */
    public MainPanel(PlateauComponent plateauComponent) {
        infraControlPanel = new InfraControlPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(plateauComponent);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
        this.add(infraControlPanel);

    }

    /**
     * Ajouter un bouton
     *
     * @param button JButton
     */
    public void addButton(JButton button) {
        this.infraControlPanel.addButton(button);
    }

}

