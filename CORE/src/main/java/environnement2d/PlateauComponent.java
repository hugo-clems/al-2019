package environnement2d;

import agent.AbstractAgent;
import entites.Obstacle;
import plateau.Case;
import plateau.IAgentite;
import plateau.Plateau;
import plateau.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PlateauComponent extends JComponent {

    //region Attributes
    private int caseSize = 16;

    private Plateau plateau;

    private CasePaint casePaint = (caseToPaint, g, x, y, sizeMax) -> {
    };
    //endregion

    //region Constructors
    public PlateauComponent(Plateau plateau) {
        this.plateau = plateau;
    }

    public PlateauComponent(Plateau plateau, CasePaint casePaint) {
        this.plateau = plateau;
        this.casePaint = casePaint;
    }
    //endregion

    //region Getters
    public List<AbstractAgent> getAgents() {
        List<AbstractAgent> agents = new ArrayList<>();
        for (IAgentite agentite : plateau.getListeAgentites().keySet()) {
            if (agentite instanceof AbstractAgent) {
                agents.add((AbstractAgent) agentite);
            }
        }
        return agents;
    }
    //endregion

    //region Setters
    public void setCasePaint(CasePaint casePaint) {
        this.casePaint = casePaint;
    }

    public void setCaseSize(int size) {
        if (size > 0) {
            this.caseSize = size;
        }
    }
    //endregion

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
                        g.fillRect(x * caseSize, y * caseSize, caseSize, caseSize);
                    } else {
                        this.casePaint.onDraw(c, g, x * caseSize, y * caseSize, this.caseSize);
                    }
                }
                g.setColor(Color.black);
                g.drawRect(x * caseSize, y * caseSize, caseSize, caseSize);
            }
        }
    }

    public interface CasePaint {
        void onDraw(Case caseToPaint, Graphics g, int positionX, int positionY, int sizeMax);
    }
}