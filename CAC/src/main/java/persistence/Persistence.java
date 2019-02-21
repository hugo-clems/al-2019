package persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import domaine.Configuration;
import interfaces.IPersistence;

public class Persistence implements IPersistence{

	@Override
	public boolean trouverTous() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Configuration trouverParID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supprimerTout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sauvegarder(ArrayList<Configuration> configurationsAPersister) {
		// TODO Auto-generated method stub
		
		Genson genson = new GensonBuilder().setSkipNull(true).useClassMetadata(true).useRuntimeType(true).create();
		String res = genson.serialize(configurationsAPersister);
		
		 OutputStream os = null;
	        try {
	            os = new FileOutputStream(new File("../../resources/bdd.json"));
	            os.write(res.getBytes(), 0, res.length());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                os.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		return false;
	}

}
