package plateau;

import environnement2d.PlateauComponent;
import environnement2d.Window;

public class Application {

    //region Attributes
    /**
     * Plateau
     */
    private Plateau mPlateau;

    /**
     * l'arène
     */
    private PlateauComponent plateauComponent;
    //endregion

    //region Constructors
    /**
     * Constructor
     *
     * @param nomPlateau String
     * @param ligne int
     * @param colonne int
     */
    public Application(String nomPlateau, int ligne, int colonne) {
        this.mPlateau = new Plateau(nomPlateau, ligne, colonne);
        plateauComponent = new PlateauComponent(mPlateau);
    }

    /**
     * Constructor
     *
     * @param nomPlateau String
     * @param ligne int
     * @param colonne int
     * @param casePaint CasePaint
     */
    public Application(String nomPlateau, int ligne, int colonne, PlateauComponent.CasePaint casePaint) {
        this.mPlateau = new Plateau(nomPlateau, ligne, colonne);
        plateauComponent = new PlateauComponent(mPlateau, casePaint);
    }
    //endregion

    //region Setters
    /**
     * Set casePaint sur plateau
     * @param casePaint
     */
    public void setCasePaint(PlateauComponent.CasePaint casePaint) {
        plateauComponent.setCasePaint(casePaint);
    }

    public void setCaseSize(int size) {
        plateauComponent.setCaseSize(size);
    }
    //endregion

    //region Getters
    /**
     * Get Plateau
     * @return plateau IAgentPlateau
     */
    public IAgentPlateau getIAgentPlateau() {
        return this.mPlateau;
    }

    public IEntitePlateau getIEntitePlateau() {
        return this.mPlateau;
    }
    //endregion

    //region Plateau Managment
    /**
     * Initialisation du plateau
     * ajout d'un agent sur le plateau
     *
     * @param position Position
     * @param agentite IAgentite
     * @return true si l'agent est placé sur le plateau sinon false
     */
    public Boolean placerAgentite(Position position, IAgentite agentite) {
        return mPlateau.placerAgentite(position, agentite);
    }

    /**
     * Enlever un agent du plateau
     * @param position Position
     * @param agentite IAgentite
     * @return le plateau
     */
    public IAgentite enleverAgentite(Position position, IAgentite agentite) {
        return mPlateau.enleverAgentite(position, agentite);
    }
    //endregion

    /**
     * Lancement de l'interface 2D
     */
    public void run() {
        Window window = new Window(plateauComponent);
        window.start();
    }
}
