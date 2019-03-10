package systemeRecommandation;

import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import interfaces.IRecommandation;

import java.util.*;
import java.util.stream.Collectors;


public class SystemeRecommandation implements IRecommandation {

    /**
     * Fonction récursive calculant le produit cartésien d'un ensemble de listes de connexions
     *
     * @param iterateur Un itérateur sur un ensemble de listes
     * @return Un ensemble d'ensembles de connexions représentant les combinaisons possibles
     */
    private static Set<Set<Connexion>> produitCartesien(Iterator<List<Connexion>> iterateur) {
        Set<Set<Connexion>> combinaisons = new HashSet<>();

        if (!iterateur.hasNext()) {
            combinaisons.add(new HashSet<>());
            return combinaisons;
        }

        List<Connexion> listeCourante = iterateur.next();
        Set<Set<Connexion>> resultat = produitCartesien(iterateur);
        for (Connexion connexion : listeCourante) {
            for (Set<Connexion> connexions : resultat) {
                Set<Connexion> t = new HashSet<>();
                t.add(connexion);
                t.addAll(connexions);
                combinaisons.add(t);
            }
        }

        return combinaisons;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculerValeurInteret(Connexion connexion) {
        if (connexion.getNbOccurence() == 0)
            return 0;
        return 100 * connexion.getNbApprobation() / connexion.getNbOccurence();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Configuration> creerConfigurations(List<Connexion> connexions) {
        Set<Configuration> configurations = new HashSet<>();
        Map<String, List<Connexion>> mapMeilleuresConnexionsPossiblesParPort = new HashMap<>();

        if (connexions.size() < 1) {
            return configurations;
        }

        for (Connexion connexion : connexions) {
            Port portFourni = connexion.getFourni();
            List<Connexion> listeMeilleuresConnexionsPossibles = mapMeilleuresConnexionsPossiblesParPort.get(portFourni.getService());

            if (listeMeilleuresConnexionsPossibles == null || calculerValeurInteret(connexion) > calculerValeurInteret(listeMeilleuresConnexionsPossibles.get(0))) {
                listeMeilleuresConnexionsPossibles = new ArrayList<>();
                listeMeilleuresConnexionsPossibles.add(connexion);
            } else if (calculerValeurInteret(connexion) == calculerValeurInteret(listeMeilleuresConnexionsPossibles.get(0))) {
                listeMeilleuresConnexionsPossibles.add(connexion);
            } else {
                continue;
            }

            mapMeilleuresConnexionsPossiblesParPort.put(portFourni.getService(), listeMeilleuresConnexionsPossibles);
        }

        for (Set<Connexion> ensemble : produitCartesien(mapMeilleuresConnexionsPossiblesParPort.values().iterator())) {
            Configuration configuration = new Configuration();
            configuration.setConnexions(ensemble);

            configurations.add(configuration);
        }

        return configurations;
    }

}
