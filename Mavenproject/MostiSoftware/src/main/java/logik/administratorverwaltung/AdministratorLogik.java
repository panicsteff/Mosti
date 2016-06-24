package logik.administratorverwaltung;

import java.sql.Date;
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
	private static double abfülldauer;
	private static int backupdauer;
	private static Date letztesBackup;
	
	public AdministratorLogik(){
		administratorDB = new AdministratorDB();
		
		ArrayList<Integer> terminwerte = administratorDB.terminWerteLaden();
		ArrayList<Integer> schichtwerte = administratorDB.schichtWerteLaden();
		ArrayList<Double> presswerte = administratorDB.pressWerteLaden();
		int backup = administratorDB.backupDauerLaden();
		Date letztesBackup = administratorDB.letztesBackupLaden();
		
		setZeitslot(terminwerte.get(0));
		setArbeitsbeginn(terminwerte.get(1));
		setArbeitsende(terminwerte.get(2));
		setAufteilung(terminwerte.get(3));
		setZeilenanzahlProSeite((arbeitsende-arbeitsbeginn)/(zeitslot*aufteilung));
		
		setMitarbeiterProSchicht(schichtwerte.get(0));
		setSchichtenProTag(schichtwerte.get(1));
		setSchichtDauer((arbeitsende-arbeitsbeginn)/schichtenProTag);
		
		setPressdauer(presswerte.get(0));
		setAbfülldauer(presswerte.get(1));
		
		setBackupdauer(backup);
		setLetztesBackup(letztesBackup);
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

	public static double getAbfülldauer() {
		return abfülldauer;
	}

	public static void setAbfülldauer(double abfülldauer) {
		AdministratorLogik.abfülldauer = abfülldauer;
	}
	
	public static int getBackupdauer() {
		return backupdauer;
	}

	public static void setBackupdauer(int backupdauer) {
		AdministratorLogik.backupdauer = backupdauer;
	}
	
	public static long getLetztesBackup() {
		return letztesBackup.getTime();
	}

	public static void setLetztesBackup(Date letztesBackup) {
		AdministratorLogik.letztesBackup = letztesBackup;
	}

	
	public void datenSpeichern(int anzeige, int zeitslot, int beginn, int ende, int mitProSchicht,
			int schichtenProTag, double pressdauer, double abfülldauer, int backup){
		administratorDB.terminWerteSpeichern(anzeige, zeitslot, beginn, ende);
		administratorDB.schichtWerteSpeichern(mitProSchicht, schichtenProTag);
		administratorDB.pressWerteSpeichern(pressdauer, abfülldauer);
		administratorDB.backupdauerSpeichern(backup);
	}
	
	public static void letztesBackupSpeichern(long date){
		AdministratorDB.letztesBackupSpeichern(new Date(date));
	}
	
	public int terminNachInteger(String s){
		int index = s.indexOf(":");
		String stunde  = s.substring(0, index);
		String minuten = s.substring(index+1, s.length());
		
		int stundenzahl;
		int minutenzahl;
		
		try{
			stundenzahl = Integer.parseInt(stunde);
			minutenzahl = Integer.parseInt(minuten);
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		int uhrzeit = stundenzahl*60 + minutenzahl;
		
		return uhrzeit;
	}
	
	public String terminInString(int uhrzeit){
		int stunde = uhrzeit/60;
		int minute = uhrzeit%60;
		
		String ausgabe = null;
		if(stunde<10){
			ausgabe = "0"+stunde;
		}else{
			ausgabe = stunde+"";
		}
		if(minute<10){
			ausgabe = ausgabe + ":0" + minute;
		}else{
			ausgabe = ausgabe + ":" + minute;
		}		
		return ausgabe;
	}

	

	
	
	
}
