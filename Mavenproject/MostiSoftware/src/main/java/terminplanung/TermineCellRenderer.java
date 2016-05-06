package terminplanung;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TermineCellRenderer extends DefaultTableCellRenderer.UIResource{

	private static final long serialVersionUID = 1L;
	private Konfigurationswerte k = new Konfigurationswerte();

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean cellHasFocus, int row, int col) {
			super.getTableCellRendererComponent(table, value,
			isSelected, cellHasFocus, row, col);
			
			int terminid = (Integer) value;
			String s = terminNachUhrzeit(terminid);
			setText(s);
			return this;
	}

	private String terminNachUhrzeit(int terminId) {
		
		int stunde = 0;
		int minuten = k.getArbeitsbeginn() + (terminId-1) * k.getZeitslot();			//terminid-1 sagt was an zeit schon vergangen ist
		while (minuten - 60 >= 0) {
			minuten -= 60;
			stunde++;
		}

		if (minuten < 10) {
			return stunde + ":0" + minuten;
		}

		return stunde + ":" + minuten;
	}
}	