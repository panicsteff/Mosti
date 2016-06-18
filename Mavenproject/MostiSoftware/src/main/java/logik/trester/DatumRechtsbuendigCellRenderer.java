package logik.trester;

import java.awt.Component;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class DatumRechtsbuendigCellRenderer extends
		DefaultTableCellRenderer.UIResource {

	private Format format;

	public DatumRechtsbuendigCellRenderer() {
		setHorizontalAlignment(SwingConstants.RIGHT);
		format = new SimpleDateFormat("dd.MM.yyyy");
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isselected, boolean hasfocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isselected, hasfocus,
				row, column);

		Date date = (Date) value;
		String s = format.format(date);
		setText(s);
		return this;

	}
}
