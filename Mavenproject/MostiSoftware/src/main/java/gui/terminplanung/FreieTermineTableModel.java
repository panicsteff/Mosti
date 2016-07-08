package gui.terminplanung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import logik.kundenverwaltung.Formats;
import logik.terminplanung.Intervall;
import logik.terminplanung.Termin;
import logik.terminplanung.TerminLogik;

public class FreieTermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> freieTermine;
	private ArrayList<Intervall> intervallListe;
	private TerminLogik terminlogik;
	private Date datum;

	public FreieTermineTableModel(ArrayList<Termin> ft, Date d, int dauer) {
		freieTermine = ft;
		terminlogik = new TerminLogik();
		intervallListe = terminlogik.berechneLücken(freieTermine, dauer);
		datum = d;

	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Tag";
		case 1:
			return "Von";
		case 2:
			return "Bis";
		default:
			return null;
		}
	}

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return intervallListe.size();
	}

	public Object getValueAt(int row, int col) {

		switch (col) {
		case 0:
			return Formats.DATE_FORMAT.format(datum);
		case 1:
			return intervallListe.get(row).getStart();
		case 2:
			return intervallListe.get(row).getEnde()
					+ terminlogik.getZeitslot(); // Die Zeitspanne geht dann bis
													// eine sek vor dem nächsten
													// Termin, also ist das Ende
													// "eins später" als notiert
		default:
			return null;
		}
	}

}
