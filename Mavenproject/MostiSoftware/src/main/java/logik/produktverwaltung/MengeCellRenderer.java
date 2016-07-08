package logik.produktverwaltung;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.Format;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MengeCellRenderer extends DefaultTableCellRenderer.UIResource {

	private static final long serialVersionUID = 1L;
	private Format format;

	public MengeCellRenderer() {
		format = new DecimalFormat("###0");
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isselected, boolean hasfocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isselected, hasfocus,
				row, column);

		Integer menge = (Integer) value;
		String s = format.format(menge);
		setText(s);
		return this;
	}

}
