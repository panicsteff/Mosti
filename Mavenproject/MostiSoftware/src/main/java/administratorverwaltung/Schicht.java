package administratorverwaltung;

import java.sql.Date;
import java.util.ArrayList;


public class Schicht {
	
	private ArrayList<Integer> mitarbeiterIds;
	private int schichtId;
	private int uhrzeit;
	private Date datum;
		
	
	public Schicht(){
		mitarbeiterIds = new ArrayList<Integer>();
	}
	
	Integer getMitarbeiterId(int reihe){
		return mitarbeiterIds.get(reihe);
	}

	void addMitarbeiterId(int id){
		mitarbeiterIds.add(id);
	}
	
	int getAnzahlMitarbeiter(){
		return mitarbeiterIds.size();
	}

	int getSchichtId() {
		return schichtId;
	}

	void setSchichtId(int schichtId) {
		this.schichtId = schichtId;
	}

	int getUhrzeit() {
		return uhrzeit;
	}

	void setUhrzeit(int uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

}
