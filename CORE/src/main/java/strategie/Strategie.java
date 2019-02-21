package strategie;

import agent.AbstractAgent;

import java.util.ArrayList;
import java.util.List;

public class Strategie implements IStrategie {

    /**
     * La liste des agents à ordonnancer.
     */
    private List<AbstractAgent> listeAgents = new ArrayList<>();
    private boolean isRunning = false;

    @Override
    public void lancer() {
        this.isRunning = true;
        while (this.isRunning) {
            // On attends 666ms pour que les tours ne soient pas instantanés
            for (AbstractAgent agent : listeAgents) {
                try {
                    Thread.sleep(666);
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
