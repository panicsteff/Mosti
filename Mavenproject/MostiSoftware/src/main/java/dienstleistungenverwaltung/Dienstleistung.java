package dienstleistungenverwaltung;

import verkaufsverwaltung.Verkaufsposition;

public class Dienstleistung extends Verkaufsposition {
	
	//preis meint hier preis pro liter
	private static final int verkaufsmenge = 0;

	public Dienstleistung(String name, double preis, int literzahl) {  
		super(name, preis, verkaufsmenge, literzahl);
	}

	
	public void setVerkaufsMenge(int verkaufsMenge) {
		super.setVerkaufsMenge(verkaufsmenge);
	}
	
}
