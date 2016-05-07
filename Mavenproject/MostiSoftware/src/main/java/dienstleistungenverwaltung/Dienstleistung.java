package dienstleistungenverwaltung;

import verkaufsverwaltung.Einkaufsposition;

public class Dienstleistung extends Einkaufsposition {
	
	//preis meint hier preis pro liter

	public Dienstleistung(String name, double preis, int menge) {  
		super(name, preis, menge);
	}

	 
}
