package lagerverwaltung;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.Format;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class PreisCellRenderer extends DefaultTableCellRenderer.UIResource {

	private Format format;

	public PreisCellRenderer() {
		format = new DecimalFormat("###0.00 €");
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isselected, boolean hasfocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isselected, hasfocus,
				row, column);

		double gehalt = (Double) value;
		String s = format.format(gehalt);
		setText(s);
		return this;
	}

}
