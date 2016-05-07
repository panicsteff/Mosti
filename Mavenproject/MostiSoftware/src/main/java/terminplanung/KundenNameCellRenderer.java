package terminplanung;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class KundenNameCellRenderer extends DefaultTableCellRenderer.UIResource{
	
	private static final long serialVersionUID = 1L;
	private TerminDB termindb;
	
	public KundenNameCellRenderer(){
		termindb = new TerminDB();
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean cellHasFocus, int row, int col) {
			super.getTableCellRendererComponent(table, value,
			isSelected, cellHasFocus, row, col);
			
			int kundenId = (Integer) value;
			String name = termindb.kundenNamenLaden(kundenId);
			setText(name);
			return this;
			
			
	}		
}
