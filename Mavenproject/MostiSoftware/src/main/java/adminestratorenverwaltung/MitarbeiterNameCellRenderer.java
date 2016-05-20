package adminestratorenverwaltung;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MitarbeiterNameCellRenderer extends DefaultTableCellRenderer.UIResource{
	
	private static final long serialVersionUID = 1L;
	private SchichtplanDB schichtdb;
	
	public MitarbeiterNameCellRenderer(){
		schichtdb = new SchichtplanDB();
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean cellHasFocus, int row, int col) {
			super.getTableCellRendererComponent(table, value,
			isSelected, cellHasFocus, row, col);
			
			int mitarbeiterId = (Integer) value;
			String name = schichtdb.mitarbeiterNamenLaden(mitarbeiterId);
			setText(name);
			return this;
			
			
	}		

}
