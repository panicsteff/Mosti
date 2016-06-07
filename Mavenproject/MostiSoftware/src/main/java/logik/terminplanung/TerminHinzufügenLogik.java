package logik.terminplanung;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

import kundenverwaltung.Formats;

public class TerminHinzufügenLogik {
	
	private TerminLogik terminlogik;
	private Konfigurationswerte k;
	
	public TerminHinzufügenLogik(){
		terminlogik = new TerminLogik();
		k = new Konfigurationswerte();
	}

	public long nächstenTagBerechnen(long datum){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(datum);
		
		calendar.add(GregorianCalendar.DATE, 1);
		Date neuesDatum = calendar.getTime();
		
		return neuesDatum.getTime();
	}
	
	public long vorherigenTagBerechnen(long datum){
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(datum);
		
		calendar.add(GregorianCalendar.DATE, -1);
		Date neuesDatum = calendar.getTime();
		
		return neuesDatum.getTime();
	}
	
	
	public ArrayList<Termin> freieTermineSuchen(long datum){

		ArrayList<Termin> freieTermine = terminlogik.termineLaden(datum);
		
		class MyPredicate<t> implements Predicate<t>{  
			public boolean test(t termin) {
				if(((Termin) termin).getKundenId() != 0){
					return true;
				}
				return false;
				}  
			}  
		
		MyPredicate<Termin> filter  = new MyPredicate<Termin>();
		freieTermine.removeIf(filter);
		return freieTermine;
	}
	
	public int berechneAnzeigeSeite(int terminzeile){
		return terminlogik.berechneAnzeigeSeite(terminzeile);
	}
	
	public int berechneTermindauer(String s) throws ParseException{
		
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
	
	public long formatieren(String datum){
		
		Date d= new Date();
		try {
			d = Formats.DATE_FORMAT.parse(datum);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d.getTime();
	}
	
}
