package verkaufsverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import kundenverwaltung.Kunde;
import persistenz.VerkäufeDB;

public class Verkaufsverwaltung {
	
	private Einkauf einkauf;
	private ArrayList<Verkaufsposition> einkaufsliste;
	private VerkäufeDB verkäufeDB;
	
	public Verkaufsverwaltung(){
		verkäufeDB = new VerkäufeDB();
		
	}
	
	public Einkauf ladeKundeneinkauf(Kunde kunde, Date date){
		einkaufsliste = verkäufeDB.einkaufLaden(kunde, date);
		einkauf = new Einkauf(kunde, date, einkaufsliste);
		return einkauf;
	}
	
	public ArrayList<Verkaufsposition> ladeAlleEinkäufeVonKunde(Kunde kunde){
		return verkäufeDB.alleEinkäufeVonKundeLaden(kunde);
	}

}
