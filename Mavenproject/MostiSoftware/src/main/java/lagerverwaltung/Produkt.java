package lagerverwaltung;

import verkaufsverwaltung.Einkaufsposition;

public class Produkt extends Einkaufsposition {

	private int vorratsMenge;
	private int untergrenze;
	private boolean isAbf�llmaterial;

	public Produkt(String name, double preis, int menge, int untergrenze, boolean isA, int verkaufsmenge) {
		super(name, preis, verkaufsmenge);
		this.setVorratsmenge(menge);
		this.setUntergrenze(untergrenze);
		this.setAbf�llmaterial(isA);
		
	}

	public int getVorratsmenge() {
		return vorratsMenge;
	}

	public void setVorratsmenge(int menge) {
		this.vorratsMenge = menge;
	}

	public int getUntergrenze() {
		return untergrenze;
	}

	public void setUntergrenze(int untergrenze) {
		this.untergrenze = untergrenze;
	}

	public boolean isAbf�llmaterial() {
		return isAbf�llmaterial;
	}

	public void setAbf�llmaterial(boolean isAbf�llmaterial) {
		this.isAbf�llmaterial = isAbf�llmaterial;
	}

}