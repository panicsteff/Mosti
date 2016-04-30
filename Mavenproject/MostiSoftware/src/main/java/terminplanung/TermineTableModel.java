package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TermineTableModel extends AbstractTableModel {

	ArrayList<Integer> terminliste;
	private int tageszeit;
	TerminDB termindb = new TerminDB();

	public TermineTableModel(ArrayList<Integer> terminliste, int tz) {
		this.terminliste = terminliste;
		tageszeit = tz;
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
		return 36;
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return berechneUhrzeit(row, tageszeit);
			
		case 1:
			return termindb.kundenNamenLaden(terminliste.get(row));
			
		default:
			return null;
		}
	}
	

	public Integer getTermin(int position) {
		return terminliste.get(position);
	}
	
	public String berechneUhrzeit(int row, int tageszeit){
		
		int stunde = 6 + 3*tageszeit;
		int minuten = row*5;
		while(minuten-60 >= 0){
			minuten-=60;
			stunde++;
		}
		
		if(minuten<10){
			return stunde + ":0" + minuten;
		}
		
		return stunde + ":" + minuten;
	}

}
