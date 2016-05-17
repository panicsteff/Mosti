package terminplanung;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TerminLogik {

	private TerminDB terminDb;
	private Konfigurationswerte k;
	
	public TerminLogik(){
		k = new Konfigurationswerte();
	}
	
	
	ArrayList<Termin> zahlenNachTermine(ArrayList<Integer> terminzahlen, int anzeigeseite) {

		ArrayList<Termin> terminliste = new ArrayList<Termin>();
		
		for(int i = 0; i < terminzahlen.size(); i++){
			Termin t = new Termin();
			t.setKundenId(terminzahlen.get(i));
			int terminid = i + (anzeigeseite-1)*k.getZeilenanzahlProSeite();
			t.setTerminId(terminid);	
			terminliste.add(t);
		}
		
		return terminliste;
	}
	
	ArrayList<Integer> termineNachZahlen(ArrayList<Termin> terminliste){
		ArrayList<Integer> zahlenliste = new ArrayList<Integer>();
		
		for(int i=0; i<terminliste.size(); i++){
			int kundenid = terminliste.get(i).getKundenId();
			zahlenliste.add(kundenid);
		}
		
		return zahlenliste;
	}
	
	ArrayList<Integer> terminIDNachZahlen(ArrayList<Termin> terminliste){
		ArrayList<Integer> terminIdListe = new ArrayList<Integer>();
		
		for(int i=0; i<terminliste.size(); i++){
			int terminid = terminliste.get(i).getTerminId();
			terminIdListe.add(terminid);
		}
		
		return terminIdListe;
	}
	
	
	ArrayList<Termin> termineLaden(Date datum, int anzeigeseite){
	
		int obergrenze = anzeigeseite * k.getZeilenanzahlProSeite();
		int untergrenze = (anzeigeseite - 1)*k.getZeilenanzahlProSeite() + 1;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		terminDb = new TerminDB();
		ArrayList<Integer> terminzahlen = terminDb.termineLaden(obergrenze, untergrenze, laufenderTag);
		ArrayList<Termin> terminliste = zahlenNachTermine(terminzahlen, anzeigeseite);
		
		return terminliste;
		
	}
	
	void termineSpeichern(ArrayList<Termin> terminliste, Date datum){
		
		ArrayList<Integer> kundenIdListe = termineNachZahlen(terminliste);
		ArrayList<Integer> terminIdListe = termineNachZahlen(terminliste);
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		terminDb.termineSpeichern(terminIdListe, kundenIdListe, laufenderTag);
		
	}
	
	
}
