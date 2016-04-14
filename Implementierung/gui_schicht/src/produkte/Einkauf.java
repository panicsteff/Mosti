package produkte;

import java.util.ArrayList;

public class Einkauf {

	private ArrayList<Einkaufsposition> einkaufsliste;
	private double total;

	public Einkauf() {
		einkaufsliste = new ArrayList<Einkaufsposition>();
		total = 0;
	}

	public void addEinkauf(Einkaufsposition position) {
		einkaufsliste.add(position);
	}

	public void printEinkauf() {
		for (Einkaufsposition e : einkaufsliste) {
			System.out.println(e.getAnzahl() + "x " + e.getProdukt().getName()
					+ " a " + e.getProdukt().getPreis()+ " €");
		}
	}

	public double bildeSumme(){
		for (Einkaufsposition e : einkaufsliste) {
			total = total +  (e.getAnzahl()*e.getProdukt().getPreis());
		}
		return total;

	}
}
