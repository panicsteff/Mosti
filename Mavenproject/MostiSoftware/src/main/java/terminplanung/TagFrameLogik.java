package terminplanung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class TagFrameLogik {

	private Konfigurationswerte k;
	private TerminLogik  terminlogik;
	
	public TagFrameLogik(){
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
	
	void termineSpeichern(ArrayList<Termin> alleTermine, Date datum){
		terminlogik.termineSpeichern(alleTermine, datum);
	}
	
	ArrayList<Termin> termineLaden(Date datum){
		return terminlogik.termineLaden(datum);
		
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
