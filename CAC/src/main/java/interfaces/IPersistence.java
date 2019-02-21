package interfaces;

import domaine.Configuration;

public interface IPersistence {
		
	public boolean trouverTous();
	public Configuration trouverParID(String id);
	public boolean sauvegarder();
	public boolean supprimerTout();

}
