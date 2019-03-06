package systemeRecommandation;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import interfaces.IRecommandation;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.util.*;

public class SystemeRecommandationTest {

    private IRecommandation iRecommandation;

    private List<Composant> composants;

    private List<Port> portsRequis;

    private List<Port> portsFournis;

    @Before
    public void setUp() {
        iRecommandation = new SystemeRecommandation();

        composants = Arrays.asList(
                new Composant("comp0"),
                new Composant("comp1"),
                new Composant("comp2"),
                new Composant("comp3")
        );

        portsRequis = Arrays.asList(
                new Port(composants.get(0), "s0"),
                new Port(composants.get(0), "s1"),
                new Port(composants.get(1), "s2"),
                new Port(composants.get(2), "s0"),
                new Port(composants.get(2), "s2"),
                new Port(composants.get(3), "s3")
        );

        portsFournis = Arrays.asList(
                new Port(composants.get(0), "s2"),
                new Port(composants.get(1), "s1"),
                new Port(composants.get(2), "s1"),
                new Port(composants.get(2), "s3"),
                new Port(composants.get(3), "s0"),
                new Port(composants.get(3), "s2")
        );
    }

    @Test
    public void testCalculValeurInteretOK() {
        // Given
        Connexion connexion = new Connexion(portsFournis.get(0), portsRequis.get(1), 10, 0);
        int valeurInteret;

        // When
        connexion.setNbApprobation(0);
        valeurInteret = iRecommandation.calculerValeurInteret(connexion);

        // Then
        Assert.assertThat(valeurInteret, CoreMatchers.equalTo(0));

        // When
        connexion.setNbApprobation(5);
        valeurInteret = iRecommandation.calculerValeurInteret(connexion);

        // Then
        Assert.assertThat(valeurInteret, CoreMatchers.equalTo(50));

        // When
        connexion.setNbApprobation(10);
        valeurInteret = iRecommandation.calculerValeurInteret(connexion);

        // Then
        Assert.assertThat(valeurInteret, CoreMatchers.is(100));
    }

    @Test
    public void testCreerConfigurations() {
        // Given
        List<Configuration> listeConfiguration;
        List<Connexion> connexions = Arrays.asList(
                // s0
                new Connexion(portsFournis.get(4), portsRequis.get(0), 100, 75),
                new Connexion(portsFournis.get(4), portsRequis.get(3), 100 ,90),

                // s1
                new Connexion(portsFournis.get(1), portsRequis.get(1), 100, 100),
                new Connexion(portsFournis.get(2), portsRequis.get(1), 100, 90),

                // s2
                new Connexion(portsFournis.get(0), portsRequis.get(2), 100, 90),
                new Connexion(portsFournis.get(0), portsRequis.get(4), 100, 80),
                new Connexion(portsFournis.get(5), portsRequis.get(2), 100, 70),
                new Connexion(portsFournis.get(5), portsRequis.get(4), 100, 60),

                // s3
                new Connexion(portsFournis.get(3), portsRequis.get(5), 100, 100)
        );

        // Then
        listeConfiguration = new ArrayList<>(iRecommandation.creerConfigurations(connexions));

        Assert.assertThat(listeConfiguration.size(), CoreMatchers.is(1));
        Assert.assertFalse(listeConfiguration.get(0).getConnexions().contains(connexions.get(0)));
        Assert.assertThat(listeConfiguration.get(0).getConnexions(), CoreMatchers.hasItem(connexions.get(1)));

        // When
        connexions.get(0).setNbApprobation(90);

        // Then
        listeConfiguration = new ArrayList<>(iRecommandation.creerConfigurations(connexions));
        Assert.assertThat(listeConfiguration.size(), CoreMatchers.is(2));
        Assert.assertTrue(listeConfiguration.get(0).getConnexions().contains(connexions.get(0)) || listeConfiguration.get(0).getConnexions().contains(connexions.get(1)));
        Assert.assertTrue(listeConfiguration.get(1).getConnexions().contains(connexions.get(0)) || listeConfiguration.get(1).getConnexions().contains(connexions.get(1)));
    }

}