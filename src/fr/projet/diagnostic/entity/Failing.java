package fr.projet.diagnostic.entity;

public class Failing extends Etat {

	public int idError;
	public String description;
	public String localisation;
	
	public Failing(int idState) {
		super(idState);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
