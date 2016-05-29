package logik.terminplanung;

import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class TagFrameController {

	private Konfigurationswerte k;
	private TerminLogik  terminlogik;
	
	public TagFrameController(){
		k = new Konfigurationswerte();
		terminlogik = new TerminLogik();
	}
	
	int berechneAnzahlZeitslots(int dauer){
		return dauer/k.getZeitslot();
	}
	
	int getZeile(int pos, int anzeigeseite){
		return pos + (anzeigeseite - 1) * k.getZeilenanzahlProSeite();
	}
	
	int anzahlAlleTermine(){
		return k.getZeilenanzahlProSeite()* k.getAufteilung();
	}
	
	boolean istTerminFrei(ArrayList<Termin> termine){
		for (Termin t : termine) {
			if (t.getKundenId() != 0) {
				JOptionPane
						.showMessageDialog(null,
								"Nicht genug Zeit für einen Termin dieser Länge verfügbar");
				return false;
			}
		}
		return true;
	}
	
	void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum, int beginn){
		terminlogik.termineSpeichern(kundenId, anzahlZeitslot, datum, beginn);
	}
	
	ArrayList<Termin> termineLaden(Date datum){
		return terminlogik.termineLaden(datum.getTime());
		
	}
	
	boolean isFrueherEnabled(int anzeigeseite){
		if(anzeigeseite == 1){
			return false;
		} else{
			return true;
		}	
	}
	
	boolean isSpaeterEnabled(int anzeigeseite){
		if(anzeigeseite == k.getAufteilung()){
			return false;
		} else{
			return true;
		}	
	}
	
	
	
}
