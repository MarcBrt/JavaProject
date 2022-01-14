package fr.projet.diagnostic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;	

public class FileManager {

	private File file;
	private BufferedReader br = null;
	
	public FileManager(File file) {
		this.file = file; 
	}
	
	private void open() throws FileNotFoundException {
		this.br = new BufferedReader(new FileReader(this.file));
	}
	
	public String readLine() throws IOException {
		
		if (this.br == null) {
			this.open();
		}
		
		 return br.readLine();
	}
	
	public List<String> readAllLine() throws IOException {
		
		if (this.br == null) {
			this.open();
		}
	
		return this.br.lines().collect(Collectors.toList());
	}
	
}