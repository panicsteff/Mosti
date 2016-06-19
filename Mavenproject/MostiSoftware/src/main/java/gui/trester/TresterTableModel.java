package gui.trester;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import persistenz.KundeDB;
import logik.kundenverwaltung.Kunde;
import logik.trester.Tresterabrechnung;

@SuppressWarnings("serial")
class TresterTableModel extends AbstractTableModel {

	private ArrayList<Tresterabrechnung> tliste;
	private KundeDB kundedb;

	TresterTableModel(ArrayList<Tresterabrechnung> liste) {
		tliste = liste;
		kundedb = new KundeDB();
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Tresterkunde";
		case 1:
			return "Literzahl";
		case 2:
			return "Preis pro 1000 Liter [€]";
		case 3:
			return "Summe";
		case 4:
			return "Verkaufsdatum";
		default:
			return null;
		}
	}

	public int getRowCount() {
		return (tliste != null ? tliste.size() : 0);
	}

	public Object getValueAt(int row, int col) {
		Tresterabrechnung t = tliste.get(row);
		switch (col) {
		case 0:
			Kunde kunde = kundedb.einzelnenKundeLaden(t.getKundenID());
			return kunde.getVorname() + " " + kunde.getNachname();
		case 1:
			return t.getLiterzahl();
		case 2:
			return t.getPreis();
		case 3:
			return t.getLiterzahl()*(t.getPreis()/1000.0);
		case 4:
			return t.getDate();

		default:
			return null;
		}
	}

	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0:
			return Integer.class;
		case 1:
			return Integer.class;
		case 2:
			return Double.class;
		case 3:
			return Double.class;
		case 4:
			return Date.class;
		default:
			return null;
		}
	}

	Tresterabrechnung getTresterabrechnung(int row) {
		return tliste.get(row);
	}

	ArrayList<Tresterabrechnung> getTresterabrechnungen() {
		return tliste;
	}

	void setTresterabrechnungen(ArrayList<Tresterabrechnung> liste) {
		tliste = liste;
	}

	double berechneKostenGesamt() {
		double sum = 0;
		for (int i = 0; i < getRowCount(); i++) {
			sum = sum + (Double) getValueAt(i, 3);
		}
		sum = Math.round(sum * 100);
		sum = sum / 100;
		return sum;
	}

	int berechneLiterGesamt() {
		int sum = 0;
		for (int i = 0; i < getRowCount(); i++) {
			sum = sum + (Integer) getValueAt(i, 1);
			// System.out.println((Integer)getValueAt(0, i));
		}
		return sum;
	}

}

