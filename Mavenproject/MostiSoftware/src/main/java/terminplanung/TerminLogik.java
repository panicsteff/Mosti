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
		terminDb = new TerminDB();
	}
	
	
	ArrayList<Termin> zahlenNachTermine(ArrayList<Integer> terminzahlen) {

		ArrayList<Termin> terminliste = new ArrayList<Termin>();
		
		for(int i = 0; i < terminzahlen.size(); i++){
			Termin t = new Termin();
			t.setKundenId(terminzahlen.get(i));
			t.setTerminId(i+1);									//ID z�hlung beginnt bei 1!!!
			
//			Date d = new Date();
//			int stunde;
//			int minute;
//			d.setHours(hours);
//			t.setUhrzeit(uhrzeit);
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

	ArrayList<Termin> termineLaden(Date datum){
	
		int obergrenze = (k.getArbeitsende() - k.getArbeitsbeginn()) / k.getZeitslot();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		ArrayList<Integer> terminzahlen = terminDb.termineLaden(obergrenze, laufenderTag);
		ArrayList<Termin> terminliste = zahlenNachTermine(terminzahlen);
		
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
	
	ArrayList<Termin> freieTermineSuchen(Date d){

		ArrayList<Termin> freieTermine = termineLaden(d);
		
		for(int i=0; i<freieTermine.size(); i++){
			if(freieTermine.get(i).getKundenId() == 0){
				freieTermine.remove(i);
			}
		}
		
		return freieTermine;
		
	}
}
