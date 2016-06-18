package logik.terminplanung;

import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class TagFrameController {

	private TerminLogik terminlogik;
	
	public TagFrameController(){
		terminlogik = new TerminLogik();
	}
	
	public int berechneAnzahlZeitslots(int dauer){
		return dauer/terminlogik.getZeitslot();
	}
	
	public int getTermindauer(int anzahlZeitslots){
		return anzahlZeitslots * terminlogik.getZeitslot();
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
				JOptionPane.showMessageDialog(null,
								"Nicht genug Zeit für einen Termin dieser Länge verfügbar");
				return false;
			}
		}
		return true;
	}
	
	public boolean neueTermindauerPrüfen(ArrayList<Termin> alle, Termin t, int anzahl, int uhrzeit){
		
		for(int i=0; i<alle.size(); i++){
			if(alle.get(i).getUhrzeit() == uhrzeit){
				for(int j=i; j<anzahl + i; j++){
					if(alle.get(j).getKundenId() != 0 && alle.get(j).getKundenId() != t.getKundenId()){
						return false;
					}
				}
				break;
			}
		}
		return true;
	}
	
	public int terminStringNachInt(String s){
		int index = s.indexOf(":");
		if(index <= 0 || index >= 3){
			return -1;
		}
		String stunde  = s.substring(0, index);
		String minuten = s.substring(index+1, s.length());
		
		int stundenzahl;
		int minutenzahl;
		
		try{
			stundenzahl = Integer.parseInt(stunde);
			minutenzahl = Integer.parseInt(minuten);
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		int uhrzeit = stundenzahl*60 + minutenzahl;
		if((terminlogik.getArbeitsbeginn() - uhrzeit)%terminlogik.getZeitslot() != 0){
			return -2;
		}
		if(uhrzeit > terminlogik.getArbeitsende() || uhrzeit < terminlogik.getArbeitsbeginn()){
			return -3;
		}
		
		return uhrzeit;
	}
	
	public Termin startTerminfinden(ArrayList<Termin> alleTermine, Termin t){
		int j=0;
		for(int i=0; i<alleTermine.size(); i++){
			if(alleTermine.get(i).getTerminId() == t.getTerminId()){
				for(j=i; j>=0; j--){
					if(alleTermine.get(j).getKundenId() != t.getKundenId()){
						break;
					}
				}
				break;
			}
		}
		return alleTermine.get(j+1);
	}
	
	public String terminNachUhrzeit(int uhrzeit){
		return terminlogik.terminNachUhrzeit(uhrzeit);
	}
	
	public void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum, int beginn, int menge, boolean flaschen){
		TerminLogik.termineSpeichern(kundenId, anzahlZeitslot, datum, beginn, menge, flaschen);
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
