package entites;

public class Nid extends AbstractEntitePassive {

    private int nbFourmis;

    public Nid(int nbFourmis){
        this.nbFourmis = nbFourmis;
    }

    public int getNbFourmis() {
        return nbFourmis;
    }

    public void setNbFourmis(int nbFourmis) {
        this.nbFourmis = nbFourmis;
    }
}
