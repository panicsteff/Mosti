package logik.produktverwaltung;

import javax.swing.JOptionPane;

import logik.verkaufsverwaltung.Verkaufsposition;

public class Produkt extends Verkaufsposition {

	private int vorratsmenge;
	private int untergrenze;
	private boolean isAbfüllmaterial;
	private static final int liter = 0;

	public Produkt(String name, double preis, int menge, int untergrenze,
			boolean isA, int verkaufsmenge) {
		super(name, preis, verkaufsmenge, liter);
		this.setVorratsmenge(menge);
		this.setUntergrenze(untergrenze);
		this.setAbfüllmaterial(isA);
	}

	public int getVorratsmenge() {
		return vorratsmenge;
	}

	public void setVorratsmenge(int menge) {
		this.vorratsmenge = menge;
		if (vorratsmenge < untergrenze) {
			JOptionPane.showMessageDialog(null,
					"Die angegebene Untergrenze für das Produkt " + getName()
							+ " wurde soeben unterschritten.", "Warnung",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void setLiterzahl(int literzahl) {
		super.setLiterzahl(liter);
	}

	public int getUntergrenze() {
		return untergrenze;
	}

	public void setUntergrenze(int untergrenze) {
		this.untergrenze = untergrenze;
	}

	public boolean isAbfüllmaterial() {
		return isAbfüllmaterial;
	}

	public void setAbfüllmaterial(boolean isAbfüllmaterial) {
		this.isAbfüllmaterial = isAbfüllmaterial;
	}


}