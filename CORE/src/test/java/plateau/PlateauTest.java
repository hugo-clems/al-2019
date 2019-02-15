package plateau;

import agent.AbstractAgent;
import agent.AbstractAgentSitue;
import common.Direction;
import entites.Obstacle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.Assert.*;

public class PlateauTest {

    private Plateau plateau;
    private AbstractAgent agent;

    @Before
    public void setUp() throws Exception {
        plateau = new Plateau("plateauTest", 5, 5);
        agent = Mockito.mock(AbstractAgentSitue.class, Mockito.CALLS_REAL_METHODS);

        plateau.getListeAgentites().put(agent, plateau.getCases().get(new Position(2, 3)));
        plateau.getCases().get(new Position(2, 3)).getAgentites().add(agent);
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