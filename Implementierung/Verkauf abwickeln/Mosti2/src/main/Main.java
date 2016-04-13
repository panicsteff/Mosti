package main;

import kassenfunktion.Abrechnung;
import produkte.Einkauf;
import produkte.Einkaufsposition;
import produkte.Produkt;
import produkte.Produktverwaltung;

public class Main {

	public static void main(String[] args) {
		
		Produktverwaltung pVerwaltung = new Produktverwaltung();
		Produkt p1 = new Produkt ("5L Box", 1.00, 10, 200);
		Produkt p2 = new Produkt ("10L Box", 2.00, 100, 200);
		
//		pVerwaltung.addProdukt(p1);
//		pVerwaltung.addProdukt(p2);
//		
//		pVerwaltung.printProdukte();
//		
//		Abrechnung öams = new Abrechnung(pVerwaltung);
//		öams.addAnzahl(6);
//		öams.addAnzahl(80);
//		System.out.println("Gesamtsumme für Öams: " +öams.bildeSumme()+"€");
		
		Einkaufsposition e1 = new Einkaufsposition(p1, 6);
		Einkaufsposition e2 = new Einkaufsposition(p2, 80);
		Einkauf öamsEinkauf = new Einkauf();
		öamsEinkauf.addEinkauf(e1);
		öamsEinkauf.addEinkauf(e2);
		öamsEinkauf.printEinkauf();
		System.out.println("Wie viel muss Öams bezahlen? --> " + öamsEinkauf.bildeSumme() + " €");
		
		

	}

}
