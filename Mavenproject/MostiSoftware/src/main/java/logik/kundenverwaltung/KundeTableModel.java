package logik.kundenverwaltung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class KundeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Kunde> kunden;

	public KundeTableModel() {
		kunden = new ArrayList<Kunde>();
	}

	public Class<?> getColumnClass(int col) {
		switch (col) {
		default:
			return String.class;
		}
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Nachname";
		case 1:
			return "Vorname";
		case 2:
			return "Straße";
		case 3:
			return "Hausnummer";
		case 4:
			return "Plz";
		case 5:
			return "Wohnort";
		case 6:
			return "Telefonnummer";
		default:
			return null;
		}
	}

	public int getRowCount() {
		return kunden != null ? kunden.size() : 0;
	}

	public int getColumnCount() {
		return 7;
	}

	public Object getValueAt(int row, int col) {
		Kunde kunde = kunden.get(row);
		switch (col) {

		case 0:
			return kunde.getNachname();
		case 1:
			return kunde.getVorname();
		case 2:
			return kunde.getStrasse();
		case 3:
			return kunde.getHausnummer();
		case 4:
			return kunde.getPlz();
		case 5:
			return kunde.getWohnort();
		case 6:
			return kunde.getTel();
		default:
			return null;
		}
	}

	public ArrayList<Kunde> getKunden() {
		return kunden;
	}

	public void setKunden(ArrayList<Kunde> kunden) {
		this.kunden = kunden;
	}

	public Kunde getKunde(int row) {
		return kunden.get(row);
	}

	public void deletKunde(Kunde k) {
		kunden.remove(k);
	}
}
