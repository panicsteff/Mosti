package adminestratorenverwaltung;

import java.util.ArrayList;

public class Konfigurationswerte {
	
	private static int zeitslot;
	private static int arbeitsbeginn;
	private static int arbeitsende;
	private static int aufteilung;
	private static int zeilenanzahlProSeite;
	private SchichtplanDB schichtDb;
	
	public Konfigurationswerte(){
		schichtDb = new SchichtplanDB();
		ArrayList<Integer> konfigurationswerte = schichtDb.adminwerteLaden();
		
		setZeitslot(konfigurationswerte.get(0));
		setArbeitsbeginn(konfigurationswerte.get(1));
		setArbeitsende(konfigurationswerte.get(2));
		setAufteilung(konfigurationswerte.get(3));
		setZeilenanzahlProSeite((arbeitsende-arbeitsbeginn)/(zeitslot*aufteilung));
	}
	
	int getZeitslot() {
		return zeitslot;
	}
	void setZeitslot(int zeitslot) {
		Konfigurationswerte.zeitslot = zeitslot;
	}
	int getArbeitsbeginn() {
		return arbeitsbeginn;
	}
	void setArbeitsbeginn(int arbeitsbeginn) {
		Konfigurationswerte.arbeitsbeginn = arbeitsbeginn;
	}
	int getArbeitsende() {
		return arbeitsende;
	}
	void setArbeitsende(int arbeitsende) {
		Konfigurationswerte.arbeitsende = arbeitsende;
	}
	int getAufteilung() {
		return aufteilung;
	}
	void setAufteilung(int aufteilung) {
		Konfigurationswerte.aufteilung = aufteilung;
	}

	int getZeilenanzahlProSeite() {
		return zeilenanzahlProSeite;
	}

	void setZeilenanzahlProSeite(int zeilenanzahlProSeite) {
		Konfigurationswerte.zeilenanzahlProSeite = zeilenanzahlProSeite;
	}
	

}