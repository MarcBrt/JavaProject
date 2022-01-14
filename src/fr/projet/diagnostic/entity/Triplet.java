package fr.projet.diagnostic.entity;

import java.util.ArrayList;

public class Triplet {
    public int idTriplet;
    public String er;
    public String ec;
    public ArrayList<Intervalle> ct;

    public Triplet(int id, String eveRef, String eveContrainte, ArrayList<Intervalle> contrainteTemp) {
        if( eveRef.startsWith("In") || eveRef.startsWith("RE") || eveRef.startsWith("FE") ) {
            this.idTriplet = id;
            this.er = eveRef;
            this.ec = eveContrainte;
            this.ct = contrainteTemp;
        }
    }

    public int getIdTriplet() {
        return idTriplet;
    }

    public void setIdTriplet(int idTriplet) {
        this.idTriplet = idTriplet;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public ArrayList<Intervalle> getCt() {
        return ct;
    }

    public void setCt(ArrayList<Intervalle> ct) {
        this.ct = ct;
    }
}
