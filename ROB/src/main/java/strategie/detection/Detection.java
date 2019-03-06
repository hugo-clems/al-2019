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
            addCaseRobotToCarteMemoire(robot.getCarteMemoire(), caseRobot);
        }
    }

    private static CaseRobot convertCaseToCaseRobot(Case caseAdjacente) {
        List<IAgentite> listeAgentsEntites = caseAdjacente.getAgentites();
        int poids = 0;
        boolean obstacle = false;
        boolean robot = false;
        boolean depot = false;
        boolean collecte = false;
        boolean colis = false;

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
            } else if (agentite instanceof Colis) {
                colis = true;
            }

        }

        return new CaseRobot(caseAdjacente.getPosition(), poids, obstacle, robot, depot, collecte, colis);
    }

    private static void addCaseRobotToCarteMemoire(List<List<CaseRobot>> carteMemoire, CaseRobot caseRobot) {
        // Quand on get et set, penser -1

        Position position = caseRobot.getPosition();

        if (carteMemoire == null) {
            carteMemoire = new ArrayList<>();
        }

        while (position.getY() > carteMemoire.size()) {
            carteMemoire.add(new ArrayList<>());
        }

        if (carteMemoire.get(position.getY() - 1) == null) {
            carteMemoire.set(position.getY() - 1, new ArrayList<>());
        } else if (position.getX() > carteMemoire.get(position.getY() - 1).size()) {
            // while ajouter des null pour atteindre la case voulue
            // carteMemoire.get(position.getY() - 1).ensureCapacity(position.getX());
        }

        carteMemoire.get(position.getY() - 1).set(position.getX() - 1, caseRobot);
    }

    public static void main (String[] args){
        CaseRobot caseRobot = new CaseRobot(new Position(5, 7), 0, false, false, false, false, false);
        addCaseRobotToCarteMemoire(null, caseRobot);
        // afficher la carte pour verifier
    }

}
