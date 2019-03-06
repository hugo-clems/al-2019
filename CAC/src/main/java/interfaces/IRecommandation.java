package interfaces;


import domaine.Configuration;
import domaine.Connexion;

import java.util.List;

public interface IRecommandation {

    /**
     * Calcule la valeur d'intérêt pour une Connexion.
     *
     * @param connexion La Connexion dont sa valeur d'intérêt doit être calculée
     * @return Valeur d'intérêt d'une Connexion exprimée en pourcentage
     */
    int calculerValeurInteret(Connexion connexion);

    /**
     * Utilise les Connexions en entrée pour créer des Configurations en fonction de
     *
     * @param connexions Liste de connexions
     * @return La liste de Configurations triée par valeur d'intérêt
     */
    List<Configuration> creerConfigurations(List<Connexion> connexions);

}