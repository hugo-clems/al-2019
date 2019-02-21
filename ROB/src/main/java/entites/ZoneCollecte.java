package entites;

import plateau.Case;
import plateau.IEntitePlateau;
import plateau.Position;

import java.util.List;

public class ZoneCollecte extends Zone {

    /**
     * Constructeur par défaut.
     *
     * @param nom        nom de l'agent
     * @param plateau    plateau auquel appartient l'agent
     * @param listePositions liste des positions de la zone de collecte
     */
    public ZoneCollecte(String nom, IEntitePlateau plateau, List<Position> listePositions) {
        super(nom, plateau, listePositions);
    }
}
