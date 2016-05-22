package administratorverwaltung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import mitarbeiterverwaltung.Formats;

public class FreieSchichtTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Schicht> freieSchicht;
	private ArrayList<Intervall> intervallListe;

	public FreieSchichtTableModel(ArrayList<Schicht> fs) {
		freieSchicht = fs;
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
		in.setStart(freieSchicht.get(0).getSchichtId());

		int i;
		for (i = 0; i < freieSchicht.size() - 1; i++) {
			if (freieSchicht.get(i + 1).getSchichtId() != freieSchicht.get(i).getSchichtId() + 1) {
				in.setEnde(freieSchicht.get(i).getSchichtId());								
				intervallListe.add(in);
				in = new Intervall();
				in.setStart(freieSchicht.get(i + 1).getSchichtId());
			}
		}
		in.setEnde(freieSchicht.get(i).getSchichtId()); // letzter Termin ist immer das Ende
		intervallListe.add(in);
	}


}
