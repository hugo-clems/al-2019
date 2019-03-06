package domaine;

import java.util.UUID;

public class Connexion {

    private UUID uuid;

    private Port fourni;
    private Port requis;

    private int nbApprobation;
    private int nbOccurence;

    public Connexion(UUID uuid, Port fourni, Port requis, int nbOccurence, int nbApprobation) {
        this.uuid = uuid;
        this.fourni = fourni;
        this.requis = requis;
        this.nbOccurence = nbOccurence;
        this.nbApprobation = nbApprobation;
    }

    public Connexion(Port fourni, Port requis, int nbOccurence, int nbApprobation) {
        this.uuid = UUID.randomUUID();
        this.fourni = fourni;
        this.requis = requis;
        this.nbOccurence = nbOccurence;
        this.nbApprobation = nbApprobation;
    }

    public Connexion(Port fourni, Port requis) {
        this.uuid = UUID.randomUUID();
        this.fourni = fourni;
        this.requis = requis;
        this.nbOccurence = 0;
        this.nbApprobation = 0;
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

    public int getNbApprobation() {
        return nbApprobation;
    }

    public void setNbApprobation(int nbApprobation) {
        this.nbApprobation = nbApprobation;
    }

    public int getNbOccurence() {
        return nbOccurence;
    }

    public void setNbOccurence(int nbOccurence) {
        this.nbOccurence = nbOccurence;
    }

}