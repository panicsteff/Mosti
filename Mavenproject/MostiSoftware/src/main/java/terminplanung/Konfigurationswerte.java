package terminplanung;

import java.util.ArrayList;

public class Konfigurationswerte {

	private static int zeitslot;
	private static int arbeitsbeginn;
	private static int arbeitsende;
	private static int aufteilung;
	private static int zeilenanzahlProSeite;
	private TerminDB terminDb;
	
	public Konfigurationswerte(){
		terminDb = new TerminDB();
		ArrayList<Integer> konfigurationswerte = terminDb.adminwerteLaden();
		
		setZeitslot(konfigurationswerte.get(0));
		setArbeitsbeginn(konfigurationswerte.get(1));
		setArbeitsende(konfigurationswerte.get(2));
		setAufteilung(konfigurationswerte.get(3));
		setZeilenanzahlProSeite((arbeitsende-arbeitsbeginn)/(zeitslot*aufteilung));
	}
	
	public int getZeitslot() {
		return zeitslot;
	}
	public void setZeitslot(int zeitslot) {
		Konfigurationswerte.zeitslot = zeitslot;
	}
	public int getArbeitsbeginn() {
		return arbeitsbeginn;
	}
	public void setArbeitsbeginn(int arbeitsbeginn) {
		Konfigurationswerte.arbeitsbeginn = arbeitsbeginn;
	}
	public int getArbeitsende() {
		return arbeitsende;
	}
	public void setArbeitsende(int arbeitsende) {
		Konfigurationswerte.arbeitsende = arbeitsende;
	}
	public int getAufteilung() {
		return aufteilung;
	}
	public void setAufteilung(int aufteilung) {
		Konfigurationswerte.aufteilung = aufteilung;
	}

	public int getZeilenanzahlProSeite() {
		return zeilenanzahlProSeite;
	}

	public void setZeilenanzahlProSeite(int zeilenanzahlProSeite) {
		Konfigurationswerte.zeilenanzahlProSeite = zeilenanzahlProSeite;
	}
	
}
