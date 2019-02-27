package strategie;

import agent.AbstractAgent;

import java.util.ArrayList;
import java.util.List;

public class Strategie implements IStrategie {
    private Runner runner;
    private Thread t = null;

    /**
     * La liste des agents à ordonnancer.
     */
    private List<AbstractAgent> listeAgents = new ArrayList<>();

    public Strategie(List<AbstractAgent> listeAgents) {
        this.listeAgents = listeAgents;
    }

    @Override
    public void lancer() {
        if (this.t == null) {
            this.runner = new Runner(this.listeAgents);
            this.t = new Thread(this.runner);
            this.t.start();
        } else {
            this.t.resume();
        }

    }

    @Override
    public void arreter() {
        this.runner.setRunning(false);
    }

    @Override
    public void pause() {
        this.t.suspend();
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
