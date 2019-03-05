package interfaces;

import java.util.ArrayList;

import domaine.Configuration;
import domaine.Connexion;

public interface IPersistence {
	
	/**
	 * Retourne toutes les configurations qui ont été persistées
	 * @return La liste des configuration dans la base de données
	 */
	public ArrayList<Configuration> trouverTous();
	
	/**
	 * Retrouve un élément dans la bdd à partir de son id
	 * @param id : L'id de l'élément à retrouver
	 * @return l'élément s'il existe, null sinon
	 */
	public Configuration trouverParID(String id);
	
	/**
	 * Persiste une configuration en l'écrivant dans le fichier
	 * @param configurationAPersister
	 * @return true si la sauvegarde s'est bien passé
	 */
	public boolean sauvegarder(ArrayList<Configuration> configurationAPersister);
	
	/**
	 * Vide la base de données
	 * @return true si la suppression s'est bien passé
	 */
	public boolean supprimerTout();
	
	public ArrayList<Connexion> trouverConnexion(ArrayList<Connexion> array);

	boolean sauvegarderConnexion(ArrayList<Connexion> connexionAPersister);

}
