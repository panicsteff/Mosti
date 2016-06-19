package logik.verkaufsverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import persistenz.VerkäufeDB;

public class Verkaufsverwaltung {

	private Verkauf verkauf;
	//private ArrayList<Verkaufsposition> einkaufsliste;
	private VerkäufeDB verkäufeDB;

	public Verkaufsverwaltung() {
		verkäufeDB = new VerkäufeDB();
	}
	
	public void addVerkauf(Verkauf verkauf) {
		verkäufeDB.kundeneinkaufHinzufügen(verkauf);
	}
	
	// für bestimmten Kunden

	public Verkauf ladeKundeinkaufTag(Kunde kunde, Date date) {
		verkauf = verkäufeDB.kundeneinkaufLaden(kunde, date);
		return verkauf;
	}
//	
//	public ArrayList<Verkaufsposition> ladeKundeneinkaufZeitraum(Kunde kunde, Date date1, Date date2) {
//		return verkäufeDB.kundeneinkäufeZeitraumLaden(kunde, date1, date2);
//	}
//
//	public ArrayList<Verkaufsposition> ladeAlleEinkäufeVonKunde(Kunde kunde) {
//		return verkäufeDB.alleEinkäufeVonKundeLaden(kunde);
//	}
//	
//	
//	
//	// alle Kunden
//
//	public ArrayList<Verkaufsposition> ladeTagesVerkäufe(Date date) {
//		return verkäufeDB.tagesVerkäufeLaden(date);
//	}
//	
//	public ArrayList<Verkaufsposition> ladeAlleVerkäufeZeitraum(Date date1, Date date2){
//		return verkäufeDB.VerkäufeZeitraumLaden(date1, date2);
//	}
//	
//	public ArrayList<Verkaufsposition> ladeAlleVerkäufe(){
//		return verkäufeDB.alleVerkäufeLaden();
//	}
	
	// Summe der verkauften Literzahlen
//	
//		public int getSummeVerkaufteLiter(ArrayList<Verkaufsposition> liste){
//			int summe = 0;
//			for(Verkaufsposition v: liste){
//				summe = summe + v.getLiterzahl();
//			}
//			return summe;
//		}
//		
//		public int getLitersummeGesamterTag(Date datum){
//			ArrayList<Verkaufsposition> verkaufsliste = ladeTagesVerkäufe(datum);
//			return getSummeVerkaufteLiter(verkaufsliste);
//		}
	
	// für bestimmten Kunden

		public ArrayList<VerkaufspositionPlus> ladeKundeinkaufTag2(Kunde kunde, Date date) {
			return verkäufeDB.kundeneinkaufLaden2(kunde, date);
		}
		
		public ArrayList<VerkaufspositionPlus> ladeKundeneinkaufZeitraum2(Kunde kunde, Date date1, Date date2) {
			return verkäufeDB.kundeneinkäufeZeitraumLaden2(kunde, date1, date2);
		}

		public ArrayList<VerkaufspositionPlus> ladeAlleEinkäufeVonKunde2(Kunde kunde) {
			return verkäufeDB.alleEinkäufeVonKundeLaden2(kunde);
		}
		
		
		
		// alle Kunden

		public ArrayList<VerkaufspositionPlus> ladeTagesVerkäufe2(Date date) {
			return verkäufeDB.tagesVerkäufeLaden2(date);
		}
		
		public ArrayList<VerkaufspositionPlus> ladeAlleVerkäufeZeitraum2(Date date1, Date date2){
			return verkäufeDB.VerkäufeZeitraumLaden2(date1, date2);
		}
		
		public ArrayList<VerkaufspositionPlus> ladeAlleVerkäufe2(){
			return verkäufeDB.alleVerkäufeLaden2();
		}
	
	// Summe der verkauften Literzahlen
	
	public int getSummeVerkaufteLiter(ArrayList<VerkaufspositionPlus> liste){
		int summe = 0;
		for(VerkaufspositionPlus v: liste){
			summe = summe + v.getLiterzahl();
		}
		return summe;
	}
	
	public int getLitersummeGesamterTag(Date datum){
		ArrayList<VerkaufspositionPlus> verkaufsliste = ladeTagesVerkäufe2(datum);
		return getSummeVerkaufteLiter(verkaufsliste);
	}
	
}
