package domaine;

public abstract class Recommandable {

    private Integer nbApprobation;

    private Integer nbOccurence;

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
