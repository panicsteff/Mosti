package gui.verkauf;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import persistenz.KundeDB;
import logik.kundenverwaltung.Kunde;
import logik.verkaufsverwaltung.Verkaufsposition;
import logik.verkaufsverwaltung.VerkaufspositionPlus;

@SuppressWarnings("serial")
class VerkäufeTableModel extends AbstractTableModel {

	//private ArrayList<Verkaufsposition> vliste;
	private ArrayList<VerkaufspositionPlus> vliste;
	private KundeDB kundedb;

	VerkäufeTableModel(ArrayList<VerkaufspositionPlus> liste) {
		vliste = liste;
		kundedb = new KundeDB();
	}

	public int getColumnCount() {
		return 6;
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Name";
		case 1:
			return "Preis pro Stück/Liter";
		case 2:
			return "Verkaufsmenge";
		case 3:
			return "Literzahl";
		case 4:
			return "Kunde";
		case 5:
			return "Verkaufsdatum";
		default:
			return null;
		}
	}

	public int getRowCount() {
		return (vliste != null ? vliste.size() : 0);
	}

	public Object getValueAt(int row, int col) {
		VerkaufspositionPlus v = vliste.get(row);
		switch (col) {
		case 0:
			return v.getName();
		case 1:
			return v.getPreis();
		case 2:
			return v.getVerkaufsMenge();
		case 3:
			return v.getLiterzahl();
		case 4:
			Kunde kunde = kundedb.einzelnenKundeLaden(v.getKundenID());
			return kunde.getVorname() + " " + kunde.getNachname();
		case 5:
			return v.getDate();
		default:
			return null;
		}
	}

	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0:
			return String.class;
		case 1:
			return Double.class;
		case 2:
			return Integer.class;
		case 3:
			return Integer.class;
		case 4:
			return String.class;
		case 5:
			return Date.class;
		default:
			return null;
		}
	}

	Verkaufsposition getVerkaufsposition(int row) {
		return vliste.get(row);
	}

	ArrayList<VerkaufspositionPlus> getVerkaufspositionen() {
		return vliste;
	}

	void setVerkaufspositionen(ArrayList<VerkaufspositionPlus> liste) {
		vliste = liste;
	}

	double berechneKostenGesamt() {
		double sum = 0;
		for (int i = 0; i < getRowCount(); i++) {
			sum = sum
					+ ((Integer) (getValueAt(i, 2)) * vliste.get(i)
							.getPreis())+ ((Integer) (getValueAt(i, 3)) * vliste.get(i)
									.getPreis());
//			System.out.println((Integer) (getValueAt(0, i)) + " und "
//					+ vliste.get(i).getPreis());

		}
		sum = Math.round(sum * 100);
		sum = sum / 100;
		return sum;

	}

	int berechneLiterGesamt() {
		int sum = 0;
		for (int i = 0; i < getRowCount(); i++) {
			sum = sum + (Integer) getValueAt(i, 3);
			// System.out.println((Integer)getValueAt(0, i));
		}
		return sum;
	}

}
