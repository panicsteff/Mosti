package verkaufsverwaltung;

public class Einkaufsposition {
	
	private String name;
	private double preis;
	private int verkaufsMenge;
	private int literzahl;


	public Einkaufsposition(String name, double preis, int verkaufsmenge, int literzahl) {
		this.setName(name);
		this.setPreis(preis);
		this.setVerkaufsMenge(verkaufsmenge);
		this.setLiterzahl(literzahl);
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


	public int getVerkaufsMenge() {
		return verkaufsMenge;
	}


	public void setVerkaufsMenge(int verkaufsMenge) {
		this.verkaufsMenge = verkaufsMenge;
	}
	
	public void printEinkaufsposition() {
		System.out.println("Einkaufsdrum: " + getName() + "  Anzahl: "
				+ getVerkaufsMenge() + " a " + getPreis() + " �");
	}


	public int getLiterzahl() {
		return literzahl;
	}


	public void setLiterzahl(int literzahl) {
		this.literzahl = literzahl;
	}

}
