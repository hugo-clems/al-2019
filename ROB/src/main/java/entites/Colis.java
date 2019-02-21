package entites;

import plateau.IEntitePlateau;

public class Colis extends AbstractEntitePassive {

    /**
     * Constructeur par défaut.
     *
     * @param nom     nom de l'agent
     * @param plateau plateau auquel appartient l'agent
     */
    public Colis(String nom, IEntitePlateau plateau) {
        super(nom, plateau);
    }

}
