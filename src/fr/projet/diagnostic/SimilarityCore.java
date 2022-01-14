package fr.projet.diagnostic;

import fr.projet.diagnostic.entity.Intervalle;

public class SimilarityCore {

	public int calculPos(int verif, Intervalle bdd) {
		if (verif >= bdd.getBi() && verif <= bdd.getBs()) {
			return 0;
		} else {
			return Math.min(Math.abs(verif - bdd.getBi()), Math.abs(verif - bdd.getBs()));
		}
	}
	
	public int calculMinPos(Intervalle int1, Intervalle int2) {
		return Math.min(this.calculPos(int1.getBi(), int2), this.calculPos(int2.getBs(), int2));
	}
	
	public int calculIpos(Intervalle verif, Intervalle bdd) {
		
		if (verif.getBi() >= Math.max(verif.getBi(), bdd.getBi()) && verif.getBi() <= Math.min(verif.getBs(), bdd.getBs())) {
			return 0;
		} else {
			return this.calculMinPos(verif, bdd);
		}
	}
	
}
