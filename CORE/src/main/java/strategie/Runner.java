package strategie;

import agent.AbstractAgent;

import java.util.ArrayList;
import java.util.List;

public class Runner implements Runnable {
    /**
     * Etat de l'ordonnanceur.
     * true si l'ordonnanceur tourne, false sinon
     */
    private boolean isRunning = true;
    private final int DUREE_TOUR = 666;

    public Runner(List<AbstractAgent> listeAgents) {
        this.isRunning = isRunning;
        this.listeAgents = listeAgents;
    }

    /**
     * La liste des agents à ordonnancer.
     */
    private List<AbstractAgent> listeAgents;

    @Override
    public void run() {
        this.isRunning = true;
        System.out.println("start");
        while (this.isRunning) {
            // On attends 666ms pour que les tours ne soient pas instantanés
            for (AbstractAgent agent : listeAgents) {
                agent.actionTour();     // Effectue l'action du tour
                // TODO envoie info fin de tour au 2D
            }
            try {
                Thread.sleep(this.DUREE_TOUR);
                System.out.println("sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public void suspend() {

    }
}
