package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import interfaces.IPersistence;

/**
 * Cette classe est le composant offrant les services de persistence
 * Pour se faire, elle écrit dans un fichier qu'il ne faut pas modifier !!
 * cf . attribut filename
 * @author rialy
 * L'enregistrement se fait à l'aide de la sérialization Genson 
 */
public class Persistence implements IPersistence {

	public static final String FILENAME = "src/main/resources/bdd.json";

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public ArrayList<Connexion> trouverTous() {
		// TODO Auto-generated method stub
		ArrayList<Connexion> res = new ArrayList<Connexion>();
		try {
			String content = readFile(FILENAME, StandardCharsets.UTF_8);
			Genson genson = new GensonBuilder().setSkipNull(true)/* .useClassMetadata(true) */.useRuntimeType(true)
					.create();

			ArrayList<Connexion> resultv = genson.deserialize(content, new GenericType<ArrayList<Connexion>>() {
			});
			if (!(resultv == null || resultv.isEmpty())) {
				res = resultv;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}

//	public Configuration trouverParID(String id) {
//		List<Connexion> connexions = trouverTous();
//		List<Connexion> filtree = connexions.stream().filter(conf -> conf.getId().equals(id))
//				.collect(Collectors.toList());
//		Connexion res = filtree.size() != 0 ? filtree.get(0) : null;
//		return res;
//	}

	@Override
	public boolean supprimerTout() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(FILENAME);
			pw.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean sauvegarder(ArrayList<Configuration> configurationsAPersister) {
		// TODO Auto-generated method stub

		Genson genson = new GensonBuilder().setSkipNull(true)/* .useClassMetadata(true) */.useRuntimeType(true)
				.create();
		String res = genson.serialize(configurationsAPersister);

		OutputStream os = null;
		try {
			File newfile = new File(FILENAME);
			os = new FileOutputStream(newfile);
			os.write(res.getBytes(), 0, res.length());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean sauvegarderConnexion(ArrayList<Connexion> connexionAPersister) {
		// TODO Auto-generated method stub

		Genson genson = new GensonBuilder().setSkipNull(true).exclude(Port.class)/* .useClassMetadata(true) */.useRuntimeType(true)
				.create();
		String res = genson.serialize(connexionAPersister);

		OutputStream os = null;
		try {
			File newfile = new File(FILENAME);
			os = new FileOutputStream(newfile);
			os.write(res.getBytes(), 0, res.length());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Connexion> trouverConnexion(ArrayList<Connexion> array) {
		ArrayList<Connexion> res = new ArrayList<Connexion>();
		try {
			String content = readFile(FILENAME, StandardCharsets.UTF_8);
			Genson genson = new GensonBuilder().setSkipNull(true)/* .useClassMetadata(true) */.useRuntimeType(true)
					.create();

			ArrayList<Connexion> resultv = genson.deserialize(content, new GenericType<ArrayList<Connexion>>() {
			});
			for(Connexion a : array) {
				res.add(a);
				for(Connexion c : resultv) {
					if(c.equalsIdPort(a)) {
						a.setNbApprobation(c.getNbApprobation());
					}					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
