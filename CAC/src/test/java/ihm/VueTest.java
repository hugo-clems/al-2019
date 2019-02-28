package ihm;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VueTest {

    private VueImpl vueImpl = new VueImpl();
    private Configuration configuration;
    private Connexion connexion1;
    private Connexion connexion2;

    @Before
    public void setUp(){
        this.configuration = new Configuration();
        //Création de composants
        Composant pc = new Composant("PC");
        Composant videoProj = new Composant("VIDEO-PROJ");
        //Ajout des ports
        Port pr = new Port(videoProj, "IMAGE");
        Port pf = new Port(pc, "IMAGE");
        pc.ajouterPortFourni(pf);
        videoProj.ajouterPortRequis(pr);

        //Création de composants
        Composant tel = new Composant("TELEPHONE");
        Composant enceinte = new Composant("ENCEINTE");
        //Ajout des ports
        Port pf2 = new Port(tel, "SON");
        Port pr2 = new Port(enceinte, "SON");
        tel.ajouterPortFourni(pf2);
        enceinte.ajouterPortRequis(pr2);

        //Création des connexions
        this.connexion1 = new Connexion(pf, pr);
        this.connexion2 = new Connexion(pf2, pr2);
        this.configuration.ajouterConnexion(connexion1);
        this.configuration.ajouterConnexion(connexion2);
    }

    @Test
    public void testAffichageConnexion(){
        assertEquals(this.connexion1.toString(), "[PC][PROV]IMAGE - [VIDEO-PROJ][REQ]IMAGE");
        this.vueImpl.afficherConfiguration(configuration);
    }

}
