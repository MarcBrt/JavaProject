package fr.projet.diagnostic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.projet.diagnostic.entity.Cas;
import fr.projet.diagnostic.entity.Failing;
import fr.projet.diagnostic.entity.Intervalle;
import fr.projet.diagnostic.entity.NotIdentified;
import fr.projet.diagnostic.entity.Triplet;
import fr.projet.diagnostic.util.FileManager;

public class Main {

	public static void main(String[] args) {

		File fileCas = new File("regle.txt");
		List<Cas> cass = analyse(fileCas);
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

		File fileTestedCas = new File("test.txt");
		List<Cas> testedCase = analyse(fileTestedCas);

		// Ajout de l'instance du calculateur de similarite
		int ligne = 0;
		SimilarityCore similarityCore = new SimilarityCore(groupes.get(testedCase.get(ligne).tripletCount()), testedCase.get(ligne));

		System.out.println("Similarite des cas par cas: \n");

		
		for (Entry<Integer, Float> entry : similarityCore.tableau.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		File file = new File("error.txt");
		file.delete();
		FileManager fm = new FileManager(file);
		
		ErrorGeneratorCore egc = new ErrorGeneratorCore(cass);
		
		try {
			fm.writeLine("Erreur g?n?r? ? partir du fichier " + fileCas.getAbsolutePath());
			fm.writeLine("");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int idCas = -1;
		for (Cas cas : egc.generateError()) {
			try {
				String triplet = "";
				if (idCas != cas.getId()) {
					fm.writeLine("");
					idCas = cas.getId();
					fm.writeLine("Cas N?" + idCas);
				}
				for (Triplet trip : cas.getP()) {
					triplet += String.format("(%s, %s, %s) * ", trip.er, trip.ec, trip.ct.bi == 0 && trip.ct.bs == 0 ? "nct" : "[" + trip.ct.bi + ", " + trip.ct.bs + "]");
				}
				triplet = triplet.substring(0, triplet.length() - 3);
				fm.writeLine(triplet + " " + ((Failing) cas.getS()).description);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Fichier des erreurs enregistr? dans " + file.getAbsolutePath());
		

	}

	/**
	 * Analyse un fichier afin de retourner la distance entre 2 cas.
	 * @param filename
	 * @return List<Cas>
	 */
	public static List<Cas> analyse(File file) {
		FileManager fm = new FileManager(file);
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
