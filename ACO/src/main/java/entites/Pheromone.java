package entites;

import plateau.Plateau;

public class Pheromone extends AbstractEntiteActive {

    private int tauxPheromone;

    public Pheromone(int tauxPheromone, Plateau plateau, String nom){
        super(nom,plateau);
        this.tauxPheromone = tauxPheromone;
    }

    public int getTauxPheromone() {
        return tauxPheromone;
    }

    public void setTauxPheromone(int tauxPheromone) {
        this.tauxPheromone = tauxPheromone;
    }

}
