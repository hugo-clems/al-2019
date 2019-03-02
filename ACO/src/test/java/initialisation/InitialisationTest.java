package initialisation;


import entites.Nid;
import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;
import plateau.Case;
import plateau.IAgentite;
import plateau.Position;

import java.util.List;

import static org.junit.Assert.*;

public class InitialisationTest {

    private Initialisation initTest;

    @Before
    public void setUp(){
        String xml_location="src/test/java/initialisation/";
        String xml_filename="initialisation.xml";
        initTest= new Initialisation(xml_location,xml_filename);
    }

    @Test
    public void testPlateauSize(){
        int ligne=40;
        int colonne = 40;
        assertEquals(initTest.getPlateauACO().getColonne(),colonne);
        assertEquals(initTest.getPlateauACO().getLigne(),ligne);
    }

    @Test
    public void testPositionFourmilliere(){
        int x=24;
        int y=20;
        int y2=21;
        assertTrue(initTest.nidPresentDansCase(initTest.getPlateauACO().getCases().get(new Position(x,y))));
        assertFalse(initTest.nidPresentDansCase(initTest.getPlateauACO().getCases().get(new Position(x,y2))));
        assertTrue(x < initTest.getPlateauACO().getLigne());
        assertTrue(y < initTest.getPlateauACO().getColonne());
    }

    @Test
    public void testNbFourmis(){
        int x=24;
        int y=20;
        int expected=13;
        Nid nid = null;
        List<IAgentite> agentites_case = initTest.getPlateauACO().getCases().get(new Position(x,y)).getAgentites();
        for (IAgentite agentite_case : agentites_case) {
            if(agentite_case instanceof Nid){
                nid = (Nid)agentite_case;
            }
        }
        assertNotNull(nid);
        assertEquals(expected,nid.getNbFourmis());
    }

    @Test
    public void testPositionObstacle(){
        Position obstacle_1 = new Position(5,5);
        Position obstacle_2 = new Position(5,6);
        Position obstacle_3 = new Position(5,7);
        assertTrue(initTest.obstaclePresentDansCase(initTest.getPlateauACO().getCases().get(obstacle_1)));
        assertTrue(initTest.obstaclePresentDansCase(initTest.getPlateauACO().getCases().get(obstacle_2)));
        assertFalse(initTest.obstaclePresentDansCase(initTest.getPlateauACO().getCases().get(obstacle_3)));
    }


}