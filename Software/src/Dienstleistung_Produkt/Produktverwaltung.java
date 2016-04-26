package Dienstleistung_Produkt;

import java.util.ArrayList;

public class Produktverwaltung {

	private ArrayList<Produkt> produktliste;

	public Produktverwaltung() {
		produktliste = new ArrayList<Produkt>();
	}

	public void addProdukt(Produkt p) {
		produktliste.add(p);
	}

	public void deleteProdukt(Produkt p) {
		produktliste.remove(p);
	}

	public ArrayList<Produkt> getProduktliste() {
		return produktliste;
	}

	public int arraygroesse() {
		return produktliste.size();
	}

	public Produkt getProdukt(int index) {
		return produktliste.get(index);
	}

	public void printProdukte() {
		for (Produkt p : produktliste) {
			System.out.println(p.getName() + ", " + p.getPreis() + " € pro L");
		}
	}

}