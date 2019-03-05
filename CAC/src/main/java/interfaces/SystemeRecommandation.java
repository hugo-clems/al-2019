package interfaces;

import domaine.Configuration;
import domaine.Connexion;
import domaine.Recommandable;

import java.util.HashMap;
import java.util.List;

public interface SystemeRecommandation {

    /**
     * Calcule la valeur d'intérêt pour un Recommandable.
     *
     * @param recommandable
     * @return valeur d'intérêt d'un Recommandable exprimée en pourcentage
     */
    int calculValeurInteret(Recommandable recommandable);
    List<Configuration> choisirConfiguration(HashMap<Connexion,Integer> connexionsNotees);

}
