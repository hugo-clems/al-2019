package entites;

import plateau.IEntitePlateau;

public abstract class AbstractEntiteActive extends AbstractEntite {

    public AbstractEntiteActive(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
