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

        // Test placer un agent sur un obstacle
        Position position = new Position(0, 0);
        assertFalse(plateau.placerAgentite(position, agentTest));

        // Test placer un agent sur une case vide
        position.setX(3);
        position.setY(3);
        assertTrue(plateau.placerAgentite(position, agentTest));
        Case mCase = plateau.getCases().get(position);
        assertEquals(mCase, plateau.getListeAgentites().get(agentTest));
        assertTrue(mCase.getAgentites().contains(agentTest));
        assertEquals(1, mCase.getAgentites().size());

        // Test placer un obstacle sur une entité
        assertFalse(plateau.placerAgentite(new Position(2, 3), new Obstacle("obstacleTEst")));

        // Test placer un agent dans une case contenant déjà un agent
        assertTrue(plateau.placerAgentite(position, agent));
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
        assertFalse(plateau.deplacerAgent(agent, Direction.S));
        assertEquals(ancienneCase.getAgentites().size(), 1);

        //l'agent passe en Position (1,2)
        assertTrue(plateau.deplacerAgent(agent, Direction.NO));
        assertEquals(plateau.getCase(agent), new Case(new Position(1,2)));
        assertTrue(ancienneCase.getAgentites().isEmpty());

        //l'agent passe en Position (1,1)
        ancienneCase = plateau.getCase(agent);
        assertTrue(plateau.deplacerAgent(agent, Direction.N));
        assertEquals(plateau.getCase(agent), new Case(new Position(1,1)));
        assertTrue(ancienneCase.getAgentites().isEmpty());

        //l'agent ne bouge pas
        ancienneCase = plateau.getCase(agent);
        assertFalse(plateau.deplacerAgent(agent, Direction.N));
        assertEquals(ancienneCase.getAgentites().size(), 1);

        //le plateau sait que l'agent est en Position (1,1)
        assertEquals(plateau.getListeAgentites().get(agent), new Case(new Position(1,1)));
    }

    @Test
    public void ramasserEntiteTest() {
        AbstractEntite entite = Mockito.mock(AbstractEntite.class, Mockito.CALLS_REAL_METHODS);
        AbstractEntite autreEntite = Mockito.mock(AbstractEntite.class, Mockito.CALLS_REAL_METHODS);

        // Test ramasser obstacle
        assertFalse(plateau.ramasserEntite(agent, new Obstacle("obstacle")));

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
        when(((AbstractAgentSitue) agent).getDirection()).thenReturn(Direction.N);
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
// SO SE S
    @Test
    public void getVoisinageTest() {
        Map<Direction, Case> voisinage = plateau.getVoisinage(agent);

        //Nord
        assertEquals (new Case(new Position(2, 4)), voisinage.get(Direction.S));
        assertEquals(voisinage.get(Direction.S).getAgentites().size(), 1);
        assertTrue(voisinage.get(Direction.S).getAgentites().get(0) instanceof Obstacle);

        //Nord Ouest
        assertEquals (new Case(new Position(1, 4)), voisinage.get(Direction.SO));
        assertEquals(voisinage.get(Direction.SO).getAgentites().size(), 1);
        assertTrue(voisinage.get(Direction.SO).getAgentites().get(0) instanceof Obstacle);

        // Nord Est
        assertEquals (new Case(new Position(3, 4)), voisinage.get(Direction.SE));
        assertEquals(voisinage.get(Direction.SE).getAgentites().size(), 1);
        assertTrue(voisinage.get(Direction.SE).getAgentites().get(0) instanceof Obstacle);

        // Sud Ouest
        assertEquals(new Case(new Position(1, 2)), voisinage.get(Direction.NO));
        assertTrue(voisinage.get(Direction.NO).getAgentites().isEmpty());
    }

    @Test
    public void getCaseTest() {
        assertEquals(new Case(new Position(2,3)), plateau.getCase(agent));
    }

}