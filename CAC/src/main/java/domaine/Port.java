package domaine;

public class Port {

    private Composant composant;
    private String service;

    public Port(Composant composant, String service) {
        this.composant = composant;
        this.service = service;
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