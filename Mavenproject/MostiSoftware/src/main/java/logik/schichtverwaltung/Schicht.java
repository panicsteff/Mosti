package logik.schichtverwaltung;

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
	
	public Integer getMitarbeiterId(int reihe){
		return mitarbeiterIds.get(reihe);
	}

	public void addMitarbeiterId(int id){
		mitarbeiterIds.add(id);
	}
	
	int getAnzahlMitarbeiter(){
		return mitarbeiterIds.size();
	}

	public int getSchichtId() {
		return schichtId;
	}

	public void setSchichtId(int schichtId) {
		this.schichtId = schichtId;
	}

	public int getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(int uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

}
