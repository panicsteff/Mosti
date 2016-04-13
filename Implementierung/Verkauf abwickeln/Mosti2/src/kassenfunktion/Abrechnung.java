package kassenfunktion;

import java.util.ArrayList;

import produkte.Produkt;
import produkte.Produktverwaltung;

public class Abrechnung {
	
	private Produktverwaltung produktverwaltung;
	private double endsumme;
	private ArrayList<Integer> anzahlliste;
	
	public Abrechnung(Produktverwaltung pw){
		endsumme = 0;
		produktverwaltung = pw;
		anzahlliste = new ArrayList<Integer>();
	}
	
	
	public double bildeSumme(){
		for(int i = 0; i < produktverwaltung.arraygroesse(); i++){
			if(anzahlliste.get(i) != 0){
				endsumme = endsumme + anzahlliste.get(i)*produktverwaltung.getProdukt(i).getPreis();
			}
		}
		return endsumme;
	}
	
	public void addAnzahl(int anzahl){
		anzahlliste.add(anzahl);
	}



	

}
