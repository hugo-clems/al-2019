package domaine;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Composant implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5585017048919772280L;

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
    public Composant() {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((portFournis == null) ? 0 : portFournis.hashCode());
		result = prime * result + ((portRequis == null) ? 0 : portRequis.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Composant other = (Composant) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (portFournis == null) {
			if (other.portFournis != null)
				return false;
		} else if (!portFournis.equals(other.portFournis))
			return false;
		if (portRequis == null) {
			if (other.portRequis != null)
				return false;
		} else if (!portRequis.equals(other.portRequis))
			return false;
		return true;
	}
    
    

}