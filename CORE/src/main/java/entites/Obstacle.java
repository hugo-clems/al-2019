package entites;

import plateau.IEntitePlateau;

public class Obstacle extends AbstractEntitePassive {

    /**
     * Constructeur par défaut.
     * @param nom nom de l'obtacle
     * @param plateau plateau auquel appartient l'obstacle
     */
    public Obstacle(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
