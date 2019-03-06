package domaine;

import java.util.UUID;

public class Connexion {

    private UUID uuid;

    private Port fourni;
    private Port requis;

    private Integer nbApprobation;
    private Integer nbOccurence;

    public Connexion(UUID uuid, Port fourni, Port requis, Integer nbOccurence, Integer nbApprobation) {
        this.uuid = uuid;
        this.fourni = fourni;
        this.requis = requis;
        this.nbOccurence = nbOccurence;
        this.nbApprobation = nbApprobation;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Port getFourni() {
        return fourni;
    }

    public void setFourni(Port fourni) {
        this.fourni = fourni;
    }

    public Port getRequis() {
        return requis;
    }

    public void setRequis(Port requis) {
        this.requis = requis;
    }

    public Integer getNbApprobation() {
        return nbApprobation;
    }

    public void setNbApprobation(Integer nbApprobation) {
        this.nbApprobation = nbApprobation;
    }

    public Integer getNbOccurence() {
        return nbOccurence;
    }

    public void setNbOccurence(Integer nbOccurence) {
        this.nbOccurence = nbOccurence;
    }

}