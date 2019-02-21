package strategie;

import agent.AbstractAgent;

public interface IStrategie {

    void lancer();

    void arreter();

    void ajouterAgent(AbstractAgent agent);

    void supprimerAgent(AbstractAgent agent);

}
