package fr.projet.diagnostic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.projet.diagnostic.entity.Intervalle;
import fr.projet.diagnostic.entity.NotIdentified;
import fr.projet.diagnostic.entity.Triplet;
import fr.projet.diagnostic.util.FileManager;

public class Main {

	public static void main(String[] args) {

		List<Cas> cass = analyse();
		System.out.println(cass.size());
		HashMap<Integer, List<Cas> groupes = new HashMap<Integer, List<Cas>>();

		for (Cas cas: cass) {
			int count = cas.tripletCount();
			if (!groupes.containsKey(count)) {
				groupes.put(count, new ArrayList<Cas>());
			}
			groupes.get(count).add(cas);
		}

		for (Entry<Integer, List<Cas>> entry : groupes.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue().size());
		}

	}

	public static List<Cas> analyse() {
		FileManager fm = new FileManager(new File("regle.txt"));
		int cptTriplet = 0;
		List<Cas> cass = new ArrayList<Cas>();
		try {
			List<String> lines = fm.readAllLine();
			System.out.println(lines.get(0));

			for (String line : lines) {
				List<Triplet> triplets = new ArrayList<Triplet>();
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
				Cas cas = new Cas(cptIntervale, triplets, new NotIdentified(2));
				cass.add(cas);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cass;
	}

}
