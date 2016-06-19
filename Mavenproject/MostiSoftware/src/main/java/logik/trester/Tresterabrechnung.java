package logik.trester;

import java.util.Date;

public class Tresterabrechnung {
	
	private int gesamtLiter;
	private double preis;
	private java.sql.Date date;
	private int kundenID;
	
	public Tresterabrechnung(int kundenid, int literzahl, double preis, java.sql.Date date){
		setKundenID(kundenid);
		gesamtLiter = literzahl;
		this.preis = preis;
		this.date = date;
	}
	
//	private double berechnePreis(){
//		return (getLiterzahl()/1000) * tv.getPreisPro1000L();
//	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	public int getLiterzahl() {
		return gesamtLiter;
	}

	public void setLiterzahl(int liter) {
		this.gesamtLiter = liter;
	}
	
	public void printTresterAbrechnung(){
		System.out.println("Trester"+preis + " € für " + gesamtLiter + " L");
	}

	public Date getDate() {
		return date;
	}

	public void setDate() {
		java.util.Date datum = new Date();
		java.sql.Date date = new java.sql.Date(datum.getTime());
		this.date = date;

	}

	public int getKundenID() {
		return kundenID;
	}

	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}

//	public Kunde getTresterkunde() {
//		return tresterkunde;
//	}
//
//	public void setTresterkunde(Kunde tresterkunde) {
//		this.tresterkunde = tresterkunde;
//	}

}
