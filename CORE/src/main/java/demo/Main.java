package demo;

import common.Direction;
import entites.Obstacle;
import plateau.Application;
import plateau.IAgentite;
import plateau.Position;

import java.awt.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {

    public static void main(final String[] args) {
        //Instance de Application
        Application application = new Application("test", 20, 20);
        application.setCaseSize(30);

        //region Initialisation du plateau
        application.placerAgentite(new Position(17, 6), new Obstacle("testObstacle"));
        application.placerAgentite(new Position(10, 1), new AgentDemo(application.getIAgentPlateau()));

        AgentDemo agent2 = new AgentDemo(application.getIAgentPlateau());
        agent2.setDirection(Direction.E);
        application.placerAgentite(new Position(6, 6), agent2);

        AgentDemo agent3 = new AgentDemo(application.getIAgentPlateau());
        agent3.setDirection(Direction.SE);
        application.placerAgentite(new Position(1, 1), agent3);

        application.placerAgentite(new Position(3, 3), new EntiteActiveDemo("activeTest", 0));
        application.placerAgentite(new Position(8, 3), new EntiteActiveDemo("activeTest", 2));

        application.placerAgentite(new Position(10, 10), new EntitePasiveDemo("passiveTest"));
        //endregion

        //region Personalisation de l'affichage
        application.setCasePaint((caseToPaint, graphics, positionX, positionY, sizeMax) -> {
            Supplier<Stream<IAgentite>> iAgentiteStream = () -> caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof AgentDemo);
            if (iAgentiteStream.get().count() > 0) {
                graphics.setColor(Color.GREEN);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
                iAgentiteStream.get().forEach(iAgentite -> {
                    if (((AgentDemo) iAgentite).getEntitePortee() != null) {
                        graphics.setColor(Color.GRAY);
                        graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
                    }
                });
            }
            iAgentiteStream = () -> caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof EntitePasiveDemo);
            if (iAgentiteStream.get().count() > 0) {
                graphics.setColor(Color.RED);
                graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
            }
            iAgentiteStream = () -> caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof EntiteActiveDemo);
            if (iAgentiteStream.get().count() > 0) {
                iAgentiteStream.get().forEach(iAgentite -> {
                    Color color;
                    switch (((EntiteActiveDemo) iAgentite).getValue()) {
                        case 0:
                            graphics.setColor(Color.BLUE);
                            break;
                        case 1:
                            graphics.setColor(Color.CYAN);
                            break;
                        default:
                            graphics.setColor(Color.black);
                            break;
                    }
                    graphics.fillRoundRect(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6, 5, 5);
                });
            }
        });
        //endregion

        // Lancement de l'interface 2D
        application.run();
    }
}
