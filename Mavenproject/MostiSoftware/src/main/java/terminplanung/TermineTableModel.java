package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> terminliste;
	private Konfigurationswerte k = new Konfigurationswerte();
	private int anzeigeseite;
	private TerminDB termindb;
	

	TermineTableModel(ArrayList<Integer> terminliste, int as) {
		this.terminliste = terminliste;
		anzeigeseite = as;
		termindb = new TerminDB();
	}

	public String getColumnName(int col){
		switch(col){
		case 0: return "Uhrzeit";
		case 1: return "Kunde";
		
		default: return null;
		}
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return terminliste.size();
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return (anzeigeseite-1)*k.getZeilenanzahlProSeite() + row +1;					//absolute TerminId berechenen, noch um ein erhühen weils die zeile null gib aber nur mindestens den Termin 1
			
		case 1:
			return termindb.kundenNamenLaden(terminliste.get(row));
			
		default:
			return null;
		}
	}
	
	
}
