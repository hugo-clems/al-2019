package domaine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class AgentComposantTest {

    private AgentComposant agent1;
    private AgentComposant agent2;
    private AgentComposant agent3;

    private Composant composant1;
    private Composant composant2;
    private Composant composant3;

    private Port portS1,portS2;


    private  Noyeau noyeau = new Noyeau();
    @Before
    public void setUp (){

        composant1 = new Composant("comp1");
        composant1.ajouterPortRequis(new Port(composant1,"s1"));

        composant2 = new Composant("comp2");
        composant2.ajouterPortFourni(new Port(composant2,"s1"));
        composant2.ajouterPortFourni(new Port(composant2,"s2"));

        composant3 = new Composant("comp3");
        composant3.ajouterPortFourni(new Port(composant3,"s1"));
        composant3.ajouterPortRequis(new Port(composant3,"s2"));

        agent1 = new AgentComposant(composant1,noyeau);
        agent2 = new AgentComposant(composant2,noyeau);
        agent3 = new AgentComposant(composant3,noyeau);


    }

    @Test
    public void testBroadcast() {

        agent1.broadcast();

        assertEquals(1, agent2.recevoirMessage().size());
        assertEquals(1 ,agent3.recevoirMessage().size());

    }

    @Test
    public void testTrouverUneConnexionPossible() {

        agent1.broadcast();
        agent2.envoyerConnexionPossible();

        List<MessageAgentAuNoyeau> msg = new ArrayList<>();
        msg= noyeau.recevoirMessage();
        //On verifie qu'il recois bien un messsage
        assertEquals(1,noyeau.recevoirMessage().size());
        //On verifie qu'il a recu des connexion possible
        assertEquals(1,noyeau.getConnexionsPossbile().size());
    }

    @Test
    public  void testTrouverPlusieurConnexionPossible(){

        /*Tous les agent broadcast*/
        agent1.broadcast();
        agent2.broadcast();
        agent3.broadcast();
        /*Une fois tout le monde au courant des port requies on envoie au noyeau*/
        agent1.envoyerConnexionPossible();
        agent2.envoyerConnexionPossible();
        agent3.envoyerConnexionPossible();
        /*On met a jour le noyeau en recuperant les messages des agents*/
        noyeau.recevoirMessage();
        List<Connexion> connexions = noyeau.getConnexionsPossbile();
        //Le noyeau recoit que 2 message car agent 1 ne peut se connecter avec personne
        assertEquals(2,noyeau.recevoirMessage().size());
        //Il y a 3 connexions possible
        assertEquals(3,noyeau.getConnexionsPossbile().size());


    }


}