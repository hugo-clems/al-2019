package domaine;

import java.io.Serializable;
import java.util.UUID;

public class Port implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -9056062783144620502L;

    private Composant composant;
    private String service;
    private UUID uuid ;

    public Port(UUID uuid, Composant composant, String service) {
        this.uuid = uuid;
        this.composant = composant;
        this.service = service;
    }

    public Port(Composant composant, String service) {
        this.uuid = UUID.randomUUID();
        this.composant = composant;
        this.service = service;
    }
    
    public Port(Composant composant, String service, String id) {
        this.uuid = UUID.fromString(id);
        this.composant = composant;
        this.service = service;
    }

    public Port() {
    	
    }

    /*public Port(Composant composant, String service) {
		this(composant, service, "");
	}
	*/
	public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Composant getComposant() {
        return composant;
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((composant == null) ? 0 : composant.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
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
		Port other = (Port) obj;
		if (composant == null) {
			if (other.composant != null)
				return false;
		} else if (!composant.equals(other.composant))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean equals(Port port) {
        return this.uuid.equals(port.getUuid());
    }

   /* public int hashCode() {
        return uuid.hashCode();
    }*/

    public boolean estCompatible(Port p) {
        if (p.service.equals(this.service) && !this.composant.getNom().equals(p.composant.getNom()) )
            return true ;
        else {
            return false;
        }
    }

}