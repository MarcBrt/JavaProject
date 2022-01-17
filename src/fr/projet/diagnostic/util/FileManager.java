package fr.projet.diagnostic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;	

public class FileManager {

	private File file;
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	
	public FileManager(File file) {
		this.file = file; 
	}
	
	private void openReader() throws IOException {
		this.br = new BufferedReader(new FileReader(this.file));
	}
	
	private void openWriter() throws IOException {
		this.bw = new BufferedWriter(new FileWriter(this.file, true));
	}
	
	private void closeReader() throws IOException{
		
		if (this.br == null) {
			this.openReader();
		}
		
		this.br.close();
		this.br = null;
	}
	
	private void closeWriter() throws IOException{
		
		if (this.bw == null) {
			this.openWriter();
		}
		
		this.bw.close();
		this.bw = null;
	}
	
	public String readLine() throws IOException {
		
		if (this.br == null) {
			this.openReader();
		}
		
		 String line = br.readLine();
		 this.closeReader();
		 return line;
	}
	
	public List<String> readAllLine() throws IOException {
		
		if (this.br == null) {
			this.openReader();
		}
	
		return this.br.lines().collect(Collectors.toList());
	}
	
	public void writeLine(String line) throws IOException {
		
		if (this.bw == null) {
			this.openWriter();
		}
		
		List<String> lines = this.readAllLine();
		if (!lines.isEmpty()) {
			this.bw.newLine();
		}
		this.bw.write(line);
		this.bw.flush();
		this.closeWriter();
		
	}
	
}