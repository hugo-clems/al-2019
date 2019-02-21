package entites;

import agent.AbstractAgentSitue;
import plateau.Position;

import java.util.List;

public class Robot extends AbstractAgentSitue {

    private int nbActions;

    private int nbDeplacements;

    private List<List<CaseRobot>> carteMemoire;

    private Colis colis;

    private Position caseDepot;

    private Position caseCollecte;

    public Robot() {
        this.nbActions = 0;
        this.nbDeplacements = 0;
    }

    @Override
    public void actionTour() {

    }

    public int getNbActions() {
        return nbActions;
    }

    public void setNbActions(int nbActions) {
        this.nbActions = nbActions;
    }

    public int getNbDeplacements() {
        return nbDeplacements;
    }

    public void setNbDeplacements(int nbDeplacements) {
        this.nbDeplacements = nbDeplacements;
    }

    public List<List<CaseRobot>> getCarteMemoire() {
        return carteMemoire;
    }

    public void setCarteMemoire(List<List<CaseRobot>> carteMemoire) {
        this.carteMemoire = carteMemoire;
    }

    public Colis getColis() {
        return colis;
    }

    public void setColis(Colis colis) {
        this.colis = colis;
    }

    public Position getCaseDepot() {
        return caseDepot;
    }

    public void setCaseDepot(Position caseDepot) {
        this.caseDepot = caseDepot;
    }

    public Position getCaseCollecte() {
        return caseCollecte;
    }

    public void setCaseCollecte(Position caseCollecte) {
        this.caseCollecte = caseCollecte;
    }

}
