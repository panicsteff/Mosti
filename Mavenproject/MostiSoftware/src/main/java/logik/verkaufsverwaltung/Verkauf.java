package logik.verkaufsverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;

public class Verkauf {

	private ArrayList<Verkaufsposition> einkaufsliste;
	private int literzahl;
	private double total;
	private Kunde kunde;
	private Date date;
	//private Verkaufsposition vposition;
	
	public Verkauf(Kunde kunde, Date date, ArrayList<Verkaufsposition> liste) {
		this.setKunde(kunde);
		setVerkaufsDatum(date);
		einkaufsliste = liste;
		literzahl = berechneLiterzahl();
		total = berechneTotal();
	}
	
	

	public void addEinkaufsposition(Verkaufsposition position) {
		einkaufsliste.add(position);
		//total = total + position.getVerkaufsMenge()*position.getPreis();
	}
	
	public Verkaufsposition getVerkaufsposition(int index){
		return einkaufsliste.get(index);
	}
	
	public ArrayList<Verkaufsposition> getVerkäufeListe(){
		return einkaufsliste;
	}
	
	public int getListengröße(){
		return einkaufsliste.size();
	}

	public void printEinkauf() {
		for (Verkaufsposition e : einkaufsliste) {
				System.out.print(e.getVerkaufsMenge() + "x " + e.getName()+ ", ");
		}
		System.out.println(" für insgesamt " + getSumme()+ " €");
	}

	public void setSumme(double total) {
		this.total = total;
	}

	public double getSumme() {
		return total;
	}

	public int getLiterzahl() {
		return literzahl;
	}
	
	public int berechneLiterzahl(){
		int summe = 0;
		for(Verkaufsposition v: einkaufsliste){
			summe = summe + v.getLiterzahl();
		}
		return summe;
	}
	
	public double berechneTotal(){
		double erg = 0.0;
		int faktor = 0;
		for(Verkaufsposition v: einkaufsliste){
			if(v.getLiterzahl() != 0)
				faktor = v.getLiterzahl();
			else 
				faktor = v.getVerkaufsMenge();
			erg = erg + faktor*v.getPreis();
		}
		return erg;
	}
	
	

//	public void setLiterzahl(int literzahl) {
//		this.literzahl = literzahl;
//	}

	public Date getVerkaufsDatum() {
		return date;
	}

	public void setVerkaufsDatum(Date date) {
		this.date = date;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

}