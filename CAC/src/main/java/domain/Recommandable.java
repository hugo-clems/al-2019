package java.domain;

public abstract  class Recommandable {

    private Integer nbApprobation;

    private Integer nbOccurence;

    public Integer getNbApprobation() {
        return nbApprobation;
    }

    public void setNbApprobation(Integer nbApprobation) {
        this.nbApprobation = nbApprobation;
    }
}
