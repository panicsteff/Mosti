package verkaufsverwaltung;

import java.util.ArrayList;

public class Einkauf {

	private ArrayList<Einkaufsposition> einkaufsliste;
	private int literzahl;
	private double total;

	public Einkauf() {
		einkaufsliste = new ArrayList<Einkaufsposition>();
		setLiterzahl(0);
		total = 0;
	}

	public void addEinkauf(Einkaufsposition position) {
		einkaufsliste.add(position);
		//total = total + position.getVerkaufsMenge()*position.getPreis();
	}

	public void printEinkauf() {
		for (Einkaufsposition e : einkaufsliste) {
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

}