package administratorverwaltung;

import java.util.ArrayList;

public class Konfigurationswerte {
	
	private static int mitarbeiterProSchicht;
	private static int schichtenProTag;
	private static int schichtDauer;
	private static int arbeitsbeginn;
	private static int arbeitsende;
	
	private SchichtplanDB schichtDb;
	
	public Konfigurationswerte(){
		schichtDb = new SchichtplanDB();
		ArrayList<Integer> konfigurationswerte = schichtDb.adminwerteLaden();
		
		setMitarbeiterProSchicht(konfigurationswerte.get(0));
		setSchichtenProTag(konfigurationswerte.get(1));
		setArbeitsbeginn(konfigurationswerte.get(2));
		setArbeitsende(konfigurationswerte.get(3));
		setSchichtDauer((arbeitsende-arbeitsbeginn)/schichtenProTag);
		
	}
	
	int getMitarbeiterProSchicht() {
		return mitarbeiterProSchicht;
	}
	void setMitarbeiterProSchicht(int zeitslot) {
		Konfigurationswerte.mitarbeiterProSchicht = zeitslot;
	}

	public int getSchichtenProTag() {
		return schichtenProTag;
	}

	public void setSchichtenProTag(int schichtenProTag) {
		Konfigurationswerte.schichtenProTag = schichtenProTag;
	}

	public int getSchichtDauer() {
		return schichtDauer;
	}

	public void setSchichtDauer(int schichtDauer) {
		Konfigurationswerte.schichtDauer = schichtDauer;
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
	
}
