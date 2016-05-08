package lagerverwaltung;

import javax.swing.JOptionPane;

import kundenverwaltung.KundenVerwaltung;
import verkaufsverwaltung.Einkaufsposition;

public class Produkt extends Einkaufsposition {

	private int vorratsmenge;
	private int untergrenze;
	private boolean isAbf�llmaterial;

	public Produkt(String name, double preis, int menge, int untergrenze,
			boolean isA, int verkaufsmenge) {
		super(name, preis, verkaufsmenge);
		this.setVorratsmenge(menge);
		this.setUntergrenze(untergrenze);
		this.setAbf�llmaterial(isA);

	}

	public int getVorratsmenge() {
		return vorratsmenge;
	}

	public void setVorratsmenge(int menge) {
		this.vorratsmenge = menge;
		if (vorratsmenge < untergrenze) {
			JOptionPane.showMessageDialog(null,
					"Die angegebene Untergrenze f�r das Produkt " + getName()
							+ " wurde soeben unterschritten.", "Warnung",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public int getUntergrenze() {
		return untergrenze;
	}

	public void setUntergrenze(int untergrenze) {
		this.untergrenze = untergrenze;
	}

	public boolean isAbf�llmaterial() {
		return isAbf�llmaterial;
	}

	public void setAbf�llmaterial(boolean isAbf�llmaterial) {
		this.isAbf�llmaterial = isAbf�llmaterial;
	}

}