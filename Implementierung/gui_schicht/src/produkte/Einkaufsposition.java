package produkte;

import java.util.ArrayList;

public class Einkaufsposition {
	
	private Produkt produkt;
	private int einkaufsanzahl;
	
	public Einkaufsposition(Produkt p, int anzahl){
		produkt = p;
		einkaufsanzahl = anzahl;
	}
	
	public Produkt getProdukt(){
		return produkt;
	}
	
	public int getAnzahl(){
		return einkaufsanzahl;
	}
	
	

}
