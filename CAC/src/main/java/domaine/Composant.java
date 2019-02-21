package domain;

import javax.sound.sampled.Port;
import java.util.HashSet;
import java.util.Set;

public class Composant  {

    private String nom;

    private Set<Port> portFournis;
    private Set<Port> portRequis;

    public Composant(String name) {
        this.nom = name;
        this.portFournis = new HashSet<>();
        this.portRequis = new HashSet<>();
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Port> getPortFourni() {
        return portFournis;
    }

    public void setPortFourni(Set<Port> portFourni) {
        this.portFournis = portFourni;
    }

    public Set<Port> getPortRequis() {
        return portRequis;
    }

    public void setPortRequis(Set<Port> portRequis) {
        this.portRequis = portRequis;
    }

    public void addPortFournit (Port p){
        portFournis.add(p);
    }

    public void addPortRequit (Port p) {
        portRequis.add(p);
    }


}