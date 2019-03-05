package entites;

import plateau.IEntitePlateau;
import plateau.Plateau;

public class Pheromone extends AbstractEntiteActive {

    private int tauxPheromone;

    public Pheromone(int tauxPheromone,String nom, IEntitePlateau iEntitePlateau){
        super(nom, iEntitePlateau);
        this.tauxPheromone = tauxPheromone;
    }

    public int getTauxPheromone() {
        return tauxPheromone;
    }

    public void setTauxPheromone(int tauxPheromone) {
        this.tauxPheromone = tauxPheromone;
    }

    @Override
    public void ActionTour() {
        this.tauxPheromone--;

        if (tauxPheromone ==0){
            this.seSuicider();
        }
    }
}
