package domaine;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Composant {

    /**
     * Nom du composant
     */
    private String nom;

    /**
     * Port fournis par le composant
     */
    private Set<Port> portFournis = new HashSet<>();
    /**
     * Port requis par le composant
     */
    private Set<Port> portRequis = new HashSet<>();

    public Composant(String name) {
        this.nom = name;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Port> getPortFournis() {
        return portFournis;
    }

    public void setPortFournis(Set<Port> portFournis) {
        this.portFournis = portFournis;
    }

    public Set<Port> getPortRequis() {
        return portRequis;
    }

    public void setPortRequis(Set<Port> portRequis) {
        this.portRequis = portRequis;
    }

    /**
     * Ajouter un port à l'ensemble des ports fournis
     *
     * @param p Port à ajouter
     */
    public void ajouterPortFourni(Port p) {
        portFournis.add(p);
    }

    /**
     * Ajouter un port à l'ensemble des ports requis
     *
     * @param p Port à ajouter
     */
    public void ajouterPortRequis(Port p) {
        portRequis.add(p);
    }

}