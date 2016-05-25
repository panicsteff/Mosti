package verkaufsverwaltung;

public class GroberEinkauf {
	
	private int gesamtLiterZahl;
	private double gesamtKosten;
	
	public GroberEinkauf(){                 // wird für einen bestimmten Tag erstellt, braucht evtl. noch Datum
		setGesamtLiterZahl(0);
		setGesamtKosten(0);
	}

	public int getGesamtLiterZahl() {
		return gesamtLiterZahl;
	}

	public void addToGesamtLiterZahl(int zusatzliter){
		gesamtLiterZahl = gesamtLiterZahl + zusatzliter;
	}

	public double getGesamtKosten() {
		return gesamtKosten;
	}
	
	public void addToGesamtLiterZahl(double zusatzkosten){
		gesamtKosten = gesamtKosten + zusatzkosten;
	}
	
	public void printGrobenEinkauf(){
		System.out.println("Liter: "+ getGesamtLiterZahl()+ ", Einnahmen: "+ getGesamtKosten());
	}
	
	public void setGesamtLiterZahl(int gesamtLiterZahl) {
		this.gesamtLiterZahl = gesamtLiterZahl;
	}
	
	public void setGesamtKosten(double gesamtKosten) {
		this.gesamtKosten = gesamtKosten;
	}
}
