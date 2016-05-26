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

	public Verkauf ladeKundeneinkauf(Kunde kunde, Date date) {
		einkaufsliste = verk�ufeDB.einkaufLaden(kunde, date);
		verkauf = new Verkauf(kunde, date, einkaufsliste);
		return verkauf;
	}

	public ArrayList<Verkaufsposition> ladeAlleEink�ufeVonKunde(Kunde kunde) {
		return verk�ufeDB.alleEink�ufeVonKundeLaden(kunde);
	}

	public void addVerkauf(Verkauf verkauf) {
		verk�ufeDB.kundeneinkaufHinzuf�gen(verkauf);
	}

	public ArrayList<Verkaufsposition> ladeTagesVerk�ufe(Date date) {
		return verk�ufeDB.tagesVerk�ufeLaden(date);
	}

}
