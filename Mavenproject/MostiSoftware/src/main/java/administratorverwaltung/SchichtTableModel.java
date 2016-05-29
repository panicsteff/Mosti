package administratorverwaltung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SchichtTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Schicht> schichtliste;
	private Konfigurationswerte k;
	

	SchichtTableModel(ArrayList<Schicht> schichtliste) {
		this.schichtliste = schichtliste;
		k = new Konfigurationswerte();
	}

	public String getColumnName(int col){
		switch(col){
		case 0: return "Vormaittag";
		case 1: return "Nachmittag";
		
		default: return null;
		}
	}
	
	public int getColumnCount() {
		return k.getSchichtenProTag();
	}

	public int getRowCount() {
		return k.getMitarbeiterProSchicht();
	}

	public Object getValueAt(int row, int col) {
		if(schichtliste.get(col).getAnzahlMitarbeiter()<= row){
			return "";
		}
		
		return schichtliste.get(col).getMitarbeiterId(row);	
	}
	
	
	

}
