package entites;

import plateau.Case;
import plateau.IEntitePlateau;

import java.util.List;

public class ZoneDepot extends Zone {

    /**
     * Constructeur par défaut.
     *
     * @param nom        nom de l'agent
     * @param plateau    plateau auquel appartient l'agent
     * @param listeCases
     */
    public ZoneDepot(String nom, IEntitePlateau plateau, List<Case> listeCases) {
        super(nom, plateau, listeCases);
    }

}
