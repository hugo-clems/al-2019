package plateau;

import environnement2d.PlateauComponent;
import environnement2d.Window;

public class Application {

    //region Attributes
    private Plateau mPlateau;

    private PlateauComponent plateauComponent;
    //endregion

    //region Constructors
    public Application(String nomPlateau, int ligne, int colonne) {
        this.mPlateau = new Plateau(nomPlateau, ligne, colonne);
        plateauComponent = new PlateauComponent(mPlateau);
    }

    public Application(String nomPlateau, int ligne, int colonne, PlateauComponent.CasePaint casePaint) {
        this.mPlateau = new Plateau(nomPlateau, ligne, colonne);
        plateauComponent = new PlateauComponent(mPlateau, casePaint);
    }
    //endregion

    //region Getters
    public void setCasePaint(PlateauComponent.CasePaint casePaint) {
        plateauComponent.setCasePaint(casePaint);
    }

    public IAgentPlateau getIAgentPlateau() {
        return this.mPlateau;
    }
    //endregion

    //region Methode pour l'initialisation du plateau
    public Boolean placerAgentite(Position position, IAgentite agentite) {
        return mPlateau.placerAgentite(position, agentite);
    }

    public IAgentite enleverAgentite(Position position, IAgentite agentite) {
        return mPlateau.enleverAgentite(position, agentite);
    }
    //endregion

    //Lancement de l'interface 2D
    public void run() {
        Window window = new Window(plateauComponent);
        window.start();
    }
}
