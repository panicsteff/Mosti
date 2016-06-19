package logik.verkaufsverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import persistenz.Verk�ufeDB;

public class Verkaufsverwaltung {

	private Verkauf verkauf;
	//private ArrayList<Verkaufsposition> einkaufsliste;
	private Verk�ufeDB verk�ufeDB;

	public Verkaufsverwaltung() {
		verk�ufeDB = new Verk�ufeDB();
	}
	
	public void addVerkauf(Verkauf verkauf) {
		verk�ufeDB.kundeneinkaufHinzuf�gen(verkauf);
	}
	
	// f�r bestimmten Kunden

	public Verkauf ladeKundeinkaufTag(Kunde kunde, Date date) {
		verkauf = verk�ufeDB.kundeneinkaufLaden(kunde, date);
		return verkauf;
	}
//	
//	public ArrayList<Verkaufsposition> ladeKundeneinkaufZeitraum(Kunde kunde, Date date1, Date date2) {
//		return verk�ufeDB.kundeneink�ufeZeitraumLaden(kunde, date1, date2);
//	}
//
//	public ArrayList<Verkaufsposition> ladeAlleEink�ufeVonKunde(Kunde kunde) {
//		return verk�ufeDB.alleEink�ufeVonKundeLaden(kunde);
//	}
//	
//	
//	
//	// alle Kunden
//
//	public ArrayList<Verkaufsposition> ladeTagesVerk�ufe(Date date) {
//		return verk�ufeDB.tagesVerk�ufeLaden(date);
//	}
//	
//	public ArrayList<Verkaufsposition> ladeAlleVerk�ufeZeitraum(Date date1, Date date2){
//		return verk�ufeDB.Verk�ufeZeitraumLaden(date1, date2);
//	}
//	
//	public ArrayList<Verkaufsposition> ladeAlleVerk�ufe(){
//		return verk�ufeDB.alleVerk�ufeLaden();
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
//			ArrayList<Verkaufsposition> verkaufsliste = ladeTagesVerk�ufe(datum);
//			return getSummeVerkaufteLiter(verkaufsliste);
//		}
	
	// f�r bestimmten Kunden

		public ArrayList<VerkaufspositionPlus> ladeKundeinkaufTag2(Kunde kunde, Date date) {
			return verk�ufeDB.kundeneinkaufLaden2(kunde, date);
		}
		
		public ArrayList<VerkaufspositionPlus> ladeKundeneinkaufZeitraum2(Kunde kunde, Date date1, Date date2) {
			return verk�ufeDB.kundeneink�ufeZeitraumLaden2(kunde, date1, date2);
		}

		public ArrayList<VerkaufspositionPlus> ladeAlleEink�ufeVonKunde2(Kunde kunde) {
			return verk�ufeDB.alleEink�ufeVonKundeLaden2(kunde);
		}
		
		
		
		// alle Kunden

		public ArrayList<VerkaufspositionPlus> ladeTagesVerk�ufe2(Date date) {
			return verk�ufeDB.tagesVerk�ufeLaden2(date);
		}
		
		public ArrayList<VerkaufspositionPlus> ladeAlleVerk�ufeZeitraum2(Date date1, Date date2){
			return verk�ufeDB.Verk�ufeZeitraumLaden2(date1, date2);
		}
		
		public ArrayList<VerkaufspositionPlus> ladeAlleVerk�ufe2(){
			return verk�ufeDB.alleVerk�ufeLaden2();
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
		ArrayList<VerkaufspositionPlus> verkaufsliste = ladeTagesVerk�ufe2(datum);
		return getSummeVerkaufteLiter(verkaufsliste);
	}
	
}
