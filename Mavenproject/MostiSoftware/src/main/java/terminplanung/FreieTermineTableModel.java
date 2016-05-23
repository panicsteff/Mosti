package terminplanung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import kundenverwaltung.Formats;

public class FreieTermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> freieTermine;
	private ArrayList<Intervall> intervallListe;
	private Date datum;

	public FreieTermineTableModel(ArrayList<Termin> ft, Date d) {
		freieTermine = ft;
		berechneL�cken();
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
			return intervallListe.get(row).getEnde() + 1;			//Die Zeitspanne geht dann bis eine sek vor dem n�chsten Termin, also ist das Ende "eins sp�ter" als notiert
		default:
			return null;
		}
	}
	

	private void berechneL�cken() {

		intervallListe = new ArrayList<Intervall>();

		Intervall in = new Intervall();
		in.setStart(freieTermine.get(0).getTerminId());

		int i;
		for (i = 0; i < freieTermine.size() - 1; i++) {
			if (freieTermine.get(i + 1).getTerminId() != freieTermine.get(i).getTerminId() + 1) {
				in.setEnde(freieTermine.get(i).getTerminId());								
				intervallListe.add(in);
				in = new Intervall();
				in.setStart(freieTermine.get(i + 1).getTerminId());
			}
		}
		in.setEnde(freieTermine.get(i).getTerminId()); // letzter Termin ist immer das Ende
		intervallListe.add(in);
	}

}
