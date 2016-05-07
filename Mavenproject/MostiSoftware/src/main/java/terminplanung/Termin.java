package terminplanung;


public class Termin {

	private int kundenId;
	private int terminId;
	
	
	
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
}
