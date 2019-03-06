package interfaces;


public interface IConnexion {

    /**
     * Cette methode envoie la liste des connexion possible au noyeu ! Il faut d'abord broadcast tout les agents */

    public void envoyerConnexionPossible();

    /**
     * Fonction pour diffuser la liste des port Requis au autre agent
     */
    public void broadcast();

}