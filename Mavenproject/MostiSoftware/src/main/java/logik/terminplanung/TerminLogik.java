package logik.terminplanung;

import java.util.ArrayList;
import java.sql.Date;

import persistenz.TerminDB;


public class TerminLogik {

	private TerminDB terminDb;
	private Konfigurationswerte k;
	
	public TerminLogik(){
		k = new Konfigurationswerte();
		terminDb = new TerminDB();
	}
	
	private ArrayList<Termin> inVolleTerminListe(ArrayList<Termin> alteTerminliste){
		alteTerminliste = termineSortieren(alteTerminliste);
		
		ArrayList<Termin> neueTerminliste = new ArrayList<Termin>();
		int i;
		int j;
		for(i = 0, j = k.getArbeitsbeginn(); j<k.getArbeitsende(); j = j+k.getZeitslot()){
				if(i<alteTerminliste.size() && alteTerminliste.get(i).getUhrzeit() == j){
					Termin t = alteTerminliste.get(i);
					for(int l = 0; l < t.getAnzahlZeitslots(); l++){
						t = new Termin();
						t.setAnzahlZeitslots(alteTerminliste.get(i).getAnzahlZeitslots());
						t.setDatum(alteTerminliste.get(i).getDatum());
						t.setKundenId(alteTerminliste.get(i).getKundenId());
						t.setUhrzeit(j);
						neueTerminliste.add(t);
						j = j+k.getZeitslot();
					}
					j = j-k.getZeitslot();
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

	private ArrayList<Termin> termineSortieren(ArrayList<Termin> terminliste) {
		int i;
		int j;
		int min;
		int minstelle;
		for (i = 0; i < terminliste.size(); i++) {
			min = terminliste.get(i).getUhrzeit();
			minstelle = i;
			for (j = i; j < terminliste.size(); j++) {
				if (terminliste.get(j).getUhrzeit() < min) {
					min = terminliste.get(j).getUhrzeit();
					minstelle = j;
				}
			}
			Termin h = terminliste.get(i);
			terminliste.set(i, terminliste.get(minstelle));
			terminliste.set(minstelle, h);
		}
		
		return terminliste;
	}

	ArrayList<Termin> termineLaden(long d){
		
		Date datum = new Date(d);
		ArrayList<Termin> terminliste = terminDb.termineLaden(datum);
		terminliste = inVolleTerminListe(terminliste);
		return terminliste;
		
	}
	
	int berechneAnzeigeSeite(int uhrzeit){
		int zeile = (uhrzeit - k.getArbeitsbeginn())/k.getZeitslot() + 1;    //EInsbasierte Indexzählung 
		int i;
		for(i=0; i<=k.getAufteilung(); i++){
			if((i+1)*k.getZeilenanzahlProSeite() >= zeile){
				break;
			}
		}
		return i+1;
	}
	
	public String terminNachUhrzeit(int uhrzeit) {
		
		int stunde = uhrzeit/60;
		int minute = uhrzeit%60;
		
		if(minute<10){
			return stunde + ":0" + minute;
		}
		
		return stunde + ":" + minute;
	}
	
	public ArrayList<Intervall> berechneLücken(ArrayList<Termin> freieTermine) {

		ArrayList<Intervall> intervallListe = new ArrayList<Intervall>();

		Intervall in = new Intervall();
		in.setStart(freieTermine.get(0).getUhrzeit());

		int i;
		for (i = 0; i < freieTermine.size() - 1; i++) {
			if (freieTermine.get(i + 1).getUhrzeit() != freieTermine.get(i).getUhrzeit() + k.getZeitslot()) {
				in.setEnde(freieTermine.get(i).getUhrzeit());								
				intervallListe.add(in);
				in = new Intervall();
				in.setStart(freieTermine.get(i + 1).getUhrzeit());
			}
		}
		in.setEnde(freieTermine.get(i).getUhrzeit()); // letzter Termin ist immer das Ende
		intervallListe.add(in);
		
		return intervallListe;
	}
	

	public int getZeilenAnzahlProSeite(){
		return k.getZeilenanzahlProSeite();
	}
	
	public ArrayList<Integer> kundenIDLaden(String eingabe){
		return terminDb.kundenIdLaden(eingabe);
	}
	
	public String kundenNamenLaden(int id){
		return terminDb.kundenNamenLaden(id);
	}
	
	void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum, int beginn){
		terminDb.termineSpeichern(kundenId, anzahlZeitslot, datum, beginn);
		
	}
	
	public int getZeitslot(){
		return k.getZeitslot();
	}
	
}
