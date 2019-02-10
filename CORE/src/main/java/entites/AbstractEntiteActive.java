package entites;

import plateau.IEntitePlateau;

public abstract class AbstractEntiteActive extends AbstractEntite {

    /**
     * Constructeur par défaut.
     * @param nom
     * @param plateau
     */
    public AbstractEntiteActive(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
