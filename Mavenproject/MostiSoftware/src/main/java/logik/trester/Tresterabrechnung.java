package logik.trester;

import java.util.Date;

import logik.kundenverwaltung.Kunde;


public class Tresterabrechnung {
	
	private int gesamtLiter;
	private double preis;
	private Tresterverwaltung tv;
	private Kunde tresterkunde;
	private java.sql.Date date;
	
	public Tresterabrechnung(/*Tresterverwaltung tv,*/Kunde kunde, int literzahl, double preis){
		//this.tv = tv;
		gesamtLiter = literzahl;
		setTresterkunde(kunde);
		setDate();
		this.preis = preis;
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

	public Kunde getTresterkunde() {
		return tresterkunde;
	}

	public void setTresterkunde(Kunde tresterkunde) {
		this.tresterkunde = tresterkunde;
	}

}
