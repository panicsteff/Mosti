package dienstleistung;

public class Dienstleistung {
	
	private String name;
	private double preisProLiter;
	private int verkaufsMenge;
	
	public Dienstleistung(String name, double preis){
		this.setName(name);
		this.setPreisProLiter(preis);
		this.setVerkaufsMenge(0);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPreisProLiter() {
		return preisProLiter;
	}

	public void setPreisProLiter(double preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

	public int getVerkaufsMenge() {
		return verkaufsMenge;
	}

	public void setVerkaufsMenge(int verkaufsMenge) {
		this.verkaufsMenge = verkaufsMenge;
	}

	public static Dienstleistung[] listeDienstleistungen =
		{new Dienstleistung("Literzahl - heiß in Beutel", 0.1),
		new Dienstleistung("Literzahl - heiß in Flaschen", 0.2),
		new Dienstleistung("Literzahl - kalt", 0.3)
		};
	
	

}
