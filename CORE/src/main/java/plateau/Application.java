package plateau;

import environnement2d.PlateauComponent;
import environnement2d.Window;

public class Application {

    private Plateau mPlateau;

    Application(String nomPlateau, int ligne, int colonne) {
        this.mPlateau = new Plateau(nomPlateau, ligne, colonne);
    }

    public Boolean placerAgentite(Position position, IAgentite agentite) {
        return mPlateau.placerAgentite(position, agentite);
    }

    public IAgentite enleverAgentite(Position position, IAgentite agentite) {
        return mPlateau.enleverAgentite(position, agentite);
    }

    public void run() {
        PlateauComponent plateauComponent = new PlateauComponent(mPlateau);
        Window window = new Window(plateauComponent);
        // TODO run window
    }
}
