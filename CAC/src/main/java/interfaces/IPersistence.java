package interfaces;

import java.util.ArrayList;

import domaine.Configuration;

public interface IPersistence {
		
	public boolean trouverTous();
	public Configuration trouverParID(String id);
	public boolean sauvegarder(ArrayList<Configuration> configurationAPersister);
	public boolean supprimerTout();

}
