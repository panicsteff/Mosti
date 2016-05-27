package gui.verkauf;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import logik.verkaufsverwaltung.Verkaufsposition;

@SuppressWarnings("serial")
class VerkäufeTableModel extends AbstractTableModel {

	private ArrayList<Verkaufsposition> vliste;

	VerkäufeTableModel(ArrayList<Verkaufsposition> liste) {
		vliste = liste;
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Name";
		case 1:
			return "Preis pro Stück/Liter [€]";
		case 2:
			return "Verkaufsmenge";
		case 3:
			return "Literzahl";
		default:
			return null;
		}
	}

	public int getRowCount() {
		return (vliste != null ? vliste.size() : 0);
	}

	public Object getValueAt(int row, int col) {
		Verkaufsposition v = vliste.get(row);
		switch (col) {
		case 0:
			return v.getName();
		case 1:
			return v.getPreis();
		case 2:
			return v.getVerkaufsMenge();
		case 3:
			return v.getLiterzahl();
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
		default:
			return null;
		}
	}

	Verkaufsposition getVerkaufsposition(int row) {
		return vliste.get(row);
	}

	ArrayList<Verkaufsposition> getVerkaufspositionen() {
		return vliste;
	}

	void setVerkaufspositionen(ArrayList<Verkaufsposition> liste) {
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
