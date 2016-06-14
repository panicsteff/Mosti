package gui.kundenverwaltung;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class KundeListCellRenderer extends DefaultListCellRenderer.UIResource {

	public KundeListCellRenderer() {
		setHorizontalAlignment(SwingConstants.LEFT);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		return this;
	}

}
