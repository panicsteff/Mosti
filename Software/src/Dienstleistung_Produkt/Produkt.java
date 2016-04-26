package Dienstleistung_Produkt;

public class Produkt {

	private String name;
	private double preis;
	private int menge;
	private int untergrenze;
	private int verkaufsMenge;

	public Produkt(String name, double preis, int menge, int untergrenze) {
		this.setName(name);
		this.setPreis(preis);
		this.setMenge(menge);
		this.setUntergrenze(untergrenze);
		this.setVerkaufsMenge(0);
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

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
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

}