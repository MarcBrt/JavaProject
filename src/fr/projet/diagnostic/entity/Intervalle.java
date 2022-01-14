package fr.projet.diagnostic.entity;

public class Intervalle {

    public int idIntervalle;
    public int bi;
    public int bs;

    public Intervalle(int idIntervalle, int bi, int bs) {
        this.idIntervalle = idIntervalle;
        this.bi = bi;
        this.bs = bs;
    }

    public int getIdIntervalle() {
        return idIntervalle;
    }

    public void setIdIntervalle(int idIntervalle) {
        this.idIntervalle = idIntervalle;
    }

    public int getBi() {
        return bi;
    }

    public void setBi(int bi) {
        this.bi = bi;
    }

    public int getBs() {
        return bs;
    }

    public void setBs(int bs) {
        this.bs = bs;
    }
}
