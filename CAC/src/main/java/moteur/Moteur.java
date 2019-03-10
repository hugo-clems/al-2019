package moteur;

import domaine.AgentComposant;
import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import ihm.IVue;
import interfaces.IConnexion;
import noyau.Noyau;
import ihm.Eval;
import ihm.VueImpl;
import interfaces.IEval;
import interfaces.IRecommandation;
import persistence.Persistence;
import systemeRecommandation.SystemeRecommandation;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Moteur {

    private Set<Configuration> configurations = new HashSet<>();
    private IRecommandation systemeRecommandation;

    public Moteur(IRecommandation iRecommandation) {
        this.systemeRecommandation = iRecommandation;
    }

    public Set<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<Configuration> configurations) {
        this.configurations = configurations;
    }

    public IRecommandation getSystemeRecommandation() {
        return systemeRecommandation;
    }

    public void setSystemeRecommandation(IRecommandation iRecommandation) {
        this.systemeRecommandation = iRecommandation;
    }

    public void ajouterConfiguration(Configuration configuration) {
        configurations.add(configuration);
    }


    public static void main (String[] args) {
        //Création instances
        IVue vue = new VueImpl();
        Persistence persistence = new Persistence();
        IRecommandation recommandation = new SystemeRecommandation();
        Noyau noyau = new Noyau(vue, persistence, recommandation);

        List<AgentComposant> listeAgent = noyau.creerAgents();
        for (AgentComposant ac : listeAgent){
            ac.broadcast();
        }
        for (AgentComposant ac : listeAgent){
            ac.envoyerConnexionPossible();
        }

        noyau.calculerConfigurations();

        Eval eval = new Eval(noyau);
        eval.demanderNotation();

    }

}
