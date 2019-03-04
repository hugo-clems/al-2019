package plateau;

import agent.AbstractAgent;
import agent.AbstractAgentSitue;
import common.Direction;
import entites.AbstractEntite;
import entites.Obstacle;

import java.util.HashMap;
import java.util.Map;

public class Plateau implements IEntitePlateau, IAgentPlateau {

    //region Attributes
    /**
     * Nom du plateau.
     */
    private String nom;

    /**
     * Map des agentités du plateau liés à une case.
     */
    private Map<IAgentite, Case> listeAgentites;

    /**
     * Map des cases du plateau avec leur position.
     */
    private Map<Position, Case> cases;

    /**
     * Nombre de colonne du plateau (axe x)
     */
    private int colonne;

    /**
     * Nombre de ligne du plateau (axe y)
     */
    private int ligne;
    //endregion

    //region Contructors
    /**
     * Constructeur par défaut.
     * @param nom Le nom du plateau
     * @param ligne nombre de ligne du plateau
     * @param colonne nombre de colonne du plateau
     */
    public Plateau(String nom, int ligne, int colonne) {
        this.nom = nom;
        this.ligne = ligne;
        this.colonne = colonne;
        this.listeAgentites = new HashMap<>();
        cases = new HashMap<>();

        // Initialisation du plateau
        this.init();
    }
    //endregion

    //region Getters/Setters
    /**
     * Get le nom du plateau.
     * @return Le nom du plateau
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set le nom du plateau.
     *
     * @param nom le nouveau nom du plateau
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get la map des agentités.
     * @return La map des agents lier à une case
     */
    public Map<IAgentite, Case> getListeAgentites() {
        return listeAgentites;
    }

    /**
     * Get la map des cases du plateau.
     * @return La map des cases avec leur position
     */
    public Map<Position, Case> getCases() {
        return cases;
    }

    /**
     * get le nombre de colone (axe x)
     * @return colone
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Get le nombre de ligne (axe Y)
     * @return ligne
     */
    public int getLigne() {
        return ligne;
    }
    //endregion

    /**
     * place agent/entité sur une case
     *
     * @param position Position
     * @param agentite IAgentite
     * @return true si l'agent/entité est placé(e) sur la case sinon false
     */
    public Boolean placerAgentite(Position position, IAgentite agentite) {

        Case mCase = cases.get(position);
        if (checkObstacleInCase(mCase) || (agentite instanceof Obstacle && !mCase.getAgentites().isEmpty()))
            return false;
        mCase.getAgentites().add(agentite);
        listeAgentites.put(agentite, mCase);
        return true;
    }

    /**
     * Enlève l'agent/entité de la case
     *
     * @param position Position
     * @param agentite IAgentite
     * @return null si la case ne contient pas l'agent/entité sinon l'agent/entité
     */
    public IAgentite enleverAgentite(Position position, IAgentite agentite) {
        if (listeAgentites.isEmpty() || listeAgentites.get(agentite) == null) {
            return null;
        }
        Case mCase = listeAgentites.remove(agentite);
        mCase.getAgentites().remove(agentite);
        return agentite;
    }

    /**
     * Déplace un agent
     *
     * @param agent l'agent à déplacer
     * @param direction la direction vers laquelle déplacer l'agent
     * @return false si la case est occupée par un obstacle true sinon
     */
    @Override
    public Boolean deplacerAgent(AbstractAgent agent, Direction direction) {
        Case c = getCaseByDirectionForAgent(direction, getCase(agent));

        //Vérification s'il n'y a pas d'obstacle sur la case où veut bouger l'agent
        assert c != null;
        if (checkObstacleInCase(c)) {
            return false;
        }
        //Déplacement de l'agent
        getCase(agent).getAgentites().remove(agent);
        c.getAgentites().add(agent);
        listeAgentites.replace(agent, c);
        return true;
    }

    /**
     * Ramasse une entité
     *
     * @param agent l'agent qui ramasse l'entité
     * @param entite l'entité à ramasser
     * @return true si l'agent ramasse l'entité false sinon
     */
    @Override
    public Boolean ramasserEntite(AbstractAgent agent, AbstractEntite entite) {
        if (agent instanceof AbstractAgentSitue) {
            AbstractAgentSitue agentSitue = (AbstractAgentSitue) agent;
            Case devantAgent = getCaseByDirectionForAgent(agentSitue.getDirection(), this.getCase(agentSitue));
            if (devantAgent != null) {
                if (agentSitue.getEntitePortee() == null
                        && !(entite instanceof Obstacle)
                        && devantAgent.getAgentites().contains(entite)) {
                    agentSitue.setEntitePortee((AbstractEntite) this.enleverAgentite(
                            devantAgent.getPosition(), entite));
                    return true;
                }
            }


        }
        return false;
    }

