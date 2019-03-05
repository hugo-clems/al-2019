package domaine;

import java.util.UUID;

public class Port  {

    private Composant composant;
    private String service;
    private UUID uuid;

    public Port(Composant composant, String service, UUID uuid) {
        this.composant = composant;
        this.service = service;
        this.uuid= uuid;
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

    @Override
    public boolean equals(Object obj) {
        Port p =(Port) obj;
         if (p.service.equals(this.service) && !this.composant.getNom().equals(p.composant.getNom()) )
            return true ;
         else {
             return false;
         }
    }
}