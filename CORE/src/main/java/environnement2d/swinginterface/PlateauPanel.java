package environnement2d.swinginterface;

import environnement2d.graphics.PlateauComponent;
import plateau.Plateau;

import javax.swing.*;
import java.awt.*;


/**
 * Panel principal du plateau.
 *
 * Contient une toolbox contenant les entités / agents à placer
 * et la représentation du plateau
 */
public class PlateauPanel extends JPanel {
    private static final int SPACE = 5;

    private PlateauComponent plateauComponent;

    private PlateauToolsPanel plateauToolsPanel;



    public PlateauPanel(PlateauToolsPanel plateauToolsPanel, Plateau plateau, SmartCursor cursor) {
        plateauComponent = new PlateauComponent(plateau);
        plateauToolsPanel = new PlateauToolsPanel();
        plateauComponent.setStartButton(plateauToolsPanel.getStartButton());

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        this.setLayout(layout);

        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.BOTH;
        this.add(plateauToolsPanel, c);


        this.add(Box.createHorizontalStrut(SPACE));
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.7;
        this.add(plateauComponent, c);
    }
}
