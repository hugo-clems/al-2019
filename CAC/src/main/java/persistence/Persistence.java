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
import java.util.List;
import java.util.stream.Collectors;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import domaine.Configuration;
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

	@Override
	public ArrayList<Configuration> trouverTous() {
		// TODO Auto-generated method stub
		ArrayList<Configuration> res = new ArrayList<Configuration>();
		try {
			String content = readFile(FILENAME, StandardCharsets.UTF_8);
			Genson genson = new GensonBuilder().setSkipNull(true)/* .useClassMetadata(true) */.useRuntimeType(true)
					.create();

			ArrayList<Configuration> resultv = genson.deserialize(content, new GenericType<ArrayList<Configuration>>() {
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

	@Override
	public Configuration trouverParID(String id) {
		List<Configuration> configurations = trouverTous();
		List<Configuration> filtree = configurations.stream().filter(conf -> conf.getId().equals(id))
				.collect(Collectors.toList());
		Configuration res = filtree.size() != 0 ? filtree.get(0) : null;
		return res;
	}

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

	@Override
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

}
