package interfaces;

import java.util.ArrayList;

import domaine.Connexion;

public interface IPersistence {
	
	/**
	 * Vide la base de données
	 * @return true si la suppression s'est bien passé
	 */
	public boolean supprimerTout();
	
	/**
	 * Prend une liste de connexions et y rajoute les notes persistées
	 * @param La liste des connexions desquelles il faut rajouter les notes
	 * @return la nouvelle liste avec les notes en plus
	 */
	public ArrayList<Connexion> trouverConnexion(ArrayList<Connexion> array);
	
	/**
	 * Persiste une connexion en l'écrivant dans le fichier
	 * @param connexionAPersister
	 * @return true si la sauvegarde s'est bien passé
	 */
	boolean sauvegarderConnexion(ArrayList<Connexion> connexionAPersister);

	public ArrayList<Connexion> trouverTous();

}
