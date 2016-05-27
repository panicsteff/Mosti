package verkaufsverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import kundenverwaltung.Kunde;
import persistenz.VerkäufeDB;

public class Verkaufsverwaltung {

	private Verkauf verkauf;
	private ArrayList<Verkaufsposition> einkaufsliste;
	private VerkäufeDB verkäufeDB;

	public Verkaufsverwaltung() {
		verkäufeDB = new VerkäufeDB();
	}
	
	public void addVerkauf(Verkauf verkauf) {
		verkäufeDB.kundeneinkaufHinzufügen(verkauf);
	}
	
	// für bestimmten Kunden

	public Verkauf ladeKundeinkaufTag(Kunde kunde, Date date) {
		einkaufsliste = verkäufeDB.kundeneinkaufLaden(kunde, date);
		verkauf = new Verkauf(kunde, date, einkaufsliste);
		return verkauf;
	}
	
	public ArrayList<Verkaufsposition> ladeKundeneinkaufZeitraum(Kunde kunde, Date date1, Date date2) {
		return verkäufeDB.kundeneinkäufeZeitraumLaden(kunde, date1, date2);
	}

	public ArrayList<Verkaufsposition> ladeAlleEinkäufeVonKunde(Kunde kunde) {
		return verkäufeDB.alleEinkäufeVonKundeLaden(kunde);
	}
	
	
	
	// alle Kunden

	public ArrayList<Verkaufsposition> ladeTagesVerkäufe(Date date) {
		return verkäufeDB.tagesVerkäufeLaden(date);
	}
	
	public ArrayList<Verkaufsposition> ladeAlleVerkäufeZeitraum(Date date1, Date date2){
		return verkäufeDB.VerkäufeZeitraumLaden(date1, date2);
	}
	
	public ArrayList<Verkaufsposition> ladeAlleVerkäufe(){
		return verkäufeDB.alleVerkäufeLaden();
	}
	
}
