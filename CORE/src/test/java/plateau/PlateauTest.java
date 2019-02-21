package plateau;

import agent.AbstractAgent;
import agent.AbstractAgentSitue;
import common.Direction;
import entites.AbstractEntite;
import entites.Obstacle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PlateauTest {

    private Plateau plateau;
    private AbstractAgent agent;

    @Before
    public void setUp() throws Exception {
        plateau = new Plateau("plateauTest", 5, 5);
        agent = Mockito.mock(AbstractAgentSitue.class, Mockito.CALLS_REAL_METHODS);
        when(((AbstractAgentSitue) agent).getDirection()).thenReturn(Direction.N);

        plateau.getListeAgentites().put(agent, plateau.getCases().get(new Position(2, 3)));
        plateau.getCases().get(new Position(2, 3)).getAgentites().add(agent);
    }

    @Test
    public void placerAgentiteTest() {
        AbstractAgent agentTest = Mockito.mock(AbstractAgentSitue.class, Mockito.CALLS_REAL_METHODS);
        Position position = new Position(0, 0);
        assertFalse(plateau.placerAgentite(position, agentTest));
        position.setX(3);
        position.setY(3);
        assertTrue(plateau.placerAgentite(position, agentTest));
        Case mCase = plateau.getCases().get(position);
        assertEquals(mCase, plateau.getListeAgentites().get(agentTest));
        assertTrue(mCase.getAgentites().contains(agentTest));
        assertEquals(1, mCase.getAgentites().size());
    }

    @Test
    public void enleverAgentiteTest() {
        Case caseDepart = plateau.getCase(agent);
        assertEquals(1, caseDepart.getAgentites().size());
        assertEquals(agent, plateau.enleverAgentite(caseDepart.getPosition(), agent));
        assertEquals(0, caseDepart.getAgentites().size());
        assertNull(plateau.getListeAgentites().get(agent));
    }

    @Test
    public void deplacerAgentTest() {
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
    public void ramasserEntiteTest() {
        AbstractEntite entite = Mockito.mock(AbstractEntite.class, Mockito.CALLS_REAL_METHODS);
        AbstractEntite autreEntite = Mockito.mock(AbstractEntite.class, Mockito.CALLS_REAL_METHODS);

        // Test ramasser obstacle
        assertFalse(plateau.ramasserEntite(agent, new Obstacle("obstacle", plateau)));

        // Test ramasser ramasser entité si déja chargé
        when(((AbstractAgentSitue) agent).getEntitePortee()).thenReturn(entite);
        plateau.placerAgentite(new Position(2, 2), entite);
        assertFalse(plateau.ramasserEntite(agent, entite));

        // Test ramasser entité pas devant
        when(((AbstractAgentSitue) agent).getDirection()).thenReturn(Direction.SO);
        assertFalse(plateau.ramasserEntite(agent, entite));

        // Test ramasser entité eloignée
        plateau.placerAgentite(new Position(1, 1), autreEntite);
        assertFalse(plateau.ramasserEntite(agent, autreEntite));

        // Test ramasser entité normalement
        when(((AbstractAgentSitue) agent).getDirection()).thenReturn(Direction.S);
        when(((AbstractAgentSitue) agent).getEntitePortee()).thenReturn(null);
        assertTrue(plateau.ramasserEntite(agent, entite));
    }

    @Test
    public void deposerEntiteTest() {
        AbstractEntite entite = Mockito.mock(AbstractEntite.class, Mockito.CALLS_REAL_METHODS);
        AbstractEntite autreEntite = Mockito.mock(AbstractEntite.class, Mockito.CALLS_REAL_METHODS);

        assertFalse(plateau.deposerEntite(agent, entite));
        ((AbstractAgentSitue) agent).setEntitePortee(entite);
        assertTrue(plateau.deposerEntite(agent, entite));
        assertFalse(plateau.deposerEntite(agent, autreEntite));
    }

    @Test
    public void getVoisinageTest() {
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
    public void getCaseTest() {
        assertEquals(new Case(new Position(2,3)), plateau.getCase(agent));
    }

}