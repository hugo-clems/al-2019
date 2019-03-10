package interfaces;

import domaine.Configuration;
import domaine.Connexion;

import java.util.List;
import java.util.Set;

public interface IRecommandation {

    /**
     * Calcule la valeur d'intérêt pour une connexion.
     *
     * @param connexion La connexion dont la valeur d'intérêt doit être calculée
     * @return Valeur d'intérêt d'une connexion exprimée en pourcentage
     */
    int calculerValeurInteret(Connexion connexion);

    /**
     * Utilise les connexions en entrée pour créer toutes les configurations possibles
     *
     * @param connexions Liste de connexions
     * @return L'ensemble des meilleures configurations
     */
    Set<Configuration> creerConfigurations(List<Connexion> connexions);

}
