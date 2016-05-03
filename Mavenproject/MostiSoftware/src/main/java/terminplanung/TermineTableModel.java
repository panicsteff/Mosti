package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> terminliste;
	private int zeitslot;
	private int arbeitsbeginn;
	private int arbeitsende;
	private int anzeigeseite;
	private int aufteilung;
	private TerminDB termindb;
	

	public TermineTableModel(ArrayList<Integer> terminliste, int as) {
		this.terminliste = terminliste;
		termindb = new TerminDB();
		ArrayList<Integer> adminwerte = termindb.ladeAdminwerte();
		zeitslot = adminwerte.get(0);
		arbeitsbeginn = adminwerte.get(1);
		arbeitsende = adminwerte.get(2);
		aufteilung = adminwerte.get(3);
		anzeigeseite = as;
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
		return berechneZeilen();
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
	

	public Integer getTermin(int position) {
		return terminliste.get(position);
	}
	
	public String berechneUhrzeit(int row, int anzeigeseite){
		
		int stunde = (arbeitsbeginn + berechneZeilen()*(anzeigeseite-1)*zeitslot)/60;
		int restminuten = (arbeitsbeginn + berechneZeilen()*(anzeigeseite-1)*zeitslot)%60;
		int minuten = row * zeitslot + restminuten;
		while(minuten-60 >= 0){
			minuten-=60;
			stunde++;
		}
		
		if(minuten<10){
			return stunde + ":0" + minuten;
		}
		
		return stunde + ":" + minuten;
	}
	
	public int berechneZeilen(){
		int spalten = (arbeitsende-arbeitsbeginn)/(zeitslot*aufteilung);
		
		return spalten;
	}
	
	public int getAufteilung(){
		return aufteilung;
	}

}
