package fr.projet.diagnostic.entity;

import java.util.ArrayList;

public class Cas {
    public int id;
    public ArrayList<Triplet> p;
    public Etat s;

    public Cas(int id, ArrayList<Triplet> p, Etat s) {
        if (p.size() >= 2) {
            this.id = id;
            this.p = p;
            this.s = s;
        }
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Triplet> getP() {
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
