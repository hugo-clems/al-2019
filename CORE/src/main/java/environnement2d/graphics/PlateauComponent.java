package environnement2d.graphics;

import entites.AbstractEntite;
import environnement2d.swinginterface.MainPanel;
import plateau.Plateau;

import javax.swing.*;
import java.awt.*;

public class PlateauComponent extends JComponent {
    private JButton start;
    private MainPanel panel;

    private final int CASE_SIZE = 8;

    private Plateau plateau;

    public PlateauComponent(Plateau plateau) {

        this.plateau = plateau;
    }

    // TODO make a real implementation with maybe images, compenents for each type of entity etc
    @Override
    protected void paintComponent(Graphics g) {
        //TODO: remplacer List<Case> par Map<Position, Cases>
//        List<Case> cases = this.plateau.getCases();
//        for (int i = 0, max = cases.size(); i < max; i++) {
//            if (cases.get(i).getAgentites().size() == 0) {
//                Position pos = cases.get(i).getPosition();
//                int x = pos.getX();
//                int y = pos.getY();
//
//                g.setColor(Color.black);
//                g.fillRect(x * CASE_SIZE, y * CASE_SIZE, CASE_SIZE, CASE_SIZE);
//            } else {
//                Position pos = cases.get(i).getPosition();
//                int x = pos.getX();
//                int y = pos.getY();
//
//                g.setColor(Color.white);
//
//                Stream<IAgentite> iAgentiteStream = cases.get(i).getAgentites().stream().filter(aCase -> aCase instanceof Obstacle);
//                if (iAgentiteStream.count() > 0) {
//                    g.setColor(Color.gray);
//                }
//
//
//                g.fillRect(x * CASE_SIZE, y * CASE_SIZE, CASE_SIZE, CASE_SIZE);
//            }
//        }


    }

    public void setStartButton(JButton startButton) {
        this.start = startButton;
    }

    public void addEntite(AbstractEntite entite) {
    }
}
