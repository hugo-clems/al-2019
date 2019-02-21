package systemeRecommandation;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import interfaces.IRecommandation;
import org.junit.*;

public class SystemeRecommandationTest {

    private IRecommandation iRecommandation;
    private Composant composant1;
    private Composant composant2;
    private Port portR;
    private Port portF;
    private Configuration configuration;
    private Connexion connexion;

    @Before
    public void setUp() {
        iRecommandation = new SystemeRecommandation();

        composant1 = new Composant("comp1");
        composant2 = new Composant("comp2");

        portR = new Port(composant1,"s1");
        portF = new Port(composant2,"s1");

        composant1.ajouterPortRequis(portR);
        composant2.ajouterPortFourni(portF);

        connexion = new Connexion(portF, portR);

        configuration = new Configuration();
        configuration.ajouterConnexion(connexion);
    }

    @Test
    public void testCalculValeurInteretOK() {
        // Given

        // When

        // Then

    }

}