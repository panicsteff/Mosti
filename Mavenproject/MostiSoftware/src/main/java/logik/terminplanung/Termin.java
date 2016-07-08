package logik.terminplanung;

import java.sql.Date;

public class Termin {

	private int kundenId;
	private int terminId;
	private Date datum;
	private int anzahlZeitslots;
	private int uhrzeit;
	private int menge;
	private boolean inFlaschen;

	public int getKundenId() {
		return kundenId;
	}

	public void setKundenId(int ID) {
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

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public boolean isInFlaschen() {
		return inFlaschen;
	}

	public void setInFlaschen(boolean inFlaschen) {
		this.inFlaschen = inFlaschen;
	}

}
