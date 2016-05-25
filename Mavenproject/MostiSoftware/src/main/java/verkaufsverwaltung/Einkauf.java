package verkaufsverwaltung;

import java.util.ArrayList;
import java.util.Date;

import kundenverwaltung.Kunde;

public class Einkauf {

	private ArrayList<Verkaufsposition> einkaufsliste;
	private int literzahl;
	private double total;
	private Kunde kunde;
	private Date date;

//	public Einkauf(Kunde kunde, Date date) {
//		this.setKunde(kunde);
//		setVerkaufsDatum(date);
//		einkaufsliste = new ArrayList<Einkaufsposition>();
//		setLiterzahl(0);
//		setSumme(0);
//	}
	
	public Einkauf(Kunde kunde, Date date, ArrayList<Verkaufsposition> liste) {
		this.setKunde(kunde);
		setVerkaufsDatum(date);
		einkaufsliste = liste;
//		setLiterzahl(0);
//		setSumme(0);
	}

	public void addEinkaufsposition(Verkaufsposition position) {
		einkaufsliste.add(position);
		//total = total + position.getVerkaufsMenge()*position.getPreis();
	}

	public void printEinkauf() {
		for (Verkaufsposition e : einkaufsliste) {
				System.out.print(e.getVerkaufsMenge() + "x " + e.getName()+ ", ");
		}
		System.out.println(" für insgesamt " + getsumme()+ " €");
	}

	public void setSumme(double total) {
		this.total = total;
	}

	public double getsumme() {
		return total;
	}

	public int getLiterzahl() {
		return literzahl;
	}

	public void setLiterzahl(int literzahl) {
		this.literzahl = literzahl;
	}

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