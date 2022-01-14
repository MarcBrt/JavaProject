package fr.projet.diagnostic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.projet.diagnostic.util.FileManager;

public class Main {

	public static void main(String[] args) {
		analyse();

	}
	
	public static void analyse() {
		FileManager fm = new FileManager(new File("regle.txt"));
		try {
			List<String> lines = fm.readAllLine();
			System.out.println(lines.get(0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
