package verkaufsverwaltung;

public class Produkt implements Einkaufsposition {

	private String name;
	private double preis;
	private int vorratsMenge;
	private int untergrenze;
	private int verkaufsMenge;
	private boolean isAbf�llmaterial;

	public Produkt(String name, double preis, int menge, int untergrenze, boolean isA, int verkaufsmenge) {
		this.setName(name);
		this.setPreis(preis);
		this.setVorratsmenge(menge);
		this.setUntergrenze(untergrenze);
		this.setAbf�llmaterial(isA);
		this.setVerkaufsMenge(verkaufsmenge);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
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

	public int getVerkaufsMenge() {
		return verkaufsMenge;
	}

	public void setVerkaufsMenge(int verkaufsMenge) {
		this.verkaufsMenge = verkaufsMenge;
	}

	public boolean isAbf�llmaterial() {
		return isAbf�llmaterial;
	}

	public void setAbf�llmaterial(boolean isAbf�llmaterial) {
		this.isAbf�llmaterial = isAbf�llmaterial;
	}

}