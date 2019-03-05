package impl;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import interfaces.SystemeRecommandation;
import org.junit.*;

import java.util.UUID;

public class SystemeRecommandationSimpleTest {

    private SystemeRecommandation systemeRecommandation;
    private Composant composant1;
    private Composant composant2;
    private Port portR;
    private Port portF;
    private Configuration configuration;
    private Connexion connexion;

    @Before
    public void setUp() {
        systemeRecommandation = new SystemeRecommandationSimple();

        composant1 = new Composant("comp1");
        composant2 = new Composant("comp2");

        portR = new Port(composant1,"s1", UUID.randomUUID());
        portF = new Port(composant2,"s1",UUID.randomUUID());

        composant1.ajouterPortRequis(portR);
        composant2.ajouterPortFourni(portF);

        connexion = new Connexion(portF, portR);

        configuration = new Configuration();
        configuration.ajouterConnexion(connexion);
    }

    @Test
    public void testCalculValeurInteretOK() {
        // Given
        configuration.setNbApprobation(5);
        configuration.setNbOccurence(5);

        // When
        int vi = systemeRecommandation.calculValeurInteret(configuration);

        // Then
        Assert.assertEquals(100, vi);
    }

}