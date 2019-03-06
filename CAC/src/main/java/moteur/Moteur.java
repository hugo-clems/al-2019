package moteur;

import domaine.Configuration;
import interfaces.IRecommandation;

import java.util.HashSet;
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

}
