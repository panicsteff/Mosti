package verkaufsverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import kundenverwaltung.Kunde;
import persistenz.Verk�ufeDB;

public class Verkaufsverwaltung {
	
	private Einkauf einkauf;
	private ArrayList<Verkaufsposition> einkaufsliste;
	private Verk�ufeDB verk�ufeDB;
	
	public Verkaufsverwaltung(){
		verk�ufeDB = new Verk�ufeDB();
		
	}
	
	public Einkauf ladeKundeneinkauf(Kunde kunde, Date date){
		einkaufsliste = verk�ufeDB.einkaufLaden(kunde, date);
		einkauf = new Einkauf(kunde, date, einkaufsliste);
		return einkauf;
	}
	
	public ArrayList<Verkaufsposition> ladeAlleEink�ufeVonKunde(Kunde kunde){
		return verk�ufeDB.alleEink�ufeVonKundeLaden(kunde);
	}

}
