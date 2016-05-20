package adminestratorenverwaltung;

import java.util.Date;

public class Schicht {
	
	private int mitarbeiterId;
	private int schichtId;
	private Date uhrzeit;
	
	
	
	int getMitarbeiterId(){
		return mitarbeiterId;
	}

	void setMitarbeiterId(int ID){
		mitarbeiterId = ID;
	}

	public int getSchichtId() {
		return schichtId;
	}

	public void setSchichtId(int schichtId) {
		this.schichtId = schichtId;
	}

	public Date getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

}
