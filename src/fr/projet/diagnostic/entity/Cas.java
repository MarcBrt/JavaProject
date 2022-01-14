package fr.projet.diagnostic.entity;

public class Cas {
    public int id;
    public Triplet p;

    public Cas(int id, Triplet p, Etat s) {
		super();
		this.id = id;
		this.p = p;
		this.s = s;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Triplet getP() {
        return p;
    }

    public void setP(Triplet p) {
        this.p = p;
    }

    public Etat getS() {
        return s;
    }

    public void setS(Etat s) {
        this.s = s;
    }

    public Etat s;
}
