package dienstleistungProdukt;

public class Dienstleistung implements Einkaufsposition {

	private String name;
	private double preis; //preis pro liter
	private int verkaufsMenge;

	public Dienstleistung(String name, double preis, int menge) {
		this.setName(name);
		this.setPreis(preis);
		this.setVerkaufsMenge(menge);
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

//	public static Dienstleistung[] listeDienstleistungen = {
//			new Dienstleistung("Literzahl - heiß in Beutel", 0.1),
//			new Dienstleistung("Literzahl - heiß in Flaschen", 0.2),
//			new Dienstleistung("Literzahl - kalt", 0.3) };

}
