package gui.terminplanung;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import logik.terminplanung.TerminLogik;

public class KundenNameCellRenderer extends DefaultTableCellRenderer.UIResource{
	
	private static final long serialVersionUID = 1L;
	private TerminLogik terminlogik;
	
	public KundenNameCellRenderer(){
		terminlogik = new TerminLogik();
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean cellHasFocus, int row, int col) {
			super.getTableCellRendererComponent(table, value,
			isSelected, cellHasFocus, row, col);
			
			int kundenId = (Integer) value;
			String name = terminlogik.kundenNamenLaden(kundenId);
			setText(name);
			return this;
			
			
	}		
}
