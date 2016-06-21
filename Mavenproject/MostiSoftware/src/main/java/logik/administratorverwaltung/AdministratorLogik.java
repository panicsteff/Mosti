package logik.administratorverwaltung;

import java.util.ArrayList;

import persistenz.AdministratorDB;

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
	private static double pressdauer;
	private static double abf�lldauer;
	
	public AdministratorLogik(){
		administratorDB = new AdministratorDB();
		
		ArrayList<Integer> terminwerte = administratorDB.terminWerteLaden();
		ArrayList<Integer> schichtwerte = administratorDB.schichtWerteLaden();
		ArrayList<Double> presswerte = administratorDB.pressWerteLaden();
		
		setZeitslot(terminwerte.get(0));
		setArbeitsbeginn(terminwerte.get(1));
		setArbeitsende(terminwerte.get(2));
		setAufteilung(terminwerte.get(3));
		setZeilenanzahlProSeite((arbeitsende-arbeitsbeginn)/(zeitslot*aufteilung));
		
		setMitarbeiterProSchicht(schichtwerte.get(0));
		setSchichtenProTag(schichtwerte.get(1));
		setSchichtDauer((arbeitsende-arbeitsbeginn)/schichtenProTag);
		
		setPressdauer(presswerte.get(0));
		setAbf�lldauer(presswerte.get(1));
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
	
	public static double getPressdauer() {
		return pressdauer;
	}

	public static void setPressdauer(double pressdauer) {
		AdministratorLogik.pressdauer = pressdauer;
	}

	public static double getAbf�lldauer() {
		return abf�lldauer;
	}

	public static void setAbf�lldauer(double abf�lldauer) {
		AdministratorLogik.abf�lldauer = abf�lldauer;
	}
	
	public void datenSpeichern(int anzeige, int zeitslot, int beginn, int ende, int mitProSchicht,
			int schichtenProTag, double pressdauer, double abf�lldauer){
		administratorDB.terminWerteSpeichern(anzeige, zeitslot, beginn, ende);
		administratorDB.schichtWerteSpeichern(mitProSchicht, schichtenProTag);
		administratorDB.pressWerteSpeichern(pressdauer, abf�lldauer);
	}

	
	
}
