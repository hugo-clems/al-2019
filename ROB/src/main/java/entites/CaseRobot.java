package entites;

import plateau.Case;
import plateau.Position;

public class CaseRobot extends Case {

    private int poids;

    /**
     * Constructeur par défaut.
     *
     * @param position position de la case
     */
    public CaseRobot(Position position, int poids) {
        super(position);
        this.poids = poids;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

}
