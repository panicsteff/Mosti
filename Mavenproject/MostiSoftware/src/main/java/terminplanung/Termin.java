package terminplanung;

import kundenverwaltung.Kunde;



public class Termin {

	private int kundenId;
	private int terminId;
	private Kunde kunde;
	
	
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

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}


}
