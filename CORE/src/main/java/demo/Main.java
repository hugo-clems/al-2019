package demo;

import common.Direction;
import entites.Obstacle;
import plateau.Application;
import plateau.IAgentite;
import plateau.Position;

import java.awt.*;
import java.util.stream.Stream;

public class Main {

    public static void main(final String[] args) {
        //Instance de Application
        Application application = new Application("test", 20, 20);

        //region Initialisation du plateau
        application.placerAgentite(new Position(17, 6), new Obstacle("testObstacle"));
        application.placerAgentite(new Position(10, 1), new AgentDemo(application.getIAgentPlateau()));

        AgentDemo agent2 = new AgentDemo(application.getIAgentPlateau());
        agent2.setDirection(Direction.E);
        application.placerAgentite(new Position(6, 6), agent2);

        AgentDemo agent3 = new AgentDemo(application.getIAgentPlateau());
        agent3.setDirection(Direction.SE);
        application.placerAgentite(new Position(1, 1), agent3);

        AgentDemo agent4 = new AgentDemo(application.getIAgentPlateau());
        agent4.setDirection(Direction.NE);
        application.placerAgentite(new Position(3, 3), agent4);

        application.placerAgentite(new Position(3, 3), new EntiteActiveDemo("activeTest"));

        application.placerAgentite(new Position(10, 10), new EntitePasiveDemo("passiveTest"));
        //endregion

        //region Personalisation de l'affichage
        application.setCasePaint((caseToPaint, graphics, positionX, positionY, sizeMax) -> {
            Stream<IAgentite> iAgentiteStream = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof AgentDemo);
            if (iAgentiteStream.count() > 0) {
                graphics.setColor(Color.GREEN);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
            }
            iAgentiteStream = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof EntitePasiveDemo);
            if (iAgentiteStream.count() > 0) {
                graphics.setColor(Color.RED);
                graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
            }
        });
        //endregion

        // Lancement de l'interface 2D
        application.run();
    }
}
