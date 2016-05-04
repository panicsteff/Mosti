package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> terminliste;
	private int zeitslot;
	private int arbeitsbeginn;
	private int anzeigeseite;
	private TerminDB termindb;
	

	TermineTableModel(ArrayList<Integer> terminliste, int as, ArrayList<Integer> adminwerte) {
		this.terminliste = terminliste;
		anzeigeseite = as;
		zeitslot = adminwerte.get(0);
		arbeitsbeginn = adminwerte.get(1);
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
			return berechneUhrzeit(row, anzeigeseite);
			
		case 1:
			return termindb.kundenNamenLaden(terminliste.get(row));
			
		default:
			return null;
		}
	}
	
	private String berechneUhrzeit(int row, int anzeigeseite){
		
		int stunde = 0;
		int minuten = (arbeitsbeginn + terminliste.size()*(anzeigeseite-1)*zeitslot) + row*zeitslot;
		while(minuten-60 >= 0){
			minuten-=60;
			stunde++;
		}
		
		if(minuten<10){
			return stunde + ":0" + minuten;
		}
		
		return stunde + ":" + minuten;
	}
	
	int getTermin(int t){
		return terminliste.get(t);
	}

}
