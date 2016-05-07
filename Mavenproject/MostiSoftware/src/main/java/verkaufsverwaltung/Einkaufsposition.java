package verkaufsverwaltung;

public class Einkaufsposition {
	
	private String name;
	private double preis;
	private int verkaufsMenge;


	public Einkaufsposition(String name, double preis, int verkaufsmenge) {
		this.setName(name);
		this.setPreis(preis);
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


	public int getVerkaufsMenge() {
		return verkaufsMenge;
	}


	public void setVerkaufsMenge(int verkaufsMenge) {
		this.verkaufsMenge = verkaufsMenge;
	}
	
	public void printEinkaufsposition() {
		System.out.println("Einkaufsdrum: " + getName() + "  Anzahl: "
				+ getVerkaufsMenge() + " a " + getPreis() + " €");
	}

}
