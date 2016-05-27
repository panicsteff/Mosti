package verkaufsverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import kundenverwaltung.Kunde;
import persistenz.Verk�ufeDB;

public class Verkaufsverwaltung {

	private Verkauf verkauf;
	private ArrayList<Verkaufsposition> einkaufsliste;
	private Verk�ufeDB verk�ufeDB;

	public Verkaufsverwaltung() {
		verk�ufeDB = new Verk�ufeDB();
	}
	
	public void addVerkauf(Verkauf verkauf) {
		verk�ufeDB.kundeneinkaufHinzuf�gen(verkauf);
	}
	
	// f�r bestimmten Kunden

	public Verkauf ladeKundeinkaufTag(Kunde kunde, Date date) {
		einkaufsliste = verk�ufeDB.kundeneinkaufLaden(kunde, date);
		verkauf = new Verkauf(kunde, date, einkaufsliste);
		return verkauf;
	}
	
	public ArrayList<Verkaufsposition> ladeKundeneinkaufZeitraum(Kunde kunde, Date date1, Date date2) {
		return verk�ufeDB.kundeneink�ufeZeitraumLaden(kunde, date1, date2);
	}

	public ArrayList<Verkaufsposition> ladeAlleEink�ufeVonKunde(Kunde kunde) {
		return verk�ufeDB.alleEink�ufeVonKundeLaden(kunde);
	}
	
	
	
	// alle Kunden

	public ArrayList<Verkaufsposition> ladeTagesVerk�ufe(Date date) {
		return verk�ufeDB.tagesVerk�ufeLaden(date);
	}
	
	public ArrayList<Verkaufsposition> ladeAlleVerk�ufeZeitraum(Date date1, Date date2){
		return verk�ufeDB.Verk�ufeZeitraumLaden(date1, date2);
	}
	
	public ArrayList<Verkaufsposition> ladeAlleVerk�ufe(){
		return verk�ufeDB.alleVerk�ufeLaden();
	}
	
}
