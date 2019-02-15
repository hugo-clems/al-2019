package plateau;

import agent.AbstractAgent;
import common.Direction;
import entites.AbstractEntite;
import entites.Obstacle;

import java.util.HashMap;
import java.util.Map;

public class Plateau implements IEntitePlateau, IDeveloppeurPlateau, IAgentPlateau {

    /**
     * Nom du plateau.
     */
    private String nom;

    /**
     * Map des agentités du plateau liés à une case.
     */
    private Map<IAgentite, Case> listeAgentites;

    /**
     * Liste des cases du plateau.
     */
    private Map<Position, Case> cases;

    /**
     * Nombre de colonne du plateau
     */
    private int colonne;

    /**
     * Nombre de ligne du plateau
     */
    private int ligne;

    /**
     * Constructeur par défaut.
     * @param nom
     * @param ligne nombre de ligne du plateau
     * @param colonne nombre de colonne du plateau
     */
    public Plateau(String nom, int ligne, int colonne) {
        this.nom = nom;
        this.ligne = ligne;
        this.colonne = colonne;
        listeAgentites = new HashMap();
        cases = new HashMap<>();

        // Initialisation du plateau
        this.init();
    }

    /**
     * Get le nom du plateau.
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Get la liste des agentités.
     * @return
     */
    public Map<IAgentite, Case> getListeAgentites() {
        return listeAgentites;
    }

    /**
     * Get la liste des cases du plateau.
     * @return
     */
    public Map<Position, Case> getCases() {
        return cases;
    }

    /**
     * Set le nom du plateau.
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    @Override
    public Boolean placerAgentite(Position position, IAgentite agentite) {
        return null;
    }

    @Override
    public IAgentite enleverAgentite(Position position, IAgentite agentite) {
        return null;
    }

    @Override
    public Boolean deplacerAgent(AbstractAgent agent, Direction direction) {
        Case c = getCaseByDirectionForAgent(direction, getCase(agent));

        //Vérification s'il n'y a pas d'obstacle sur la case où veut bouger l'agent
        for (IAgentite agentite : c.getAgentites()) {
            if (agentite instanceof Obstacle) {
                return false;
            }
        }
        //Déplacement de l'agent
        getCase(agent).getAgentites().remove(agent);
        c.getAgentites().add(agent);
        listeAgentites.replace(agent, c);
        return true;
    }

    @Override
    public Boolean ramasserEntite(AbstractAgent agent, AbstractEntite entite) {
        return null;
    }

    @Override
    public Boolean deposerEntite(AbstractAgent agent, AbstractEntite entite) {
        return null;
    }

    @Override
    public Map <Direction, Case> getVoisinage(AbstractAgent agent) {
        Map<Direction, Case> map = new HashMap();
        for (Direction direction : Direction.values()) {
            map.put(direction, getCaseByDirectionForAgent(direction, getCase(agent)));
        }
        return map;
    }

    @Override
    public Case getCase(AbstractAgent agent) {
        return listeAgentites.get(agent);
    }

    /**
     * Initialisation du plateau
     */
    private void init() {
        for (int x = 0; x < colonne; x++) {
            for (int y = 0; y < ligne; y++) {
                Position positionTemp = new Position(x, y);
                Case caseTemp = new Case(positionTemp);
                // TODO : remplacer par placerEntitie
                if (x == 0 || x == colonne - 1 || y == 0 || y == ligne - 1) {
                    Obstacle obstacle = new Obstacle("Obstacle" + x + "_" + y, this);
                    caseTemp.getAgentites().add(obstacle);
                    listeAgentites.put(obstacle, caseTemp);
                }
                cases.put(positionTemp, caseTemp);
            }
        }
    }

    /**
     * Retourne la case qui se trouve à une distance de 1 de l'agent en fonction de la direction
     * de celui-ci.
     * @param direction Direction de la case souhaitée
     * @param caseAgent Case de l'agent
     * @return Case desirée ou null en cas d'erreur
     */
    private Case getCaseByDirectionForAgent(Direction direction, Case caseAgent) {
        Position agentPosition = caseAgent.getPosition();
        switch (direction) {
            case N:
                return cases.get(new Position(agentPosition.getX(), agentPosition.getY() + 1));
            case NE:
                return cases.get(new Position(agentPosition.getX() + 1, agentPosition.getY() + 1));
            case E:
                return cases.get(new Position(agentPosition.getX() + 1, agentPosition.getY()));
            case SE:
                return cases.get(new Position(agentPosition.getX() + 1, agentPosition.getY() - 1));
            case S:
                return cases.get(new Position(agentPosition.getX(), agentPosition.getY() - 1));
            case SO:
                return cases.get(new Position(agentPosition.getX() - 1, agentPosition.getY() - 1));
            case O:
                return cases.get(new Position(agentPosition.getX() - 1, agentPosition.getY()));
            case NO:
                return cases.get(new Position(agentPosition.getX() - 1, agentPosition.getY() + 1));
        }
        return null;
    }
}
