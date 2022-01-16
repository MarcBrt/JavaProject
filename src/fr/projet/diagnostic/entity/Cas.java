package fr.projet.diagnostic.entity;

import java.util.ArrayList;
import java.util.List;

public class Cas {
    public int id;
    public List<Triplet> p = new ArrayList<>();
    public Etat s;

    public Cas(int id, List<Triplet> triplets, Etat s) {
        if (triplets.size() >= 2) {
            this.id = id;
            this.p = triplets;
            this.s = s;
        }
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Triplet> getP() {
        return p;
    }
    
    public int tripletCount() {
    	return p.size();
    }

    public void setP(ArrayList<Triplet> p) {
        this.p = p;
    }

    public Etat getS() {
        return s;
    }

    public void setS(Etat s) {
        this.s = s;
    }
}
