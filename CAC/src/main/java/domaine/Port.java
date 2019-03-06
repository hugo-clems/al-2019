package domaine;

import java.util.UUID;

public class Port {

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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean equals(Port port) {
        return this.uuid.equals(port.getUuid());
    }

    public int hashCode() {
        return uuid.hashCode();
    }

    public boolean estCompatible(Port p) {
        if (p.service.equals(this.service) && !this.composant.getNom().equals(p.composant.getNom()) )
            return true ;
        else {
            return false;
        }
    }
}