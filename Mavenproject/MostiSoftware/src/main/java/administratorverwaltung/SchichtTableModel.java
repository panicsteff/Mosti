package administratorverwaltung;

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
		case 0: return "Vormaittag";
		case 1: return "Nachmittag";
		
		default: return null;
		}
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return schichtliste.get(0).getMitarbeiterIds().size();
	}

	public Object getValueAt(int row, int col) {
		return schichtliste.get(col).getMitarbeiter(row);	
	}
	
//	ArrayList<Schicht> getSchicht(int row, int anzahl){
//		ArrayList<Schicht> liste = new ArrayList<Schicht>();
//		for(int i=0; i<anzahl; i++){
//			liste.add(schichtliste.get(row+i));
//		}
//	
//		return liste;
//	}
//	
//	ArrayList<Schicht> getAlleSchichten(){
//		return schichtliste;
//	}
	
	

}
