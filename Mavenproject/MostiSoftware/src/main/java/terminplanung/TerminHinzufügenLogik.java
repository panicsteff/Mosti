package terminplanung;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import kundenverwaltung.Formats;

public class TerminHinzufügenLogik {
	
	private TerminLogik terminlogik;
	private Konfigurationswerte k;
	
	public TerminHinzufügenLogik(){
		terminlogik = new TerminLogik();
		k = new Konfigurationswerte();
	}

	Date nächstenTagBerechnen(Date d){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		
		calendar.add(GregorianCalendar.DATE, 1);
		Date neuesDatum = calendar.getTime();
		
		return neuesDatum;
	}
	
	Date vorherigenTagBerechnen(Date d){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		
		calendar.add(GregorianCalendar.DATE, -1);
		Date neuesDatum = calendar.getTime();
		
		return neuesDatum;
	}
	
	
	ArrayList<Termin> freieTermineSuchen(Date d){

		ArrayList<Termin> freieTermine = terminlogik.termineLaden(d);
		
		for(int i = freieTermine.size()-1; i>=0; i--){									//von hinten beginnen, damits beim löschen keine überschneidungen gibt
			if(freieTermine.get(i).getKundenId() != 0){
				freieTermine.remove(i);
			}
		}
		
		return freieTermine;
	}
	
	int berechneAnzeigeSeite(int terminzeile){
		return terminlogik.berechneAnzeigeSeite(terminzeile);
	}
	
	int berechneTermindauer(String s) throws ParseException{
		
		int obstmenge = Integer.parseInt(s);
		double dauer = obstmenge*5;
		int zeitslot = k.getZeitslot();
		if(dauer%zeitslot == 0){						
			return (int) dauer;
		} else{													//Bei bedarf Dauer in vielfaches der Zeitslots umrechnen
			int h = (int) dauer/zeitslot;
			dauer = (h+1)*zeitslot;
			return (int) dauer;
		}
	}
	
	Date formatieren(String datum){
		Date d = new Date();
		
		try {
			d = Formats.DATE_FORMAT.parse(datum);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
}
