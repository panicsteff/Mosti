package schichtverwaltung;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MitarbeiterNameCellRenderer extends DefaultTableCellRenderer.UIResource{
	
	private static final long serialVersionUID = 1L;
	private SchichtLogik schichtlogik;
	
	public MitarbeiterNameCellRenderer(){
		schichtlogik = new SchichtLogik();
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean cellHasFocus, int row, int col) {
			super.getTableCellRendererComponent(table, value,
			isSelected, cellHasFocus, row, col);
			
			String name = new String();
			try{
				int mitarbeiterId = (Integer) value;
				name = schichtlogik.mitarbeiternameLaden(mitarbeiterId);
			}catch(ClassCastException e){
				;
			}
			setText(name);
			return this;
			
			
	}		

}
