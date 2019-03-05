package demo;

import entites.AbstractEntiteActive;
import plateau.IEntitePlateau;

public class EntitéActiveSuicidaireDemo extends AbstractEntiteActive {

    private int tempRestant;

    /**
     * Constructeur par défaut.
     *
     * @param nom            nom de l'entite active
     * @param iEntitePlateau
     */
    public EntitéActiveSuicidaireDemo(String nom, IEntitePlateau iEntitePlateau, int tempsRestant) {
        super(nom, iEntitePlateau);
        this.tempRestant = tempsRestant;
    }

    @Override
    public void ActionTour() {
        super.ActionTour();
        if (tempRestant > 0) {
            this.tempRestant--;
        } else {
            this.seSuicider();
        }
    }
}
