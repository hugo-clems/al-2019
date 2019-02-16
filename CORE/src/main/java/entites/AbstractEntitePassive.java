package entites;

import plateau.IEntitePlateau;

public abstract class AbstractEntitePassive extends AbstractEntite {

    /**
     * Constructeur par défaut.
     * @param nom nom de l'agent
     * @param plateau plateau auquel appartient l'agent
     */
    public AbstractEntitePassive(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }
}
