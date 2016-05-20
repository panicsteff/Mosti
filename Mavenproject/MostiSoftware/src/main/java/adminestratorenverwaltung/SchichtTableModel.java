package adminestratorenverwaltung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SchichtTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Schicht> schichtliste;
	

	SchichtTableModel(ArrayList<Schicht> schichtliste) {
		this.schichtliste = schichtliste;
	}

	public String getColumnName(int col){
		switch(col){
		case 0: return "Uhrzeit";
		case 1: return "Mitarbeiter";
		
		default: return null;
		}
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return schichtliste.size();
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return schichtliste.get(row).getSchichtId();				
			
		case 1:
			return schichtliste.get(row).getMitarbeiterId();
			
		default:
			return null;
		}
	}
	
	ArrayList<Schicht> getSchicht(int row, int anzahl){
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		for(int i=0; i<anzahl; i++){
			liste.add(schichtliste.get(row+i));
		}
	
		return liste;
	}
	
	ArrayList<Schicht> getAlleSchichten(){
		return schichtliste;
	}
	
	

}