    /**
     * Déposer une entité
     *
     * @param agent l'agent qui dépose l'entité
     * @param entite l'entité à déposer
     * @return true si l'agent dépose une entité false sinon
     */
    @Override
    public Boolean deposerEntite(AbstractAgent agent, AbstractEntite entite) {
        if (agent instanceof AbstractAgentSitue) {
            AbstractAgentSitue agentSitue = (AbstractAgentSitue) agent;
            if (entite.equals(agentSitue.getEntitePortee())) {
                ((AbstractAgentSitue) agent).setEntitePortee(null);
                this.placerAgentite(getCase(agent).getPosition(), entite);
                return true;
            }
        }
        return false;
    }

    /**
     * Récupère le voisinage d'un agent les 8 cases
     *
     * @param agent l'agent qui veut connaître son voisinage
     * @return une map qui contient la direction et la case
     */
    @Override
    public Map <Direction, Case> getVoisinage(AbstractAgent agent) {
        Map<Direction, Case> map = new HashMap<>();
        for (Direction direction : Direction.values()) {
            map.put(direction, getCaseByDirectionForAgent(direction, getCase(agent)));
        }
        return map;
    }

    /**
     * Récupère la case de l'agent
     *
     * @param agent l'agent qui veut connaître sa case
     * @return Case sur laquelle se trouve l'agent
     */
    @Override
    public Case getCase(AbstractAgent agent) {
        return listeAgentites.get(agent);
    }

    /**
     * Initialisation du plateau
     * Creation de toutes les cases
     * Ajout d'obstacle autour du plateau
     */
    private void init() {
        for (int x = 0; x < colonne; x++) {
            for (int y = 0; y < ligne; y++) {
                Position positionTemp = new Position(x, y);
                Case caseTemp = new Case(positionTemp);
                cases.put(positionTemp, caseTemp);
                if (x == 0 || x == colonne - 1 || y == 0 || y == ligne - 1) {
                    Obstacle obstacle = new Obstacle("Obstacle" + x + "_" + y);
                    placerAgentite(positionTemp, obstacle);
                }
            }
        }
    }

    /**
     * Retourne la case qui se trouve à la position donnée par rapport à l'agent.
     * @param direction Direction de la case souhaitée
     * @param caseAgent Case de l'agent
     * @return Case desirée ou null en cas d'erreur
     */
    private Case getCaseByDirectionForAgent(Direction direction, Case caseAgent) {
        Position agentPosition = caseAgent.getPosition();
        switch (direction) {
            case N:
                return cases.get(new Position(agentPosition.getX(), agentPosition.getY() - 1));
            case NE:
                return cases.get(new Position(agentPosition.getX() + 1, agentPosition.getY() - 1));
            case E:
                return cases.get(new Position(agentPosition.getX() + 1, agentPosition.getY()));
            case SE:
                return cases.get(new Position(agentPosition.getX() + 1, agentPosition.getY() + 1));
            case S:
                return cases.get(new Position(agentPosition.getX(), agentPosition.getY() + 1));
            case SO:
                return cases.get(new Position(agentPosition.getX() - 1, agentPosition.getY() + 1));
            case O:
                return cases.get(new Position(agentPosition.getX() - 1, agentPosition.getY()));
            case NO:
                return cases.get(new Position(agentPosition.getX() - 1, agentPosition.getY() - 1));
        }
        return null;
    }

    /**
     * Methode pour tester la présence d'un obstacle sur un case
     * @param pCase La case dans la quelle on test si elle contient un obstacle
     * @return true si il y'a un obstacle sinon false
     */
    private boolean checkObstacleInCase(Case pCase) {
        for (IAgentite agentite : pCase.getAgentites()) {
            if (agentite instanceof Obstacle)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Plateau{" +
                "nom='" + nom + '\'' +
                ", colonne=" + colonne +
                ", ligne=" + ligne +
                '}';
    }
}
