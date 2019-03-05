package entites;

import plateau.IEntitePlateau;

public abstract class AbstractEntiteActive extends AbstractEntite {

    private IEntitePlateau iEntitePlateau;

    /**
     * Constructeur par défaut.
     * @param nom nom de l'entite active
     */
    public AbstractEntiteActive(String nom, IEntitePlateau iEntitePlateau) {
        super(nom);
        this.iEntitePlateau = iEntitePlateau;
    }

    protected boolean seSuicider() {
        return this.iEntitePlateau.suiciderEntite(this);
    }

    public void ActionTour() {
        // todo walid
    }
}
