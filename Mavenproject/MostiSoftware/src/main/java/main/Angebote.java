package main;

import java.util.ArrayList;
import java.util.List;

import lagerverwaltung.LagerDB;
import lagerverwaltung.Produkt;
import dienstleistungenverwaltung.Dienstleistung;
import dienstleistungenverwaltung.DienstleistungenDB;

public class Angebote {

	private static ArrayList<Produkt> gesamtProduktSortiment;
	static ArrayList<Dienstleistung> DLSortiment;
	static ArrayList<Produkt> zProduktSortiment;
	static ArrayList<Produkt> abfuellSortiment;
	//private boolean hasChanged;
	private LagerDB lagerdb;
	private DienstleistungenDB dldb;

	Angebote() {
		lagerdb = new LagerDB();
		dldb = new DienstleistungenDB(); 
		//DLSortiment = new ArrayList<Dienstleistung>();
		//gesamtProduktSortiment = new ArrayList<Produkt>();
		DLSortiment = dldb.dienstleistungenLaden();
		gesamtProduktSortiment = lagerdb.produkteLaden();
		createSpecialLists();
		//hasChanged = false;
	}
	
	
	public void createSpecialLists() {
		zProduktSortiment = new ArrayList<Produkt>();
		abfuellSortiment = new ArrayList<Produkt>();
		if(gesamtProduktSortiment.size() > 0){
		for(Produkt p: gesamtProduktSortiment){
			if (p.isAbfüllmaterial() == true)
				abfuellSortiment.add(p);
			else
				zProduktSortiment.add(p);
		}}
	}
	
	public void printGesamtListe(){
		for(Produkt p: gesamtProduktSortiment){
			System.out.print(p.getName() + " ");
		}
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
	}
	
	public ArrayList<Produkt> getZProduktSortiment() {
		return zProduktSortiment;
	}

	public ArrayList<Produkt> getAbfuellSortiment() {
		return abfuellSortiment;
	}
	
	
	
//	public void addProdukt(Produkt p) {
//	if (p.isAbfüllmaterial() == true)
//		abfuellSortiment.add(p);
//	else
//		zProduktSortiment.add(p);
//	hasChanged = true;
//}
	
	
//		if (p.isAbfüllmaterial() == true)
//			abfuellSortiment.add(p);
//		else
//			zProduktSortiment.add(p);
//		hasChanged = true;
//	}
	
	
//	public void deleteProdukt(Produkt p) {
//		if (p.isAbfüllmaterial() == true)
//			abfuellSortiment.remove(p);
//		else
//			zProduktSortiment.remove(p);
//		hasChanged = true;
//	}

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
