package entites;

import plateau.IEntitePlateau;
import plateau.Position;

import java.util.List;

public class Zone extends AbstractEntitePassive {

    private List<Position> listePositions;

    /**
     * Constructeur par défaut.
     *
     * @param nom     nom de l'agent
     * @param plateau plateau auquel appartient l'agent
     */
    public Zone(String nom, IEntitePlateau plateau, List<Position> listePositions) {
        super(nom, plateau);
        this.listePositions = listePositions;
    }

    public List<Position> getListePositions() {
        return listePositions;
    }

    public void setListePositions(List<Position> listePositions) {
        this.listePositions = listePositions;
    }

}
