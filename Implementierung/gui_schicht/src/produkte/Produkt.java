package produkte;

public class Produkt {
	
	private String name;
	private double preis;
	private int menge;
	private int untergrenze;
	
	public Produkt(String name, double preis, int menge, int untergrenze){
		this.setName(name);
		this.setPreis(preis);
		this.setMenge(menge);
		this.setUntergrenze(untergrenze);
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
	
	
	

}
