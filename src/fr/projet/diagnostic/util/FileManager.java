package fr.projet.diagnostic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TreeMap;	

public class FileManager {

	private File file;
	private BufferedReader br = null;
	
	public FileManager(File file) {
		this.file = file; 
	}
	
	public void open() throws FileNotFoundException {
		this.br = new BufferedReader(new FileReader(this.file));
	}
	
	public String readLine() throws IOException {
		 return br.readLine();
	}
	
}