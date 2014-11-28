package br.ucb.controller.persistence;

import java.io.FileWriter;
import java.io.IOException;

public class FilePersistence {
	public void persist(String fileName,Object obj){
		FileWriter fr;
		try {
			fr = new FileWriter(fileName, true);
			fr.write(obj.toString()+"\n");
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
