package terminplanung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import kundenverwaltung.Formats;

public class FreieTermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> freieTermine;
	private ArrayList<Intervall> intervallListe;
	private Konfigurationswerte k;
	private Date datum;

	public FreieTermineTableModel(ArrayList<Termin> ft, Date d) {
		freieTermine = ft;
		k = new Konfigurationswerte();
		berechneLücken();
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
			return intervallListe.get(row).getEnde() + k.getZeitslot();			//Die Zeitspanne geht dann bis eine sek vor dem nächsten Termin, also ist das Ende "eins später" als notiert
		default:
			return null;
		}
	}
	

	private void berechneLücken() {

		intervallListe = new ArrayList<Intervall>();

		Intervall in = new Intervall();
		in.setStart(freieTermine.get(0).getUhrzeit());

		int i;
		for (i = 0; i < freieTermine.size() - 1; i++) {
			if (freieTermine.get(i + 1).getUhrzeit() != freieTermine.get(i).getUhrzeit() + k.getZeitslot()) {
				in.setEnde(freieTermine.get(i).getUhrzeit());								
				intervallListe.add(in);
				in = new Intervall();
				in.setStart(freieTermine.get(i + 1).getUhrzeit());
			}
		}
		in.setEnde(freieTermine.get(i).getUhrzeit()); // letzter Termin ist immer das Ende
		intervallListe.add(in);
	}

}
