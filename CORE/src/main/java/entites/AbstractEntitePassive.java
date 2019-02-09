package entites;

import plateau.IEntitePlateau;

public abstract class AbstractEntitePassive extends AbstractEntite {

    public AbstractEntitePassive(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
