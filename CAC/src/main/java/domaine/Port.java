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
}