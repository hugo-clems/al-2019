package entites;

import plateau.Case;
import plateau.IEntitePlateau;

import java.util.List;

public class Zone extends AbstractEntitePassive {

    private List<Case> listeCases;

    /**
     * Constructeur par défaut.
     *
     * @param nom     nom de l'agent
     * @param plateau plateau auquel appartient l'agent
     */
    public Zone(String nom, IEntitePlateau plateau, List<Case> listeCases) {
        super(nom, plateau);
    }

    public List<Case> getListeCases() {
        return listeCases;
    }

    public void setListeCases(List<Case> listeCases) {
        this.listeCases = listeCases;
    }

}
