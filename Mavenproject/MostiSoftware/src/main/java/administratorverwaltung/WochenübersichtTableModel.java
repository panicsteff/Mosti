package administratorverwaltung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class WochenübersichtTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private Konfigurationswerte k;
	private ArrayList<Schicht> schichtplan;
	
	public WochenübersichtTableModel(ArrayList<Schicht> schichtplan){
		k = new Konfigurationswerte();
		this.schichtplan = schichtplan;
	}
	
	
	public int getColumnCount() {
		return k.getSchichtenProTag()*6;
	}

	public int getRowCount() {
		return k.getMitarbeiterProSchicht();
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
