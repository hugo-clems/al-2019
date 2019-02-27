package ihm;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;

import java.util.List;

public interface IVue {

    public void afficherConfiguration(Configuration c);

    public List<Composant> entrerConfiguration();

    public void afficherConnexion(Connexion c); 

}
