package schichtverwaltung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class WochenübersichtTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private SchichtLogik schichtlogik;
	private ArrayList<Schicht> schichtplan;
	
	public WochenübersichtTableModel(ArrayList<Schicht> schichtplan){
		schichtlogik = new SchichtLogik();
		this.schichtplan = schichtplan;
	}
	
	
	public int getColumnCount() {
		return schichtlogik.getSchichtenProTag()*6;
	}

	public int getRowCount() {
		return schichtlogik.getMitarbeiterProSchicht();
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
