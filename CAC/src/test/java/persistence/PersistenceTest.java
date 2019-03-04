package persistence;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;

public class PersistenceTest {
	private Composant composant1;
	private Composant composant2;
	private Port portR;
	private Port portF;
	private Configuration configuration;
	private Connexion connexion;
	private Persistence persistance;
	
	@Before
	public void setUp() {

		composant1 = new Composant("comp1");
		composant2 = new Composant("comp2");

		portR = new Port(null, "s1");
		portF = new Port(null, "s1");

		composant1.ajouterPortRequis(portR);
		composant2.ajouterPortFourni(portF);

		connexion = new Connexion(portF, portR);

		configuration = new Configuration();
		configuration.ajouterConnexion(connexion);
		persistance = new Persistence();
	}

	@Test
	    public void testSauvegarder() throws IOException {
		 ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		 configurationsAPersister.add(configuration);
		 persistance.sauvegarder(configurationsAPersister);
		 OutputStream os = null;
		 InputStream hey;
		 // /home/louis/git/al-2019-2/CAC/src/main/resources/bdd.json
		 // /home/louis/git/al-2019-2/CAC/src/test/java/persistence/PersistenceTest.java
//		 try {
//			 hey = PersistenceTest.class.getResourceAsStream("bdd.json");
//			
////		     os = new FileOutputStream(new File("../../../../main/resources/bdd.json"));
//		 }catch (IOException e) {
//	            e.printStackTrace();
//	        }finally{
//	            try {
//	                os.close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//		 }
		 //to empty test file
		 PrintWriter pw = new PrintWriter("src/main/resources/bdd.json");
		 pw.close();
			 System.out.println("hey");
//		 assert(hey.read() != -1);
	 }
	@Test
	public void testTrouverTous() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		ArrayList<Configuration> getted = persistance.trouverTous();
		assert(configurationsAPersister.equals(getted));
	}

	@Test
	public void testSupprimerToutFonctionner() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		assertTrue(persistance.supprimerTout());
	}

	@Test
	public void testSupprimerToutListeVide() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		persistance.supprimerTout();
		
		ArrayList<Configuration> retrieved = persistance.trouverTous();
		assertTrue(retrieved.size() == 0);
	}

	@Test
	public void testSupprimerToutVideFichier() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		persistance.supprimerTout();
		
		String content = Persistence.readFile("src/main/resources/bdd.json", StandardCharsets.UTF_8);
		assertTrue(content.isEmpty());
	}
}
