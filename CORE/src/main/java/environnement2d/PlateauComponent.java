package environnement2d;

import entites.Obstacle;
import plateau.Case;
import plateau.IAgentite;
import plateau.Plateau;
import plateau.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.stream.Stream;

public class PlateauComponent extends JComponent {
    private final int CASE_SIZE = 16;

    private Plateau plateau;

    public PlateauComponent(Plateau plateau) {
        this.plateau = plateau;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Map<Position, Case> positionCaseMap = this.plateau.getCases();

        for (int x = 0; x < this.plateau.getColonne(); x++) {
            for (int y = 0; y < this.plateau.getLigne(); y++) {
                Case c = positionCaseMap.get(new Position(x, y));
                if (c.getAgentites().size() != 0) {
                    Stream<IAgentite> iAgentiteStream = c.getAgentites().stream().filter(aCase -> aCase instanceof Obstacle);
                    if (iAgentiteStream.count() > 0) {
                        g.setColor(Color.darkGray);
                        g.fillRect(x * CASE_SIZE, y * CASE_SIZE, CASE_SIZE, CASE_SIZE);
                    }
                }
                g.setColor(Color.black);
                g.drawRect(x * CASE_SIZE, y * CASE_SIZE, CASE_SIZE, CASE_SIZE);
            }
        }
    }
}
