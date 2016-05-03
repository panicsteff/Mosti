package main;

import java.util.ArrayList;
import java.util.List;

import dienstleistungProdukt.Dienstleistung;
import dienstleistungProdukt.Produkt;

public class Angebote {

	static List<Produkt> gesamtProduktSortiment;
	static ArrayList<Produkt> zProduktSortiment;
	static ArrayList<Produkt> abfuellSortiment;
	static ArrayList<Dienstleistung> DLSortiment;
	private boolean hasChanged;

	public Angebote() {
		zProduktSortiment = new ArrayList<Produkt>();
		abfuellSortiment = new ArrayList<Produkt>();
		DLSortiment = new ArrayList<Dienstleistung>();
		gesamtProduktSortiment = new ArrayList<Produkt>();
		hasChanged = false;
	}

	public void addProdukt(Produkt p) {
		if (p.isAbfüllmaterial() == true)
			abfuellSortiment.add(p);
		else
			zProduktSortiment.add(p);
		hasChanged = true;
	}

	public void deleteProdukt(Produkt p) {
		if (p.isAbfüllmaterial() == true)
			abfuellSortiment.remove(p);
		else
			zProduktSortiment.remove(p);
		hasChanged = true;
	}

	public void addDienstleistung(Dienstleistung d) {
		DLSortiment.add(d);
	}

	public void deleteDienstleistung(Dienstleistung d) {
		DLSortiment.remove(d);
	}
	
	public void createGesamtProduktSortiment(){
		for(Produkt p : abfuellSortiment){
			gesamtProduktSortiment.add(p);
		}
		for(Produkt p : zProduktSortiment){
			gesamtProduktSortiment.add(p);
		}
	}
	
	public List<Produkt> getGesamtSortiment(){
		if(hasChanged == true)
			createGesamtProduktSortiment();
		hasChanged = false;
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

	public void switchList(Produkt p) {
		if (abfuellSortiment.contains(p) && p.isAbfüllmaterial() == false) {
			deleteProdukt(p);
			addProdukt(p);
		} else if (zProduktSortiment.contains(p)
				&& p.isAbfüllmaterial() == true) {
			deleteProdukt(p);
			addProdukt(p);
		}
		hasChanged = false;
	}

}
