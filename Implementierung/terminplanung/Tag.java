package terminplanung;

import java.util.ArrayList;

enum Farbe {GRUEN, ROT, WEIß};

public class Tag {

	public Farbe farbe;
	ArrayList<Termin> terminliste;
	
	public Tag(){
		farbe = Farbe.WEIß;
		terminliste = new ArrayList<Termin>();
		
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
}
