package interfaces;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;

import java.util.HashMap;
import java.util.List;

public interface SystemeRecommandation {

    /**
     * Calcule la valeur d'intérêt pour un Recommandable.
     *
     * @param composant
     * @return valeur d'intérêt d'un Recommandable exprimée en pourcentage
     */
    int calculValeurInteret(Composant composant);
    List<Configuration> choisirConfiguration(HashMap<Connexion,Integer> connexionsNotees);

}
