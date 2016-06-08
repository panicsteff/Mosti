package administratorverwaltung;

import java.util.ArrayList;

public class AdministratorLogik {

	private AdministratorDB administratorDB;
	
	private static int zeitslot;
	private static int arbeitsbeginn;
	private static int arbeitsende;
	private static int aufteilung;
	private static int zeilenanzahlProSeite;
	private static int mitarbeiterProSchicht;
	private static int schichtenProTag;
	private static int schichtDauer;
	
	public AdministratorLogik(){
		administratorDB = new AdministratorDB();
		
		ArrayList<Integer> terminwerte = administratorDB.terminWerteLaden();
		ArrayList<Integer> schichtwerte = administratorDB.schichtWerteLaden();
		
		setZeitslot(terminwerte.get(0));
		setArbeitsbeginn(terminwerte.get(1));
		setArbeitsende(terminwerte.get(2));
		setAufteilung(terminwerte.get(3));
		setZeilenanzahlProSeite((arbeitsende-arbeitsbeginn)/(zeitslot*aufteilung));
		
		setMitarbeiterProSchicht(schichtwerte.get(0));
		setSchichtenProTag(schichtwerte.get(1));
		setSchichtDauer((arbeitsende-arbeitsbeginn)/schichtenProTag);
	}
	
	public ArrayList<Integer> terminWerteLaden(){
		return administratorDB.terminWerteLaden();
	}
	
	public ArrayList<Integer> schichtWerteLaden(){
		return administratorDB.terminWerteLaden();
	}

	public static int getZeitslot() {
		return zeitslot;
	}

	public static void setZeitslot(int zeitslot) {
		AdministratorLogik.zeitslot = zeitslot;
	}

	public static int getArbeitsbeginn() {
		return arbeitsbeginn;
	}

	public static void setArbeitsbeginn(int arbeitsbeginn) {
		AdministratorLogik.arbeitsbeginn = arbeitsbeginn;
	}

	public static int getArbeitsende() {
		return arbeitsende;
	}

	public static void setArbeitsende(int arbeitsende) {
		AdministratorLogik.arbeitsende = arbeitsende;
	}

	public static int getAufteilung() {
		return aufteilung;
	}

	public static void setAufteilung(int aufteilung) {
		AdministratorLogik.aufteilung = aufteilung;
	}

	public static int getZeilenanzahlProSeite() {
		return zeilenanzahlProSeite;
	}

	public static void setZeilenanzahlProSeite(int zeilenanzahlProSeite) {
		AdministratorLogik.zeilenanzahlProSeite = zeilenanzahlProSeite;
	}

	public static int getMitarbeiterProSchicht() {
		return mitarbeiterProSchicht;
	}

	public static void setMitarbeiterProSchicht(int mitarbeiterProSchicht) {
		AdministratorLogik.mitarbeiterProSchicht = mitarbeiterProSchicht;
	}

	public static int getSchichtenProTag() {
		return schichtenProTag;
	}

	public static void setSchichtenProTag(int schichtenProTag) {
		AdministratorLogik.schichtenProTag = schichtenProTag;
	}

	public static int getSchichtDauer() {
		return schichtDauer;
	}

	public static void setSchichtDauer(int schichtDauer) {
		AdministratorLogik.schichtDauer = schichtDauer;
	}
	
	public void datenSpeichern(int anzeige, int zeitslot, int beginn, int ende, int mitProSchicht, int schichtenProTag){
		administratorDB.terminWerteSpeichern(anzeige, zeitslot, beginn, ende);
		administratorDB.schichtWerteSpeichern(mitProSchicht, schichtenProTag);
	}
	
}
