package entites;

import plateau.IEntitePlateau;

public abstract class AbstractEntiteActive extends AbstractEntite {

    /**
     * Constructeur par défaut.
     * @param nom nom de l'agent
     * @param plateau plateau auquel appartient l'agent
     */
    public AbstractEntiteActive(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
