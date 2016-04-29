package terminplanung;

import java.util.Date;

import kundenverwaltung.Kunde;

public class Termin {

	private int kundenID;
	private Kunde kunde;
	private Date datum;
	private Date uhrzeit;
	
	
	
	public int getKundenId(){
		return kundenID;
	}

	public void setKundenId(int ID){
		kundenID = ID;
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

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
}
