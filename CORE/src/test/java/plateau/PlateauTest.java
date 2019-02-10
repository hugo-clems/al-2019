package plateau;

import static org.junit.Assert.*;

import agent.AbstractAgent;
import agent.AbstractAgentSitue;
import common.Direction;
import entites.Obstacle;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Map;

public class PlateauTest {

    private Plateau plateau;
    private AbstractAgent agent;

    @Before
    public void setUp() throws Exception {
        plateau = new Plateau("plateauTest");
        agent = Mockito.mock(AbstractAgentSitue.class, Mockito.CALLS_REAL_METHODS);
        //Ajout de 25 cases
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                Case c = new Case (new Position(x,y));
                //Ajout obstacles au bord
                if (x == 0 || x == 4 || y == 0 || y == 4) {
                    Obstacle o = new Obstacle("Obstacle" + x + y, plateau);
                    c.getAgentites().add(o);
                    plateau.getListeAgentites().put(o, c);
                }
                if (x == 2 && y == 3) {
                    plateau.getListeAgentites().put(agent, c);
                    c.getAgentites().add(agent);
                }
                plateau.getCases().add(c);
            }
        }
    }

    @Test
    public void placerAgentite() {
    }

    @Test
    public void enleverAgentite() {
    }

    @Test
    public void deplacerAgent() {
        //l'agent ne bouge pas
        Case ancienneCase = plateau.getCase(agent);
        assertFalse(plateau.deplacerAgent(agent, Direction.N));
        assertEquals(ancienneCase.getAgentites().size(), 1);

        //l'agent passe en Position (1,2)
        assertTrue(plateau.deplacerAgent(agent, Direction.SO));
        assertEquals(plateau.getCase(agent), new Case(new Position(1,2)));
        assertTrue(ancienneCase.getAgentites().isEmpty());

        //l'agent passe en Position (1,1)
        ancienneCase = plateau.getCase(agent);
        assertTrue(plateau.deplacerAgent(agent, Direction.S));
        assertEquals(plateau.getCase(agent), new Case(new Position(1,1)));
        assertTrue(ancienneCase.getAgentites().isEmpty());

        //l'agent ne bouge pas
        ancienneCase = plateau.getCase(agent);
        assertFalse(plateau.deplacerAgent(agent, Direction.S));
        assertEquals(ancienneCase.getAgentites().size(), 1);

        //le plateau sait que l'agent est en Position (1,1)
        assertEquals(plateau.getListeAgentites().get(agent), new Case(new Position(1,1)));
    }

    @Test
    public void ramasserEntite() {
    }

    @Test
    public void deposerEntite() {
    }

    @Test
    public void getVoisinage() {
        Map<Direction, Case> voisinage = plateau.getVoisinage(agent);

        //Nord
        assertEquals (new Case(new Position(2, 4)), voisinage.get(Direction.N));
        assertEquals(voisinage.get(Direction.N).getAgentites().size(), 1);
        assertTrue(voisinage.get(Direction.N).getAgentites().get(0) instanceof Obstacle);

        //Nord Ouest
        assertEquals (new Case(new Position(1, 4)), voisinage.get(Direction.NO));
        assertEquals(voisinage.get(Direction.NO).getAgentites().size(), 1);
        assertTrue(voisinage.get(Direction.NO).getAgentites().get(0) instanceof Obstacle);

        // Nord Est
        assertEquals (new Case(new Position(3, 4)), voisinage.get(Direction.NE));
        assertEquals(voisinage.get(Direction.NE).getAgentites().size(), 1);
        assertTrue(voisinage.get(Direction.NE).getAgentites().get(0) instanceof Obstacle);

        // Sud Ouest
        assertEquals(new Case(new Position(1, 2)), voisinage.get(Direction.SO));
        assertTrue(voisinage.get(Direction.SO).getAgentites().isEmpty());
    }

    @Test
    public void getCase() {
        assertEquals(new Case(new Position(2,3)), plateau.getCase(agent));
    }
}