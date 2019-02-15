package entites;

import plateau.IEntitePlateau;

public class Obstacle extends AbstractEntitePassive {

    /**
     * Constructeur par défaut.
     * @param nom
     * @param plateau
     */
    public Obstacle(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
