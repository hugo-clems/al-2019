package entites;

import plateau.IEntitePlateau;

public abstract class AbstractEntitePassive extends AbstractEntite {

    /**
     * Constructeur par défaut.
     * @param nom
     * @param plateau
     */
    public AbstractEntitePassive(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
