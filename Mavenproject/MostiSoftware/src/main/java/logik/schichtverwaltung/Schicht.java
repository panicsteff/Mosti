package logik.schichtverwaltung;

import java.sql.Date;
import java.util.ArrayList;


public class Schicht {
	
	private ArrayList<Integer> mitarbeiterIds;
	private ArrayList<Integer> schichtId;
	private int uhrzeit;
	private Date datum;
		
	
	public Schicht(){
		mitarbeiterIds = new ArrayList<Integer>();
		schichtId = new ArrayList<Integer>();
		}
	
	public Integer getMitarbeiterId(int reihe){
		if(reihe >= mitarbeiterIds.size()){
			return 0;
		}else
		return mitarbeiterIds.get(reihe);
	}

	public void addMitarbeiterId(int id){
		mitarbeiterIds.add(id);
	}
	
	int getAnzahlMitarbeiter(){
		return mitarbeiterIds.size();
	}

	public Integer getSchichtId(int reihe) {
		return schichtId.get(reihe);
	}

	public void addSchichtId(int Id) {
		schichtId.add(Id);
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
