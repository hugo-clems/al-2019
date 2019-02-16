package entites;

import plateau.Plateau;

public class Nid extends AbstractEntitePassive {

    private int nbFourmis;

    public Nid(Plateau plateau, String nom){
        super(nom,plateau);
        this.nbFourmis=5;
    }

    public int getNbFourmis() {
        return nbFourmis;
    }

    public void setNbFourmis(int nbFourmis) {
        this.nbFourmis = nbFourmis;
    }
}
