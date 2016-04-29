package terminplanung;

import java.util.Date;

import kundenverwaltung.Kunde;

public class Termin {

	private int kundeID;
	private Date datum;
	private Date uhrzeit;
	
	
	
	public int getKunde(){
		return kundeID;
	}

	public void setKunde(int ID){
		kundeID = ID;
	}


	public Date getDatum() {
		return datum;
	}


	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
}
