package administratorverwaltung;

import java.util.ArrayList;
import java.util.Date;

public class Schicht {
	
	private ArrayList<Integer> mitarbeiterIds;
	private int schichtId;
	private Date uhrzeit;
	
	
	
	ArrayList<Integer> getMitarbeiterIds(){
		return mitarbeiterIds;
	}

	void setMitarbeiterIds(ArrayList<Integer> mitarbeiterId){
		mitarbeiterIds = mitarbeiterId;
	}
	
	int getMitarbeiter(int pos){
		return mitarbeiterIds.get(pos);
	}

	int getSchichtId() {
		return schichtId;
	}

	void setSchichtId(int schichtId) {
		this.schichtId = schichtId;
	}

	Date getUhrzeit() {
		return uhrzeit;
	}

	void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

}
