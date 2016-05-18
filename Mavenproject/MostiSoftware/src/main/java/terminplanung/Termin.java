package terminplanung;

import java.util.Date;


public class Termin {

	private int kundenId;
	private int terminId;
	private Date uhrzeit;
	
	
	
	int getKundenId(){
		return kundenId;
	}

	void setKundenId(int ID){
		kundenId = ID;
	}

	public int getTerminId() {
		return terminId;
	}

	public void setTerminId(int terminId) {
		this.terminId = terminId;
	}

	public Date getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
}
