package strategie;

import agent.AbstractAgent;

import java.util.ArrayList;
import java.util.List;

public class Strategie implements IStrategie {
    private Runner runner;
    private Thread t;

    /**
     * La liste des agents à ordonnancer.
     */
    private List<AbstractAgent> listeAgents = new ArrayList<>();


    @Override
    public void lancer() {
        this.runner = new Runner(this.listeAgents);
        this.t = new Thread(this.runner);
        this.t.start();
    }

    @Override
    public void arreter() {
        this.runner.setRunning(false);
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
