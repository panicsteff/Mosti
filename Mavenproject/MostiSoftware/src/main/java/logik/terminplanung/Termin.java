package logik.terminplanung;

import java.sql.Date;



public class Termin {

	private int kundenId;
	private int terminId;
	private Date datum;
	private int anzahlZeitslots;
	private int uhrzeit;
	
	
	public int getKundenId(){
		return kundenId;
	}

	public void setKundenId(int ID){
		kundenId = ID;
	}

	public int getTerminId() {
		return terminId;
	}

	public void setTerminId(int terminId) {
		this.terminId = terminId;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date uhrzeit) {
		this.datum = uhrzeit;
	}

	public int getAnzahlZeitslots() {
		return anzahlZeitslots;
	}

	public void setAnzahlZeitslots(int anzahlZeitslots) {
		this.anzahlZeitslots = anzahlZeitslots;
	}

	public int getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(int uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	
	



}
