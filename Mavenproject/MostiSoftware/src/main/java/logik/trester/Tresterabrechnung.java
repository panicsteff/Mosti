package logik.trester;

import java.sql.Date;

import kundenverwaltung.Kunde;


public class Tresterabrechnung {
	
	private int gesamtLiter;
	private double preis;
	private Tresterverwaltung tv;
	private Kunde tresterkunde;
	private Date date;
	
	public Tresterabrechnung(Tresterverwaltung tv, Kunde kunde, Date date, int literzahl){
		this.tv = tv;
		gesamtLiter = literzahl;
		setTresterkunde(kunde);
		this.date = date;
		preis = berechnePreis();
	}
	
	private double berechnePreis(){
		return (getLiterzahl()/1000) * tv.getPreisPro1000L();
	}

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

	public void setDate(Date date) {
		this.date = date;
	}

	public Kunde getTresterkunde() {
		return tresterkunde;
	}

	public void setTresterkunde(Kunde tresterkunde) {
		this.tresterkunde = tresterkunde;
	}

}
