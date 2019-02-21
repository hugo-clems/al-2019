package impl;

import domaine.Recommandable;
import interfaces.SystemeRecommandation;

public class SystemeRecommandationSimple implements SystemeRecommandation {

    @Override
    public int calculValeurInteret(Recommandable recommandable) {
        return 100 * recommandable.getNbApprobation() / recommandable.getNbOccurence();
    }

}
