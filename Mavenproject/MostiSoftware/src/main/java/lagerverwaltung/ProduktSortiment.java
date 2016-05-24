package lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

import persistenz.LagerDB;

public class ProduktSortiment {

	private static ArrayList<Produkt> gesamtProduktSortiment;
	private static ArrayList<Produkt> zProduktSortiment;
	private static ArrayList<Produkt> abfuellSortiment;
	//private boolean hasChanged;
	private LagerDB lagerdb;

	public ProduktSortiment() {
		lagerdb = new LagerDB();
		gesamtProduktSortiment = lagerdb.produkteLaden();
		createSpecialLists();
		//hasChanged = false;
	}
	
	
	void createSpecialLists() {
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
	
	void updateProdukte(){
		System.out.println("Jou Datenbank");
		printGesamtListe();
		lagerdb.produkteUpdaten(gesamtProduktSortiment);
	}
	
	void addProdukt(Produkt p) {
		lagerdb.produktHinzufügen(p);
		gesamtProduktSortiment.add(p);
		createSpecialLists();
	}
	
	void deleteProdukt(Produkt p) {
		lagerdb.produktLöschen(p);
		gesamtProduktSortiment.remove(p);
		createSpecialLists();
	}

	public List<Produkt> getGesamtSortiment(){
		return gesamtProduktSortiment;
	}
	
	public ArrayList<Produkt> getZProduktSortiment() {
		return zProduktSortiment;
	}

	public ArrayList<Produkt> getAbfuellSortiment() {
		return abfuellSortiment;
	}
	
	public void printGesamtListe(){
		for(Produkt p: gesamtProduktSortiment){
			System.out.print(p.getName() + " ");
		}
		System.out.println();
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
	
	


}

