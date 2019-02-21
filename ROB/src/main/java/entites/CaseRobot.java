package entites;

import plateau.Position;

public class CaseRobot {

    private Position position;

    private int poids;

    private boolean obstacle;

    private boolean robot;

    private boolean depot;

    private boolean collecte;

    /**
     * Constructeur de CaseRobot.
     * @param position la position x et y du Robot sur le plateau
     * @param poids le poids de la case
     * @param obstacle la case contient-elle un obstacle ?
     * @param robot la case contient-elle un robot ?
     * @param depot la case est-elle dans une zone de dépôt ?
     * @param collecte la case est-elle dans une zone de collecte ?
     */
    public CaseRobot(Position position, int poids, boolean obstacle, boolean robot, boolean depot, boolean collecte) {
        this.position = position;
        this.poids = poids;
        this.obstacle = obstacle;
        this.robot = robot;
        this.depot = depot;
        this.collecte = collecte;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public boolean isRobot() {
        return robot;
    }

    public void setRobot(boolean robot) {
        this.robot = robot;
    }

    public boolean isDepot() {
        return depot;
    }

    public void setDepot(boolean depot) {
        this.depot = depot;
    }

    public boolean isCollecte() {
        return collecte;
    }

    public void setCollecte(boolean collecte) {
        this.collecte = collecte;
    }

}
