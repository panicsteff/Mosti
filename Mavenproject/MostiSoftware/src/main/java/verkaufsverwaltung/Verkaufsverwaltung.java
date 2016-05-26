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

	public Verkauf ladeKundeneinkauf(Kunde kunde, Date date) {
		einkaufsliste = verkäufeDB.einkaufLaden(kunde, date);
		verkauf = new Verkauf(kunde, date, einkaufsliste);
		return verkauf;
	}

	public ArrayList<Verkaufsposition> ladeAlleEinkäufeVonKunde(Kunde kunde) {
		return verkäufeDB.alleEinkäufeVonKundeLaden(kunde);
	}

	public void addVerkauf(Verkauf verkauf) {
		verkäufeDB.kundeneinkaufHinzufügen(verkauf);
	}

	public ArrayList<Verkaufsposition> ladeTagesVerkäufe(Date date) {
		return verkäufeDB.tagesVerkäufeLaden(date);
	}

}
