package environnement2d.graphics;

import entites.AbstractEntite;
import entites.Obstacle;
import environnement2d.swinginterface.MainPanel;
import plateau.Case;
import plateau.IAgentite;
import plateau.Plateau;
import plateau.Position;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

public class PlateauComponent extends JComponent {
    private JButton start;
    private MainPanel panel;

    private final int CASE_SIZE = 8;

    private Plateau plateau;

    public PlateauComponent() {
        this.plateau = new Plateau("plateauTest");
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 80; y++) {
                Case c = new Case(new Position(x, y));
                //Ajout obstacles au bord
                if (x == 0 || x == 99 || y == 0 || y == 79 || (y > 10 && y < 13 && x < 30)) {
                    Obstacle o = new Obstacle("Obstacle" + x + y, plateau);
                    c.getAgentites().add(o);
                    plateau.getListeAgentites().put(o, c);
                }
                if (x == 2 && y == 3 || x == 60 && y == 78) {
                    AbstractEntite agent = new AbstractEntite("agent", plateau) {
                    };
                    plateau.getListeAgentites().put(agent, c);
                    c.getAgentites().add(agent);
                }
                plateau.getCases().add(c);
            }
        }
    }

    // TODO make a real implementation with maybe images, compenents for each type of entity etc
    @Override
    protected void paintComponent(Graphics g) {
        List<Case> cases = this.plateau.getCases();
        for (int i = 0, max = cases.size(); i < max; i++) {
            if (cases.get(i).getAgentites().size() == 0) {
                Position pos = cases.get(i).getPosition();
                int x = pos.getX();
                int y = pos.getY();

                g.setColor(Color.black);
                g.fillRect(x * CASE_SIZE, y * CASE_SIZE, CASE_SIZE, CASE_SIZE);
            } else {
                Position pos = cases.get(i).getPosition();
                int x = pos.getX();
                int y = pos.getY();

                g.setColor(Color.white);

                Stream<IAgentite> iAgentiteStream = cases.get(i).getAgentites().stream().filter(aCase -> aCase instanceof Obstacle);
                if (iAgentiteStream.count() > 0) {
                    g.setColor(Color.gray);
                }


                g.fillRect(x * CASE_SIZE, y * CASE_SIZE, CASE_SIZE, CASE_SIZE);
            }
        }


    }

    public void setStartButton(JButton startButton) {
        this.start = startButton;
    }
}
