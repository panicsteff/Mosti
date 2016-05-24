package terminplanung;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TermineCellRenderer extends DefaultTableCellRenderer.UIResource{

	private static final long serialVersionUID = 1L;
	private TerminLogik terminlogik;
	
	public TermineCellRenderer(){
		super();
		terminlogik = new TerminLogik();
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean cellHasFocus, int row, int col) {
			super.getTableCellRendererComponent(table, value,
			isSelected, cellHasFocus, row, col);
			
			int terminid = (Integer) value;
			String s = terminlogik.terminNachUhrzeit(terminid);
			setText(s);
			return this;
	}


}	