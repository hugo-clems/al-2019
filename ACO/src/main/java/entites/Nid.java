package entites;

import plateau.Plateau;

public class Nid extends AbstractEntitePassive {

    private int nbFourmis;

    public Nid(String nom, int nbFourmis){
        super(nom);
        this.nbFourmis=nbFourmis;
    }

    public int getNbFourmis() {
        return nbFourmis;
    }

    public void setNbFourmis(int nbFourmis) {
        this.nbFourmis = nbFourmis;
    }
}
