package domaine;

import java.io.Serializable;

public class Port implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -9056062783144620502L;
	private Composant composant;
    private String service;

    public Port(Composant composant, String service) {
        this.composant = composant;
        this.service = service;
    }
    public Port() {
    	
    }

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
    
    
}