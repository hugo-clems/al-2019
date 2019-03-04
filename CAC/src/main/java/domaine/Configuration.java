package domaine;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Configuration extends Recommandable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4866753559812134311L;

	private String id;

	private Set<Connexion> connexions = new HashSet<>();
	

    public Configuration(Set<Connexion> connexions) {
		super();
		this.connexions = connexions;
		this.id = UUID.randomUUID().toString(); 
	}
    public Configuration() {
    	
    }

	public Set<Connexion> getConnexions() {
        return connexions;
    }

    public void setConnexions(Set<Connexion> connexions) {
        this.connexions = connexions;
    }

    public void ajouterConnexion(Connexion connexion) {
        connexions.add(connexion);
    }
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (connexions == null) {
			if (other.connexions != null)
				return false;
		} else if (!connexions.equals(other.connexions))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}