package strategie;

import agent.AbstractAgent;

import java.util.ArrayList;
import java.util.List;

public class Strategie implements IStrategie {
    /**
    * ordonnanceur
    */
    private Runner runner;

    /**
     * thread
     */
    private Thread t = null;

    private Runner.TourListener tourListener;

    /**
     * La liste des agents à ordonnancer.
     */
    private List<AbstractAgent> listeAgents = new ArrayList<>();

    /**
     * Constructor.
     */
    public Strategie(List<AbstractAgent> listeAgents) {
        this.listeAgents = listeAgents;
    }

    /**
     * Lancement de l'ordonnanceur
     */
    @Override
    public void lancer() {
        if (this.t == null) {
            this.runner = new Runner(this.listeAgents, this.tourListener);
            this.t = new Thread(this.runner);
            this.t.start();
        } else {
            this.t.resume();
        }

    }

    /**
     * Arreter l'ordonnanceur
     */
    @Override
    public void arreter() {
        this.runner.setRunning(false);
        this.t = null;
    }

    /**
     * Mettre en pause l'ordonnanceur
     */
    @Override
    public void pause() {
        this.t.suspend();
    }

    /**
     * Ajouter un agent à la liste des agents
     * @param agent l'agent à ajouter
     */
    @Override
    public void ajouterAgent(AbstractAgent agent) {
        this.listeAgents.add(agent);
    }

    /**
     * Supprimer un agent de la liste des agents
     * @param agent l'agent à supprimer
     */
    @Override
    public void supprimerAgent(AbstractAgent agent) {
        this.listeAgents.remove(agent);
    }

    /**
     * Set le listener de fin de tour
     * @param tourListener le listener
     */
    public void setTourListener(Runner.TourListener tourListener) {
        this.tourListener = tourListener;
    }
}
