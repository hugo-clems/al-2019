package environnement2d.swinginterface;


import environnement2d.graphics.PlateauComponent;

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

    private PlateauPanel plateauPanel;
    private InfraControlPanel infraControlPanel;


    /**
     * Constructor.
     */
    public MainPanel() {
        plateauPanel = new PlateauPanel();
        infraControlPanel = new InfraControlPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(plateauPanel);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
        this.add(infraControlPanel);
    }


}

