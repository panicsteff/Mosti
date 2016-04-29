package terminplanung;

import java.util.ArrayList;
import java.util.Date;

enum Farbe {GRUEN, ROT, WEIß};

public class Tag {

	public Farbe farbe;
	ArrayList<Termin> terminliste;
	Date datum;
	
	public Tag(Date datum){
		farbe = Farbe.WEIß;
		terminliste = new ArrayList<Termin>();
		this.datum = datum;
		
	}
	
	public void fuegeTerminHinzu(Termin t){
		terminliste.add(t);
	}
	
	public void loescheTermin(Termin t){
		terminliste.remove(t);
	}
	
	public ArrayList<Termin> getTerminliste(){
		return terminliste;
	}
	
	public String getDatumString(){
		return datum.toString();
	}
}
