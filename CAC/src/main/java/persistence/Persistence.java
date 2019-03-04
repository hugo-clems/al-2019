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

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import domaine.Configuration;
import interfaces.IPersistence;

public class Persistence implements IPersistence {

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	@Override
	public ArrayList<Configuration> trouverTous() {
		// TODO Auto-generated method stub
		try {
			String content = readFile("src/main/resources/bdd.json", StandardCharsets.UTF_8);
			Genson genson = new GensonBuilder().setSkipNull(true)/* .useClassMetadata(true) */.useRuntimeType(true)
					.create();
			ArrayList<Configuration> resultv = genson.deserialize(content, new GenericType<ArrayList<Configuration>>() {});
			return resultv;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Configuration trouverParID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supprimerTout() {
		PrintWriter pw;
		try {
			pw = new PrintWriter("src/main/resources/bdd.json");
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
			File newfile = new File("src/main/resources/bdd.json");
			System.out.println(newfile.canRead());
			os = new FileOutputStream(newfile);
			os.write(res.getBytes(), 0, res.length());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
