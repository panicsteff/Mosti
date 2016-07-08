package logik.produktverwaltung;

import java.util.ArrayList;
import java.util.List;

import persistenz.LagerDB;

public class ProduktSortiment {

	private static ArrayList<Produkt> gesamtProduktSortiment;
	private static ArrayList<Produkt> zProduktSortiment;
	private static ArrayList<Produkt> abfuellSortiment;
	private LagerDB lagerdb;

	public ProduktSortiment() {
		lagerdb = new LagerDB();
		gesamtProduktSortiment = lagerdb.produkteLaden();
		createSpecialLists();
	}

	public void createSpecialLists() { // 2 Listen: eine mit Zusatzprodukte,
										// andere mit Abfüllmaterialien
		zProduktSortiment = new ArrayList<Produkt>();
		abfuellSortiment = new ArrayList<Produkt>();
		if (gesamtProduktSortiment.size() > 0) {
			for (Produkt p : gesamtProduktSortiment) {
				if (p.isAbfüllmaterial() == true)
					abfuellSortiment.add(p);
				else
					zProduktSortiment.add(p);
			}
		}
	}

	public void produktAktualisieren(Produkt p) {
		lagerdb.produktAktualisieren(p);
		createSpecialLists();
	}

	public void updateVerkaufsmengeVonProdukt(String name, int neueMenge) {
		lagerdb.verkaufsmengeUpdaten(name, neueMenge);
	}

	public void addProdukt(Produkt p) {
		p.setId(lagerdb.produktHinzufügen(p));
		gesamtProduktSortiment.add(p);
		createSpecialLists();
	}

	public void deleteProdukt(Produkt p) {
		lagerdb.produktLöschen(p);
		gesamtProduktSortiment.remove(p);
		createSpecialLists();
	}

	public List<Produkt> getGesamtSortiment() { // alle Produkte
		return gesamtProduktSortiment;
	}

	public ArrayList<Produkt> getZProduktSortiment() { // nur Zusatzprodukte
		return zProduktSortiment;
	}

	public ArrayList<Produkt> getAbfuellSortiment() { // nur Abfüllmaterialien
		return abfuellSortiment;
	}

	public void printGesamtListe() {
		for (Produkt p : gesamtProduktSortiment) {
			System.out.print(p.getName() + " ");
		}
		System.out.println();
	}

}
