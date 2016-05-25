package dienstleistungenverwaltung;

import verkaufsverwaltung.Einkaufsposition;

public class Dienstleistung extends Einkaufsposition {
	
	//preis meint hier preis pro liter
	private static final int verkaufsmenge = 0;

	public Dienstleistung(String name, double preis, int literzahl) {  
		super(name, preis, verkaufsmenge, literzahl);
	}
	
}
