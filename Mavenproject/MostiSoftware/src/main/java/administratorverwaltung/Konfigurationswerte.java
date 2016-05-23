package administratorverwaltung;

import java.util.ArrayList;

public class Konfigurationswerte {
	
	private static int mitarbeiterProSchicht;
	private static int schichtenProTag;
	
	private SchichtplanDB schichtDb;
	
	public Konfigurationswerte(){
		schichtDb = new SchichtplanDB();
		ArrayList<Integer> konfigurationswerte = schichtDb.adminwerteLaden();
		
		setMitarbeiterProSchicht(konfigurationswerte.get(0));
		setSchichtenProTag(konfigurationswerte.get(1));
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
	
}
