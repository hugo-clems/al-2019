package moteur;

import domaine.Configuration;
import interfaces.SystemeRecommandation;

import java.util.HashSet;
import java.util.Set;

public class Moteur {

    private Set<Configuration> configurations = new HashSet<>();
    private SystemeRecommandation systemeRecommandation;

    public Moteur(SystemeRecommandation systemeRecommandation) {
        this.systemeRecommandation = systemeRecommandation;
    }

    public Set<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<Configuration> configurations) {
        this.configurations = configurations;
    }

    public SystemeRecommandation getSystemeRecommandation() {
        return systemeRecommandation;
    }

    public void setSystemeRecommandation(SystemeRecommandation systemeRecommandation) {
        this.systemeRecommandation = systemeRecommandation;
    }

    public void ajouterConfiguration(Configuration configuration) {
        configurations.add(configuration);
    }

}
