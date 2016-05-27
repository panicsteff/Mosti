package terminplanung;

import java.util.ArrayList;
import java.sql.Date;


public class TerminLogik {

	private TerminDB terminDb;
	private Konfigurationswerte k;
	
	public TerminLogik(){
		k = new Konfigurationswerte();
		terminDb = new TerminDB();
	}
	
	int getZeilenAnzahlProSeite(){
		return k.getZeilenanzahlProSeite();
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
					t.setUhrzeit(j);									//is des sinnvoll?
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
	
	void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum, int beginn){
		terminDb.termineSpeichern(kundenId, anzahlZeitslot, datum, beginn);
		
	}
	
	ArrayList<Integer> kundenIDLaden(String eingabe){
		return terminDb.kundenIdLaden(eingabe);
	}
	
	String kundenNamenLaden(int id){
		return terminDb.kundenNamenLaden(id);
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
	
	String terminNachUhrzeit(int uhrzeit) {
		
		int stunde = uhrzeit/60;
		int minute = uhrzeit%60;
		
		if(minute<10){
			return stunde + ":0" + minute;
		}
		
		return stunde + ":" + minute;
	}
	
}
