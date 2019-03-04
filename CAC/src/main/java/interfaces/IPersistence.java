package interfaces;

import java.util.ArrayList;

import domaine.Configuration;

public interface IPersistence {
		
	public ArrayList<Configuration> trouverTous();
	
	/**
	 * Retrouve un élément dans la bdd à partir de son id
	 * @param id : L'id de l'élément à retrouver
	 * @return l'élément s'il existe, null sinon
	 */
	public Configuration trouverParID(String id);
	
	
	public boolean sauvegarder(ArrayList<Configuration> configurationAPersister);
	public boolean supprimerTout();

}
