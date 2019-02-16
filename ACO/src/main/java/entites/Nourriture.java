package entites;

import plateau.Plateau;

public class Nourriture extends  AbstractEntitePassive {

    private int quantite;

    public Nourriture(int quantite , Plateau plateau, String nom){
        super(nom,plateau);
        this.quantite = quantite;
    }

    public int getQuantite(){
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
