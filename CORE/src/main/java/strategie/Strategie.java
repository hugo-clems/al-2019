package strategie;

import agent.AbstractAgent;

import java.util.ArrayList;
import java.util.List;

public class Strategie implements IStrategie {

    /**
     * La durée d'un tour.
     */
    private final int DUREE_TOUR = 666;

    /**
     * La liste des agents à ordonnancer.
     */
    private List<AbstractAgent> listeAgents = new ArrayList<>();

    /**
     * Etat de l'ordonnanceur.
     * true si l'ordonnanceur tourne, false sinon
     */
    private boolean isRunning = false;

    @Override
    public void lancer() {
        this.isRunning = true;
        while (this.isRunning) {
            // On attends 666ms pour que les tours ne soient pas instantanés
            for (AbstractAgent agent : listeAgents) {
                try {
                    Thread.sleep(this.DUREE_TOUR);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                agent.actionTour();     // Effectue l'action du tour
                // TODO envoie info fin de tour au 2D
            }
        }
    }

    @Override
    public void arreter() {
        this.isRunning = false;
    }

    @Override
    public void ajouterAgent(AbstractAgent agent) {
        this.listeAgents.add(agent);
    }

    @Override
    public void supprimerAgent(AbstractAgent agent) {
        this.listeAgents.remove(agent);
    }

}
