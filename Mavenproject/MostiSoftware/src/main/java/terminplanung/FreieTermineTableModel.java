package terminplanung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import kundenverwaltung.Formats;

public class FreieTermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> freieTermine;
	private ArrayList<Intervall> intervallListe;

	public FreieTermineTableModel(ArrayList<Integer> ft) {
		freieTermine = ft;
		berechneLücken();

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
			return Formats.DATE_FORMAT.format(new Date());
		case 1:
			return intervallListe.get(row).getStart();
		case 2:
			return intervallListe.get(row).getEnde() + 1;			//Die Zeitspanne geht dann bis eine sek vor dem nächsten Termin, also ist das Ende "eins später" als notiert
		default:
			return null;
		}
	}
	

	private void berechneLücken() {

		intervallListe = new ArrayList<Intervall>();

		Intervall in = new Intervall();
		in.setStart(freieTermine.get(0));

		int i;
		for (i = 0; i < freieTermine.size() - 1; i++) {
			if (freieTermine.get(i + 1) != freieTermine.get(i) + 1) {
				in.setEnde(freieTermine.get(i));								
				intervallListe.add(in);
				in = new Intervall();
				in.setStart(freieTermine.get(i + 1));
			}
		}
		in.setEnde(freieTermine.get(i)); // letzter Termin ist immer das Ende
		intervallListe.add(in);
	}

}

class Intervall {
	private int start;
	private int ende;

	public int getEnde() {
		return ende;
	}

	public void setEnde(int ende) {
		this.ende = ende;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}