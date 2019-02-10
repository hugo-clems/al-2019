package plateau;

import static org.junit.Assert.*;

import MASInfrastructure.Etat.LifeCycle;
import agent.AbstractAgent;
import agent.AbstractAgentSitue;
import entites.Obstacle;
import org.junit.*;

public class PlateauTest {

    private Plateau plateau;
    @Before
    public void setUp() throws Exception {
        plateau = new Plateau("plateauTest");
        //Ajout de 25 cases
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                Case c = new Case (new Position(x,y));
                //Ajout obstacles au bord
                if (x == 0 || x == 4 || y == 0 || y == 4) {
                    c.getAgentites().add(new Obstacle("Obstacle" + x + y, new IEntitePlateau() {
                    }));
                }
                plateau.getCases().add(c);
            }
        }

        /*AbstractAgent agent = new AbstractAgentSitue("Agent", new LifeCycle(), ) {
            @Override
            public void actionTour() {

            }
        }*/
    }

    @Test
    public void placerAgentite() {
    }

    @Test
    public void enleverAgentite() {
    }

    @Test
    public void deplacerAgent() {
    }

    @Test
    public void ramasserEntite() {
    }

    @Test
    public void deposerEntite() {
    }

    @Test
    public void getVoisinage() {
    }

    @Test
    public void getCase() {
    }
}