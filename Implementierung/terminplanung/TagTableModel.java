package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TagTableModel extends AbstractTableModel {

	ArrayList<Termin> terminliste;

	public TagTableModel(ArrayList<Termin> terminliste) {
		this.terminliste = terminliste;
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return 10;
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			switch(row){
			case 0: return "9:00";
			case 1: return "10:00";
			case 2: return "11:00";
			case 3: return "12:00";
			case 4: return "13:00";
			case 5: return "14:00";
			case 6: return "15:00";
			case 7: return "16:00";
			case 8: return "17:00";
			case 9: return "18:00";
			
			}
			
		case 1:
			try{
				return terminliste.get(row).getKunde() + (row+1);
			}
			catch(IndexOutOfBoundsException ex){
				return "";
			}
			
		default:
			return null;
		}
	}

}
