package interfaces;

import domaine.Recommandable;

public interface SystemeRecommandation {

    /**
     * Calcule la valeur d'intérêt pour un Recommandable.
     *
     * @param recommandable
     * @return valeur d'intérêt d'un Recommandable exprimée en pourcentage
     */
    int calculValeurInteret(Recommandable recommandable);

}
