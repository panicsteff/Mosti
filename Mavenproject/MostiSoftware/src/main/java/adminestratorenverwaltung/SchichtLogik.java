package adminestratorenverwaltung;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class SchichtLogik {
	
	private SchichtplanDB schichtplanDb;
	private Konfigurationswerte k;
	
	public SchichtLogik(){
		k = new Konfigurationswerte();
		schichtplanDb = new SchichtplanDB();
	}
	
	
	ArrayList<Schicht> zahlenNachSchichten(ArrayList<Integer> schichtzahlen) {

		ArrayList<Schicht> terminliste = new ArrayList<Schicht>();
		
		for(int i = 0; i < schichtzahlen.size(); i++){
			Schicht s = new Schicht();
			s.setMitarbeiterId(schichtzahlen.get(i));
			s.setSchichtId(i+1);									//ID zählung beginnt bei 1!!!
			
//			Date d = new Date();
//			int stunde;
//			int minute;
//			d.setHours(hours);
//			t.setUhrzeit(uhrzeit);
			terminliste.add(s);
		}
		
		return terminliste;
	}
	
	ArrayList<Integer> schichtNachZahlen(ArrayList<Schicht> schichtliste){
		ArrayList<Integer> zahlenliste = new ArrayList<Integer>();
		
		for(int i=0; i<schichtliste.size(); i++){
			int mitarbeiterid = schichtliste.get(i).getMitarbeiterId();
			zahlenliste.add(mitarbeiterid);
		}
		
		return zahlenliste;
	}
	
	ArrayList<Integer> SchichtIDNachZahlen(ArrayList<Schicht> schichtliste){
		ArrayList<Integer> schichtIdListe = new ArrayList<Integer>();
		
		for(int i=0; i<schichtliste.size(); i++){
			int terminid = schichtliste.get(i).getSchichtId();
			schichtIdListe.add(terminid);
		}
		
		return schichtIdListe;
	}

	ArrayList<Schicht> termineLaden(Date datum){
	
		int obergrenze = (k.getArbeitsende() - k.getArbeitsbeginn()) / k.getZeitslot();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		ArrayList<Integer> schichtzahlen = schichtplanDb.schichtLaden(obergrenze, laufenderTag);
		ArrayList<Schicht> schichtliste = zahlenNachSchichten(schichtzahlen);
		
		return schichtliste;
		
	}
	
	void termineSpeichern(ArrayList<Schicht> schichtliste, Date datum){
		
		ArrayList<Integer> mitarbeiterIdListe = schichtNachZahlen(schichtliste);
		ArrayList<Integer> schichtIdListe = schichtNachZahlen(schichtliste);
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		schichtplanDb.schichtSpeichern(schichtIdListe, mitarbeiterIdListe, laufenderTag);
		
	}
	
	ArrayList<Schicht> freieSchichtSuchen(Date d){

		ArrayList<Schicht> freieSchicht = schichtLaden(d);
		
		for(int i=0; i<freieSchicht.size(); i++){
			if(freieSchicht.get(i).getMitarbeiterId() == 0){
				freieSchicht.remove(i);
			}
		}
		
		return freieSchicht;
		
	}

}
