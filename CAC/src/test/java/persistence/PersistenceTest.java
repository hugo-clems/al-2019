package persistence;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

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

		portR = new Port(UUID.randomUUID(), composant1, "idR");
		portF = new Port(UUID.randomUUID(), composant2, "idF");

		composant1.ajouterPortRequis(portR);
		composant2.ajouterPortFourni(portF);

		connexion = new Connexion(portF, portR);

		configuration = new Configuration();
		configuration.setId("01");
		configuration.ajouterConnexion(connexion);
		persistance = new Persistence();
	}

	@Test
	public void testSauvegarder() throws IOException {
		ArrayList<Connexion> connexionsAPersister = new ArrayList<Connexion>();
		connexionsAPersister.add(connexion);
		persistance.sauvegarderConnexion(connexionsAPersister);
		ArrayList<Connexion> getted = persistance.trouverTous();
		assert (connexionsAPersister.containsAll(getted));
		PrintWriter pw = new PrintWriter(Persistence.FILENAME);
		pw.close();
	}

	@Test
	public void testTrouverTous() throws IOException {
		ArrayList<Connexion> connexionsAPersister = new ArrayList<Connexion>();
		connexionsAPersister.add(connexion);
		persistance.sauvegarderConnexion(connexionsAPersister);
		ArrayList<Connexion> getted = persistance.trouverTous();
		assert (connexionsAPersister.containsAll(getted));
	}

//	@Test
//	public void testTrouverParId() throws IOException {
//		ArrayList<Connexion> connexionsAPersister = new ArrayList<Connexion>();
//		connexionsAPersister.add(connexion);
//		persistance.sauvegarderConnexion(connexionsAPersister);
//		Configuration getted = persistance.trouverParID(configuration.getId());
//		assert (configuration.equals(getted));
//	}

	@Test
	public void testSupprimerToutFonctionner() throws IOException {
		ArrayList<Connexion> connexionsAPersister = new ArrayList<Connexion>();
		connexionsAPersister.add(connexion);
		persistance.sauvegarderConnexion(connexionsAPersister);
		assertTrue(persistance.supprimerTout());
	}

	@Test
	public void testSupprimerToutListeVide() throws IOException {
		ArrayList<Connexion> connexionsAPersister = new ArrayList<Connexion>();
		connexionsAPersister.add(connexion);
		persistance.sauvegarderConnexion(connexionsAPersister);
		persistance.supprimerTout();
		ArrayList<Connexion> retrieved = persistance.trouverTous();
		assertTrue(retrieved.isEmpty());
	}

	@Test
	public void testSupprimerToutVideFichier() throws IOException {
		ArrayList<Connexion> connexionsAPersister = new ArrayList<Connexion>();
		connexionsAPersister.add(connexion);
		persistance.sauvegarderConnexion(connexionsAPersister);
		persistance.supprimerTout();
		String content = Persistence.readFile(Persistence.FILENAME, StandardCharsets.UTF_8);
		assertTrue(content.isEmpty());
	}

	@Test
	public void testTrouverConnexion() throws IOException {
		ArrayList<Connexion> expectedList = new ArrayList<Connexion>();
		ArrayList<Connexion> actualList = new ArrayList<Connexion>();
		
		/* On met la note à 1 */
		connexion.incrementerNote();
		
		/* On l'ajoute à la liste à persister */
		expectedList.add(connexion);
		
		/* On la persiste */
//		System.out.println(connexion.getNbLikes());
		persistance.sauvegarderConnexion(expectedList);
		
		/* Et on met sa note à 0 */
		connexion.setNbApprobation(0);
		
		/* Lorsqu'on passe la liste en paramètre */
		actualList = persistance.trouverConnexion(expectedList);
//		System.out.println(actualList.get(0).getNbLikes());
		
		/*on doit avoir la note remise à jour */
		assertTrue(actualList.get(0).getNbApprobation() == 1);
	}
}
