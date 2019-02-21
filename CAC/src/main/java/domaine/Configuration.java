package domaine;

import java.util.HashSet;
import java.util.Set;

public class Configuration {

    private Set<Connexion> connexions = new HashSet<>();

    public Set<Connexion> getConnexions() {
        return connexions;
    }

    public void setConnexions(Set<Connexion> connexions) {
        this.connexions = connexions;
    }

    public void ajouterConnexion(Connexion connexion) {
        connexions.add(connexion);
    }

}