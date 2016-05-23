package administratorverwaltung;

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
	
	
	ArrayList<Schicht> zahlenNachSchichten(ArrayList<Integer> schichtzahlen, int schichtid) {

		ArrayList<Schicht> schichtliste = new ArrayList<Schicht>();
		
		for(int i = 0; i < k.getSchichtenProTag(); i++){
			Schicht s = new Schicht();
			ArrayList<Integer> mitarbeiterIds = new ArrayList<Integer>();
			for(int j=i*k.getMitarbeiterProSchicht(); j<(i+1)*k.getMitarbeiterProSchicht() ; j++){
				mitarbeiterIds.add(schichtzahlen.get(j));
			}
			s.setSchichtId(schichtid+1);									//ID zählung beginnt bei 1!!!
			s.setMitarbeiterIds(mitarbeiterIds);

			schichtliste.add(s);
		}
		
		return schichtliste;
	}
	
//	ArrayList<Integer> schichtNachZahlen(ArrayList<Schicht> schichtliste){
//		ArrayList<Integer> zahlenliste = new ArrayList<Integer>();
//		
//		for(int i=0; i<schichtliste.size(); i++){
//			int mitarbeiterid = schichtliste.get(i).getMitarbeiterId();
//			zahlenliste.add(mitarbeiterid);
//		}
//		
//		return zahlenliste;
//	}
//	
//	ArrayList<Integer> SchichtIDNachZahlen(ArrayList<Schicht> schichtliste){
//		ArrayList<Integer> schichtIdListe = new ArrayList<Integer>();
//		
//		for(int i=0; i<schichtliste.size(); i++){
//			int schichtid = schichtliste.get(i).getSchichtId();
//			schichtIdListe.add(schichtid);
//		}
//		
//		return schichtIdListe;
//	}

	ArrayList<Schicht> schichtLaden(Date datum){
	
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		int schichtid = laufenderTag*2;
		
		ArrayList<Integer> schichtzahlen = schichtplanDb.schichtLaden(schichtid, k.getMitarbeiterProSchicht(), k.getSchichtenProTag());
		ArrayList<Schicht> schichtliste = zahlenNachSchichten(schichtzahlen, schichtid);
		
		return schichtliste;
		
	}
	
//	void schichtSpeichern(ArrayList<Schicht> schichtliste, Date datum){
//		
//		ArrayList<Integer> mitarbeiterIdListe = schichtNachZahlen(schichtliste);
//		ArrayList<Integer> schichtIdListe = schichtNachZahlen(schichtliste);
//		
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(datum);
//		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
//		
//		schichtplanDb.schichtSpeichern(schichtIdListe, mitarbeiterIdListe, laufenderTag);
//		
//	}
//	
//	ArrayList<Schicht> freieSchichtSuchen(Date d){
//
//		ArrayList<Schicht> freieSchicht = schichtLaden(d);
//		
//		for(int i=0; i<freieSchicht.size(); i++){
//			if(freieSchicht.get(i).getMitarbeiterId() == 0){
//				freieSchicht.remove(i);
//			}
//		}
//		
//		return freieSchicht;
//		
//	}

}
