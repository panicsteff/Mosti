package terminplanung;

import java.util.Date;

import kundenverwaltung.Kunde;

public class Termin {

	private int kundenID;
	private Kunde kunde;
	private Date datum;
	private Date uhrzeit;
	
	
	
	int getKundenId(){
		return kundenID;
	}

	void setKundenId(int ID){
		kundenID = ID;
	}

	Date getDatum() {
		return datum;
	}

	void setDatum(Date datum) {
		this.datum = datum;
	}

	Date getUhrzeit() {
		return uhrzeit;
	}

	void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	Kunde getKunde() {
		return kunde;
	}

	void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
}
