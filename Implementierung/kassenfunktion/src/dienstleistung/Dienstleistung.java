package dienstleistung;

public class Dienstleistung {
	
	private String name;
	private double preisProLiter;
	
	public Dienstleistung(String name, double preis){
		this.setName(name);
		this.setPreisProLiter(preis);
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

	public static Dienstleistung[] listeDienstleistungen =
		{new Dienstleistung("Literzahl - heiﬂ in Beutel", 0.05),
		new Dienstleistung("Literzahl - heiﬂ in Flaschen", 0.1),
		new Dienstleistung("Literzahl - kalt", 0.02)
		};
	
	

}
