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
	
	public int berechneAnzahlZeitslots(int dauer){
		return dauer/k.getZeitslot();
	}
	
	public int getZeile(int pos, int anzeigeseite){
		return pos + (anzeigeseite - 1) * k.getZeilenanzahlProSeite();
	}
	
	public int anzahlAlleTermine(){
		return k.getZeilenanzahlProSeite()* k.getAufteilung();
	}
	
	public boolean istTerminFrei(ArrayList<Termin> termine){
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
	
	public void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum, int beginn){
		terminlogik.termineSpeichern(kundenId, anzahlZeitslot, datum, beginn);
	}
	
	public ArrayList<Termin> termineLaden(Date datum){
		return terminlogik.termineLaden(datum.getTime());
	}
	
	public boolean isFrueherEnabled(int anzeigeseite){
		if(anzeigeseite == 1){
			return false;
		} else{
			return true;
		}	
	}
	
	public boolean isSpaeterEnabled(int anzeigeseite){
		if(anzeigeseite == k.getAufteilung()){
			return false;
		} else{
			return true;
		}	
	}
	
	
	
}
