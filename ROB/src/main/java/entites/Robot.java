package entites;

import agent.AbstractAgentSitue;
import common.Direction;
import plateau.Case;
import plateau.IAgentPlateau;
import plateau.Position;
import strategie.detection.Detection;

import java.util.List;

public class Robot extends AbstractAgentSitue {

    private int nbActions;

    private int nbDeplacements;

    private List<List<CaseRobot>> carteMemoire;

    private Colis colis;

    private Position caseDepot;

    private Position caseCollecte;

    public Robot(String nom, IAgentPlateau plateau, Direction directionInitiale, Position centreCollecte, Position centreDepot) {
        super(nom, plateau, directionInitiale);
        this.nbActions = 0;
        this.nbDeplacements = 0;
        this.caseCollecte = centreCollecte;
        this.caseDepot = centreDepot;
    }

    @Override
    public void actionTour() {
        if(getColis() == null){
            if(estColisDevantSoi()){
                this.ramasser(new Colis("Colis"));
                setColis((Colis) this.getEntitePortee());
            }
            boolean b = allerZoneCollecte();
            if(b) nbActions++; nbDeplacements++;
        } else {
            boolean b = allerZoneDepot();
            if(b) nbActions++; nbDeplacements++;
        }
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

    public void faireDemiTour(){
        switch (this.getDirection()) {
            case N:
                this.seTournerVers(Direction.S);
                break;
            case NE:
                this.seTournerVers(Direction.SO);
                break;
            case E:
                this.seTournerVers(Direction.O);
                break;
            case SE:
                this.seTournerVers(Direction.NO);
                break;
            case S:
                this.seTournerVers(Direction.N);
                break;
            case SO:
                this.seTournerVers(Direction.NE);
                break;
            case O:
                this.seTournerVers(Direction.E);
                break;
            default:
                this.seTournerVers(Direction.SE);
                break;
        }
    }

    public boolean allerZoneCollecte(){
        Position positionRobot = this.plateau.getCase(this).getPosition();
        return this.seDeplacerVers(directionVers(positionRobot, getCaseCollecte()));
    }

    public boolean allerZoneDepot(){
        Position positionRobot = this.plateau.getCase(this).getPosition();
        return this.seDeplacerVers(directionVers(positionRobot, getCaseDepot()));
    }

    private  static Direction directionVers(Position pos1, Position pos2){

        if(pos1.getX() > pos2.getX() && pos1.getY() > pos2.getY()){
            return Direction.NO;
        }else if(pos1.getX() > pos2.getX() && pos1.getY() < pos2.getY()){
            return Direction.SO;
        }else if(pos1.getX() < pos2.getX() && pos1.getY() > pos2.getY()){
            return Direction.NE;
        }else if(pos1.getX() < pos2.getX() && pos1.getY() < pos2.getY()){
            return Direction.SE;
        }else if(pos1.getX() == pos2.getX() && pos1.getY() < pos2.getY()){
            return Direction.S;
        }else if(pos1.getX() == pos2.getX() && pos1.getY() > pos2.getY()){
            return Direction.N;
        }else if(pos1.getX() < pos2.getX() && pos1.getY() == pos2.getY()){
            return Direction.E;
        }else{
            return Direction.O;
        }
    }

    public boolean estColisDevantSoi(){
        Detection.detecterVoisinage(this);
        Case caseRobot = this.plateau.getCase(this);

        int x = 0, y = 0;

        switch (this.getDirection()) {
            case N: y--;
                break;
            case E: x++;
                break;
            case S: y++;
                break;
            case O: x--;
                break;
            default:
                break;
        }
        CaseRobot c = this.carteMemoire.get(caseRobot.getPosition().getY() + y -1).get(caseRobot.getPosition().getX() + x -1);

        return c.isCollecte();
    }

    public boolean estMurDevantSoi(){
        Detection.detecterVoisinage(this);
        Case caseRobot = this.plateau.getCase(this);

        int x = 0, y = 0;

        switch (this.getDirection()) {
            case N: y--;
                break;
            case E: x++;
                break;
            case S: y++;
                break;
            case O: x--;
                break;
            default:
                break;
        }
        CaseRobot c = this.carteMemoire.get(caseRobot.getPosition().getX() + x).get(caseRobot.getPosition().getY() + y);
        return c.isObstacle();
    }

    public void attendre(){

    }

    public boolean isColisDansInventaire(){
        return this.getColis() != null;
    }

    public void miseAjourCarteMemoire(List<Case> casesDecouvertes){

    }
}
