package main;

import java.util.ArrayList;
import java.util.List;

import lagerverwaltung.LagerDB;
import lagerverwaltung.Produkt;
import dienstleistungenverwaltung.Dienstleistung;
import dienstleistungenverwaltung.DienstleistungenDB;

public class Angebote {

	static List<Produkt> gesamtProduktSortiment;
	static ArrayList<Produkt> zProduktSortiment;
	static ArrayList<Produkt> abfuellSortiment;
	static ArrayList<Dienstleistung> DLSortiment;
	private boolean hasChanged;
	private LagerDB lagerdb;
	private DienstleistungenDB dldb;

	Angebote() {
		lagerdb = new LagerDB();
		dldb = new DienstleistungenDB(); 
		zProduktSortiment = new ArrayList<Produkt>();
		abfuellSortiment = new ArrayList<Produkt>();
		//DLSortiment = new ArrayList<Dienstleistung>();
		//gesamtProduktSortiment = new ArrayList<Produkt>();
		DLSortiment = dldb.dienstleistungenLaden();
		gesamtProduktSortiment = lagerdb.produkteLaden();
		createSpecialLists();
		hasChanged = false;
	}

//	public void addProdukt(Produkt p) {
//		if (p.isAbfüllmaterial() == true)
//			abfuellSortiment.add(p);
//		else
//			zProduktSortiment.add(p);
//		hasChanged = true;
//	}
	
	private void createSpecialLists() {
		for(Produkt p: gesamtProduktSortiment){
			if (p.isAbfüllmaterial() == true)
				abfuellSortiment.add(p);
			else
				zProduktSortiment.add(p);
		}
<<<<<<< HEAD
		System.out.println();
	}
	
	public void produkteAktualisieren(){
		System.out.println("Jou Datenbank");
		printGesamtListe();
		lagerdb.produkteUpdaten(gesamtProduktSortiment);
		createSpecialLists();
	}
	
	public void addProdukt(Produkt p) {
		lagerdb.produktHinzufügen(p);
		gesamtProduktSortiment.add(p);
		createSpecialLists();
	}
	
	public void deleteProdukt(Produkt p) {
		lagerdb.produktLöschen(p);
		gesamtProduktSortiment.remove(p);
		createSpecialLists();
	}
	
	public void dienstleistungenAktualisieren(){
		dldb.dlUpdaten(DLSortiment);
	}
	
	public void addDienstleistung(Dienstleistung d) {
		dldb.dienstleistungHinzufügen(d);
		DLSortiment.add(d);
	}

	public void deleteDienstleistung(Dienstleistung d) {
		dldb.dienstleistungLöschen(d);
		DLSortiment.remove(d);
	}
	
	public List<Produkt> getGesamtSortiment(){
		return gesamtProduktSortiment;
	}

	public ArrayList<Dienstleistung> getDLSortiment() {
		return DLSortiment;
=======
>>>>>>> a080322e1bc4b6b3257bb1e3a3157651d681482a
	}
//		if (p.isAbfüllmaterial() == true)
//			abfuellSortiment.add(p);
//		else
//			zProduktSortiment.add(p);
//		hasChanged = true;
//	}
	
	
	public void addProdukt(Produkt p) {
		gesamtProduktSortiment.add(p);
	}
	
//	public void deleteProdukt(Produkt p) {
//		if (p.isAbfüllmaterial() == true)
//			abfuellSortiment.remove(p);
//		else
//			zProduktSortiment.remove(p);
//		hasChanged = true;
//	}

	public void deleteProdukt(Produkt p) {
		gesamtProduktSortiment.remove(p);
	}

	public void addDienstleistung(Dienstleistung d) {
		DLSortiment.add(d);
	}

	public void deleteDienstleistung(Dienstleistung d) {
		DLSortiment.remove(d);
	}
	
//	public void createGesamtProduktSortiment(){
//		for(Produkt p : abfuellSortiment){
//			gesamtProduktSortiment.add(p);
//		}
//		for(Produkt p : zProduktSortiment){
//			gesamtProduktSortiment.add(p);
//		}
//	}
	
//	public List<Produkt> getGesamtSortiment(){
//		if(hasChanged == true)
//			createGesamtProduktSortiment();
//		hasChanged = false;
//		return gesamtProduktSortiment;
//	}
	
	public List<Produkt> getGesamtSortiment(){
		return gesamtProduktSortiment;
	}

	public ArrayList<Dienstleistung> getDLSortiment() {
		return DLSortiment;
	}

//	public ArrayList<Produkt> getZProduktSortiment() {
//		return zProduktSortiment;
//	}
//
//	public ArrayList<Produkt> getAbfuellSortiment() {
//		return abfuellSortiment;
//	}

//	public void switchList(Produkt p) {
//		if (abfuellSortiment.contains(p) && p.isAbfüllmaterial() == false) {
//			deleteProdukt(p);
//			addProdukt(p);
//		} else if (zProduktSortiment.contains(p)
//				&& p.isAbfüllmaterial() == true) {
//			deleteProdukt(p);
//			addProdukt(p);
//		}
//		hasChanged = false;
//	}

}
