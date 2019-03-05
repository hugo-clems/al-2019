package persistence;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
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
		configuration.setId("01");
		configuration.ajouterConnexion(connexion);
		persistance = new Persistence();
	}

	@Test
	public void testSauvegarder() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		ArrayList<Configuration> getted = persistance.trouverTous();
		assert (configurationsAPersister.containsAll(getted));
		PrintWriter pw = new PrintWriter(Persistence.FILENAME);
		pw.close();
	}

	@Test
	public void testTrouverTous() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		ArrayList<Configuration> getted = persistance.trouverTous();
		assert (configurationsAPersister.containsAll(getted));
	}

	@Test
	public void testTrouverParId() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		Configuration getted = persistance.trouverParID(configuration.getId());
		assert (configuration.equals(getted));
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
		assertTrue(retrieved.isEmpty());
	}

	@Test
	public void testSupprimerToutVideFichier() throws IOException {
		ArrayList<Configuration> configurationsAPersister = new ArrayList<Configuration>();
		configurationsAPersister.add(configuration);
		persistance.sauvegarder(configurationsAPersister);
		persistance.supprimerTout();
		String content = Persistence.readFile(Persistence.FILENAME, StandardCharsets.UTF_8);
		assertTrue(content.isEmpty());
	}
}
