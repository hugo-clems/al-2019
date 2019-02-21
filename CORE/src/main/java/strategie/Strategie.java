package strategie;

import agent.AbstractAgent;

import java.util.List;

public class Strategie implements IStrategie {

    private List<AbstractAgent> listeAgents;
    private boolean isRunning = false;

    @Override
    public void lancer() {
        this.isRunning = true;
        while (this.isRunning) {
            for (AbstractAgent agent : listeAgents) {
                try {
                    Thread.sleep(666);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                agent.actionTour();
                // TODO envoie au 2D
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
