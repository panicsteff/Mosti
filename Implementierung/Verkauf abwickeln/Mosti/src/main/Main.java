package main;

import kassenfunktion.Abrechnung;
import produkte.Produkt;
import produkte.Produktverwaltung;

public class Main {

	public static void main(String[] args) {
		
		Produktverwaltung pVerwaltung = new Produktverwaltung();
		Produkt p1 = new Produkt ("5L Box", 1.00, 10, 200);
		Produkt p2 = new Produkt ("10L Box", 2.00, 100, 200);
		
		pVerwaltung.addProdukt(p1);
		pVerwaltung.addProdukt(p2);
		
		pVerwaltung.printProdukte();
		
		Abrechnung �ams = new Abrechnung(pVerwaltung);
		�ams.addAnzahl(6);
		�ams.addAnzahl(80);
		System.out.println("Gesamtsumme f�r �ams: " +�ams.bildeSumme()+"�");
		
		

	}

}
