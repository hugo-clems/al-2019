package interfaces;

import java.util.List;

import domaine.Configuration;

public interface IPersistence {
		
	public boolean trouverTous();
	public Configuration trouverParID(String id);
	public boolean sauvegarder(List<Configuration> configurationAPersister);
	public boolean supprimerTout();

}
