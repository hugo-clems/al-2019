package domaine;

import java.util.UUID;

public class Port {

    private UUID uuid;
    private Composant composant;
    private String service;

    public Port(Composant composant, String service) {
        this.composant = composant;
        this.service = service;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public boolean equals(Port port) {
        return this.uuid.equals(port.getUuid());
    }

    public int hashCode() {
        return uuid.hashCode();
    }

}