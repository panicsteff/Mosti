package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> terminliste;
	private int anzeigeseite;
	private TerminLogik terminlogik;
	

	TermineTableModel(ArrayList<Termin> terminliste, int as) {
		this.terminliste = terminliste;
		anzeigeseite = as; 
		terminlogik = new TerminLogik();
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
		return terminlogik.getZeilenAnzahlProSeite();
	}

	public Object getValueAt(int row, int col) {
		
		int reihe = (anzeigeseite - 1) * terminlogik.getZeilenAnzahlProSeite() + row;
		
		switch (col) {
		case 0:
			return terminliste.get(reihe).getTerminId();				
			
		case 1:
			return terminliste.get(reihe).getKundenId();
			
		default:
			return null;
		}
	}
	
	ArrayList<Termin> getTermine(int row, int anzahl){
		ArrayList<Termin> liste = new ArrayList<Termin>();
		try{
			for(int i=0; i<anzahl; i++){
				liste.add(terminliste.get(row+i));
			}
		} catch(IndexOutOfBoundsException e){
			;
		}
		
	
		return liste;
	}
	
	ArrayList<Termin> getAlleTermine(){
		return terminliste;
	}
	
	void erhoeheAnzeigeseite(){
		anzeigeseite++;
	}
	
	void erniedrigeAnzeigeseite(){
		anzeigeseite--;
	}
	
	
}
