package logik.terminplanung;

import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class TagFrameController {

	private TerminLogik  terminlogik;
	
	public TagFrameController(){
		terminlogik = new TerminLogik();
	}
	
	public int berechneAnzahlZeitslots(int dauer){
		return dauer/terminlogik.getZeitslot();
	}
	
	public int getZeile(int pos, int anzeigeseite){
		return pos + (anzeigeseite - 1) * terminlogik.getZeilenAnzahlProSeite();
	}
	
	public int anzahlAlleTermine(){
		return terminlogik.getZeilenAnzahlProSeite()* terminlogik.getAufteilung();
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
		if(anzeigeseite == terminlogik.getAufteilung()){
			return false;
		} else{
			return true;
		}	
	}
	
	public int getTermindauer(int anzahlZeitslots){
		return anzahlZeitslots * terminlogik.getZeitslot();
	}
	
	public int tresterKundeLaden(Date d){
		return terminlogik.tresterKundeLaden(d);
	}
	
	public ArrayList<Integer> kundenIdLaden(String eingabe){
		return terminlogik.kundenIDLaden(eingabe);
	}
	
	public String kundenNameLaden(int id){
		return terminlogik.kundenNamenLaden(id);
	}
	
	public void tresterKundeSpeichern(Date d, int kundenId, boolean neu){
		terminlogik.tresterKundeSpeichern(d, kundenId, neu);
	}
	
	public void tresterKundeLöschen(Date d){
		terminlogik.tresterKundeLöschen(d);
	}
	
	
	
}
