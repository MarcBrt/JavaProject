package fr.projet.diagnostic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fr.projet.diagnostic.entity.Intervalle;
import fr.projet.diagnostic.entity.Triplet;
import fr.projet.diagnostic.util.FileManager;

public class Main {

	public static void main(String[] args) {
		ArrayList<Triplet> triplets = analyse();

	}
	
	public static ArrayList<Triplet> analyse() {
		FileManager fm = new FileManager(new File("regle.txt"));
		int cptTriplet = 0;
		ArrayList<Triplet> triplets = new ArrayList<>();
		try {
			List<String> lines = fm.readAllLine();
			System.out.println(lines.get(0));

			for (String line: lines) {
				int cptIntervale = 0;
				line.replaceAll("[(]", "");
				line.replaceAll("[)]", "");
				String[] separetedLine = line.split(" * ");
				for( String tripletLine: separetedLine) {
					String[] tripletOccurence = tripletLine.split(",");
					if( tripletOccurence[2].contentEquals("nct") ) {
						Intervalle intervalle = new Intervalle(cptIntervale);
						cptIntervale++;
						Triplet triplet = new Triplet(cptTriplet,
								tripletOccurence[0],
								tripletOccurence[1],
								intervalle);
						triplets.add(triplet);
					}
					else {
						int intervalle1 = Integer.parseInt( tripletOccurence[2].split(",")[0].replace("(","") );
						int intervalle2 = Integer.parseInt( tripletOccurence[2].split(",")[1].replace(")","") );
						Intervalle intervalle = new Intervalle(cptIntervale, intervalle1, intervalle2);
						cptIntervale++;
						Triplet triplet = new Triplet(cptTriplet,
								tripletOccurence[0],
								tripletOccurence[1],
								intervalle);
						triplets.add(triplet);
					}
					cptTriplet++;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return triplets;
	}

}
