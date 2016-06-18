package logik.terminplanung;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

import logik.administratorverwaltung.AdministratorLogik;
import persistenz.TerminDB;


public class TerminLogik {

	private TerminDB terminDb;
	
	public TerminLogik(){
		terminDb = new TerminDB();
		new AdministratorLogik();
	}
	
	private ArrayList<Termin> inVolleTerminListe(ArrayList<Termin> alteTerminliste){
		alteTerminliste = termineSortieren(alteTerminliste);
		
		ArrayList<Termin> neueTerminliste = new ArrayList<Termin>();
		int i;
		int j;
		for(i = 0, j = getArbeitsbeginn(); j<getArbeitsende(); j = j+getZeitslot()){
				if(i<alteTerminliste.size() && alteTerminliste.get(i).getUhrzeit() == j){
					Termin t = alteTerminliste.get(i);
					for(int l = 0; l < t.getAnzahlZeitslots(); l++){
						t = new Termin();
						t.setTerminId(alteTerminliste.get(i).getTerminId());
						t.setAnzahlZeitslots(alteTerminliste.get(i).getAnzahlZeitslots());
						t.setDatum(alteTerminliste.get(i).getDatum());
						t.setKundenId(alteTerminliste.get(i).getKundenId());
						t.setMenge(alteTerminliste.get(i).getMenge());
						t.setInFlaschen(alteTerminliste.get(i).isInFlaschen());
						t.setUhrzeit(j);
						neueTerminliste.add(t);
						j = j+getZeitslot();
					}
					j = j-getZeitslot();
					i++;
				}else{
					Termin t = new Termin();
					t.setKundenId(0);
					t.setUhrzeit(j);									
					neueTerminliste.add(t);
				}
		}
		return neueTerminliste;
	}
	
	public ArrayList<Termin> termineSortieren(ArrayList<Termin> terminliste){
		Collections.sort(terminliste, new Comparator<Termin>() {

			public int compare(Termin t1, Termin t2) {
				Integer zeit1 = t1.getUhrzeit();
				Integer zeit2 = t2.getUhrzeit();
				return zeit1.compareTo(zeit2);
			}
		});
		
		return terminliste;
	}

	ArrayList<Termin> termineLaden(long d){
		
		Date datum = new Date(d);
		ArrayList<Termin> terminliste = terminDb.termineLaden(datum);
		terminliste = inVolleTerminListe(terminliste);
		return terminliste;
		
	}
	
	int berechneAnzeigeSeite(int uhrzeit){
		int zeile = (uhrzeit - getArbeitsbeginn())/getZeitslot() + 1;    //EInsbasierte Indexzählung 
		int i;
		for(i=0; i<=getAufteilung(); i++){
			if((i+1)*getZeilenAnzahlProSeite() >= zeile){
				break;
			}
		}
		return i+1;
	}
	
	public String terminNachUhrzeit(int uhrzeit) {
		
		int stunde = uhrzeit/60;
		int minute = uhrzeit%60;
		
		String ausgabe = null;
		if(stunde<10){
			ausgabe = "0"+stunde;
		}else{
			ausgabe = stunde+"";
		}
		if(minute<10){
			ausgabe = ausgabe + ":0" + minute;
		}else{
			ausgabe = ausgabe + ":" + minute;
		}		
		return ausgabe;
	}
	
	public ArrayList<Intervall> berechneLücken(ArrayList<Termin> freieTermine, int dauer) {

		ArrayList<Intervall> intervallListe = new ArrayList<Intervall>();

		Intervall in = new Intervall();
		in.setStart(freieTermine.get(0).getUhrzeit());

		int i;
		for (i = 0; i < freieTermine.size() - 1; i++) {
			if (freieTermine.get(i + 1).getUhrzeit() != freieTermine.get(i).getUhrzeit() + getZeitslot()) {
				in.setEnde(freieTermine.get(i).getUhrzeit());	
				intervallListe.add(in);
				in = new Intervall();
				in.setStart(freieTermine.get(i + 1).getUhrzeit());
			}
		}
		in.setEnde(freieTermine.get(i).getUhrzeit()); // letzter Termin ist immer das Ende
		intervallListe.add(in);
		intervallListe = entferneZuKurzeIntervalle(intervallListe, dauer);
		
		return intervallListe;
	}
	
	public ArrayList<Intervall> entferneZuKurzeIntervalle(ArrayList<Intervall> intervallListe, int dauer){
		class MyPredicate<t> implements Predicate<t>{  
			int dauer;
			public boolean test(t intervall) {
				if(((Intervall) intervall).getEnde() - ((Intervall) intervall).getStart() < dauer){
					return true;
				}
				return false;
				}  
			}  
		
		MyPredicate<Intervall> filter  = new MyPredicate<Intervall>();
		filter.dauer = dauer; 
		intervallListe.removeIf(filter);
		
		return intervallListe;
		
	}
	
	
	public ArrayList<Integer> kundenIDLaden(String eingabe){
		return terminDb.kundenIdLaden(eingabe);
	}
	
	public String kundenNamenLaden(int id){
		return terminDb.kundenNamenLaden(id);
	}
	
	public static void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum, int beginn, int menge, boolean flaschen){
		TerminDB.termineSpeichern(kundenId, anzahlZeitslot, datum, beginn, menge, flaschen);
		
	}
	
	public static void terminLöschen(Termin t){
		TerminDB.terminLöschen(t.getTerminId());
	}
	
	public int tresterKundeLaden(Date d){
		return terminDb.tresterKundeLaden(d);
	}
	
	public void tresterKundeSpeichern(Date d, int kundenId, boolean neu){
		if(neu == true)
		terminDb.tresterKundeNeuSpeichern(d, kundenId);
	}
	
	public void tresterKundeLöschen(Date d){
		terminDb.tresterKundeLöschen(d);
	}
	
	public int getZeitslot(){
		return AdministratorLogik.getZeitslot();
	}
	
	public int getZeilenAnzahlProSeite(){
		return AdministratorLogik.getZeilenanzahlProSeite();
	}
	
	public int getArbeitsbeginn(){
		return AdministratorLogik.getArbeitsbeginn();
	}
	
	public int getArbeitsende(){
		return AdministratorLogik.getArbeitsende();
	}
	
	public int getAufteilung(){
		return AdministratorLogik.getAufteilung();
	}
	
}
