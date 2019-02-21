package strategie;

import agent.AbstractAgent;

public interface IStrategie {

    /**
     * Lance l'ordonnancement.
     */
    void lancer();

    /**
     * Arrête l'ordonnancement.
     */
    void arreter();

    /**
     * Ajoute un agent à l'ordonnanceur.
     * @param agent l'agent à ajouter
     */
    void ajouterAgent(AbstractAgent agent);

    /**
     * Supprime un agent de l'ordonnanceur.
     * @param agent l'agent à supprimer
     */
    void supprimerAgent(AbstractAgent agent);

}
