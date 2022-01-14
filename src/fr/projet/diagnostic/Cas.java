package fr.projet.diagnostic;

public class Cas {
    public int id;
    public Triplet p;

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
