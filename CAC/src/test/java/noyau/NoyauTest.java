package noyau;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import ihm.IVue;
import impl.SystemeRecommandation;
import enumeration.Appreciation;
import interfaces.IConnexion;
import interfaces.IPersistence;
import interfaces.IRecommandation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import persistence.Persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NoyauTest {

    private Noyau noyau;
    private IVue vue;
    private IConnexion connexion;
    private IPersistence persistence;
    private IRecommandation recommandation;
    private ArrayList<Configuration> configurations;



    @Before
    public void setUp() {

        noyau = new Noyau(vue, connexion, persistence, recommandation);


        Port port1 = new Port(new Composant(),"sevice1","id1");
        Port port2 = new Port(new Composant(),"sevice2","id2");
        Port port3 = new Port(new Composant(),"sevice3","id3");
        Port port4 = new Port(new Composant(),"sevice4","id4");

        Connexion connexion1 = new Connexion(port1,port2);
        Connexion connexion2 = new Connexion(port3,port4);
        Connexion connexion3 = new Connexion(port1,port4);
        Connexion connexion4 = new Connexion(port3,port2);

        Configuration configuration1 = new Configuration();
        Configuration configuration2 = new Configuration();

        //recommandation = new SystemeRecommandation();
        persistence = new Persistence();
        configurations = new ArrayList<>();
        Set<Connexion> connexions1 = new HashSet<>();
        Set<Connexion> connexions2 = new HashSet<>();

        connexions1.add(connexion1);
        connexions1.add(connexion2);
        configuration1.setConnexions(connexions1);
        connexions2.add(connexion3);
        connexions2.add(connexion4);
        configuration2.setConnexions(connexions2);

        configurations.add(configuration1);
        configurations.add(configuration2);

        noyau.setLesConfigurations(configurations);

    }

    @Test
    public void noterConfigurationAppreciationJAIME() {
        int indiceConfig = 0;
        Appreciation appreciation = Appreciation.JAIME;
        noyau.noterConfiguration(indiceConfig, appreciation);
        ArrayList<Connexion> connexionsP = new ArrayList<>();
        connexionsP.addAll(configurations.get(indiceConfig).getConnexions());
        ArrayList<Connexion> connexions = persistence.trouverConnexion(connexionsP);
        Assert.assertEquals(connexions.get(0).getNbApprobation(),1);
        Assert.assertEquals(connexions.get(1).getNbApprobation(),1);

    }

    @Test
    public void noterConfigurationAppreciationJAIMEPAS() {
        int indiceConfig = 1;
        Appreciation appreciation = Appreciation.JAIMEPAS;
        noyau.noterConfiguration(indiceConfig, appreciation);
        ArrayList<Connexion> connexionsP = new ArrayList<>();
        connexionsP.addAll(configurations.get(indiceConfig).getConnexions());
        ArrayList<Connexion> connexions = persistence.trouverConnexion(connexionsP);
        Assert.assertEquals(connexions.get(0).getNbApprobation(),-1);
        Assert.assertEquals(connexions.get(1).getNbApprobation(),-1);
    }

}
