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
        CaseRobot caseRobot;
        for (Case caseAdjacente : casesAdjacentes.values()) {
            caseRobot = convertCaseToCaseRobot(caseAdjacente);
            addCaseRobotToCarteMemoire(robot, caseRobot);
        }
    }

    private static CaseRobot convertCaseToCaseRobot(Case caseAdjacente) {
        List<IAgentite> listeAgentsEntites = caseAdjacente.getAgentites();
        int poids = 0;
        boolean obstacle = false;
        boolean robot = false;
        boolean depot = false;
        boolean collecte = false;

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

        while (position.getY() - 1 >= carteMemoire.size()) {
            carteMemoire.add(new ArrayList<>());
        }

        if (carteMemoire.get(position.getY() - 1) == null) {
            carteMemoire.set(position.getY() - 1, new ArrayList<>());
        }

        while (position.getX() > carteMemoire.get(position.getY() - 1).size()) {
            carteMemoire.get(position.getY() - 1).add(null);
        }

        carteMemoire.get(position.getY() - 1).set(position.getX() - 1, caseRobot);
        robot.setCarteMemoire(carteMemoire);
    }
}
