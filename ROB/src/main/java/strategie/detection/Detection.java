package strategie.detection;

import common.Direction;
import entites.*;
import plateau.Case;
import plateau.IAgentite;
import plateau.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Detection {

    public static void detecterVoisinage(Robot robot) {
        Map<Direction, Case> casesAdjacentes = robot.detecter();
        for (Case caseAdjacente : casesAdjacentes.values()) {
            addCaseRobotToCarteMemoire(robot, convertCaseToCaseRobot(caseAdjacente));
        }
    }

    private static CaseRobot convertCaseToCaseRobot(Case caseAdjacente) {
        List<IAgentite> listeAgentsEntites = caseAdjacente.getAgentites();
        boolean obstacle = false, robot = false, depot = false, collecte = false;
        int poids = 0;

        for (IAgentite agentite : listeAgentsEntites) {

            if (agentite instanceof Obstacle) {
                poids = 1;
                obstacle = true;
            } else if (agentite instanceof Robot) {
                poids = 1;
                robot = true;
            } else if (agentite instanceof ZoneCollecte) {
                collecte = true;
            } else if (agentite instanceof ZoneDepot) {
                depot = true;
            }

        }

        return new CaseRobot(caseAdjacente.getPosition(), poids, obstacle, robot, depot, collecte);
    }

    private static void addCaseRobotToCarteMemoire(Robot robot, CaseRobot caseRobot) {
        Position position = caseRobot.getPosition();
        List<List<CaseRobot>> carteMemoire = robot.getCarteMemoire();

        if (carteMemoire == null) {
            carteMemoire = new ArrayList<>();
        }

        int predX=position.getX() - 1,
                predY=position.getY() - 1;
        while (predY >= carteMemoire.size()) {
            carteMemoire.add(new ArrayList<>());
        }

        if (predY >= 0 && predX >= 0) {
            if (carteMemoire.get(predY) == null) {
                carteMemoire.set(predY, new ArrayList<>());
            }

            while (position.getX() > carteMemoire.get(predY).size()) {
                carteMemoire.get(predY).add(null);
            }

            carteMemoire.get(predY).set(predX, caseRobot);
        }
        robot.setCarteMemoire(carteMemoire);
    }
}
