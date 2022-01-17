package fr.projet.diagnostic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.projet.diagnostic.entity.Cas;
import fr.projet.diagnostic.entity.Intervalle;
import fr.projet.diagnostic.entity.NotIdentified;
import fr.projet.diagnostic.entity.Triplet;
import fr.projet.diagnostic.util.FileManager;

public class Main {

	public static void main(String[] args) {

		List<Cas> cass = analyse("regle.txt");
		HashMap<Integer, List<Cas>> groupes = new HashMap<>();

		for (Cas cas : cass) {
			int count = cas.tripletCount();
			if (!groupes.containsKey(count)) {
				groupes.put(count, new ArrayList<>());
			}
			groupes.get(count).add(cas);
		}

//		for (Map.Entry<Integer, List<Cas>> entry : groupes.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue().size());
//		}

		List<Cas> testedCase = analyse("test.txt");

		// Ajout de l'instance du calculateur de similarite
		int ligne = 0;
		SimilarityCore similarityCore = new SimilarityCore(groupes.get(testedCase.get(ligne).tripletCount()), testedCase.get(ligne));

		System.out.println("Similarite des cas par cas: \n");

		
		for (Entry<Integer, Float> entry : similarityCore.tableau.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}

	/**
	 * Analyse un fichier afin de retourner la distance entre 2 cas.
	 * @param filename
	 * @return List<Cas>
	 */
	public static List<Cas> analyse(String filename) {
		FileManager fm = new FileManager(new File(filename));
		int cptTriplet = 0;
		List<Cas> cass = new ArrayList<>();
		try {
			List<String> lines = fm.readAllLine();

			int cptCas = 0;
			for (String line : lines) {
				List<Triplet> triplets = new ArrayList<>();
				int cptIntervale = 0;
				line = line.replace(" ", "");
				line = line.replace("(", "");
				line = line.replace(")", "");
				String[] separetedLine = line.split("\\*");
				for (String tripletLine : separetedLine) {
					String[] tripletOccurence = tripletLine.split(",");
					if (tripletOccurence[2].contentEquals("nct")) {
						Intervalle intervalle = new Intervalle(cptIntervale);
						cptIntervale++;
						Triplet triplet = new Triplet(cptTriplet, tripletOccurence[0], tripletOccurence[1], intervalle);
						triplets.add(triplet);
					} else {
						int intervalle1 = Integer.parseInt(tripletOccurence[2].replace("(", "").replace("[", ""));
						int intervalle2 = Integer.parseInt(tripletOccurence[3].replace(")", "").replace("]", ""));
						Intervalle intervalle = new Intervalle(cptIntervale, intervalle1, intervalle2);
						cptIntervale++;
						Triplet triplet = new Triplet(cptTriplet, tripletOccurence[0], tripletOccurence[1], intervalle);
						triplets.add(triplet);
					}
					cptTriplet++;
				}
				Cas cas = new Cas(cptCas++, triplets, new NotIdentified(2));
				cass.add(cas);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return cass;
	}

}
