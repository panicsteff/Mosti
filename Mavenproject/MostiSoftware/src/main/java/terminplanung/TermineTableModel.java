package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> terminliste;
	

	TermineTableModel(ArrayList<Termin> terminliste) {
		this.terminliste = terminliste;
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
			return terminliste.get(row).getTerminId();				
			
		case 1:
			return terminliste.get(row).getKundenId();
			
		default:
			return null;
		}
	}
	
	ArrayList<Termin> getTermine(int row, int anzahl){
		ArrayList<Termin> liste = new ArrayList<Termin>();
		for(int i=0; i<anzahl; i++){
			liste.add(terminliste.get(row+i));
		}
	
		return liste;
	}
	
	ArrayList<Termin> getAlleTermine(){
		return terminliste;
	}
	
	
}
