package entites;

public class Pheromone extends AbstractEntiteActive {

    private int tauxPheromone;

    public Pheromone(int tauxPheromone){
        this.tauxPheromone = tauxPheromone;
    }

    public int getTauxPheromone() {
        return tauxPheromone;
    }

    public void setTauxPheromone(int tauxPheromone) {
        this.tauxPheromone = tauxPheromone;
    }

}
