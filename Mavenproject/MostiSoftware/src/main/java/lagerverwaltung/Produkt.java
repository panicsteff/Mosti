package lagerverwaltung;

import verkaufsverwaltung.Einkaufsposition;

public class Produkt extends Einkaufsposition {

	private int vorratsMenge;
	private int untergrenze;
	private boolean isAbfüllmaterial;

	public Produkt(String name, double preis, int menge, int untergrenze, boolean isA, int verkaufsmenge) {
		super(name, preis, verkaufsmenge);
		this.setVorratsmenge(menge);
		this.setUntergrenze(untergrenze);
		this.setAbfüllmaterial(isA);
		
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

	public boolean isAbfüllmaterial() {
		return isAbfüllmaterial;
	}

	public void setAbfüllmaterial(boolean isAbfüllmaterial) {
		this.isAbfüllmaterial = isAbfüllmaterial;
	}

}