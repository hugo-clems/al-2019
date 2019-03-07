package impl;


import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import interfaces.IRecommandation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SystemeRecommandation  {

   /* @Override
    public int calculerValeurInteret(Connexion connexion) {
        return 100 * connexion.getNbApprobation() / connexion.getNbOccurence();
    }

    @Override
    public List<Configuration> creerConfigurations(List<Connexion> connexions) {
        List<Configuration> configurations = new ArrayList<>();

        Map<Port, List<Connexion>> table = new HashMap<>();

        connexions.forEach(c -> {
            List<Connexion> liste = table.get(c.getFourni());
            if (liste == null || calculerValeurInteret(c) > calculerValeurInteret(liste.get(0))) {
                liste = new ArrayList<>();
                liste.add(c);
            } else if (calculerValeurInteret(c) == calculerValeurInteret(liste.get(0))) {
                liste.add(c);
            }
            table.put(c.getFourni(), liste);
        });

        for (Port port : table.keySet()) {

        }

        return configurations;
    }
    */

}