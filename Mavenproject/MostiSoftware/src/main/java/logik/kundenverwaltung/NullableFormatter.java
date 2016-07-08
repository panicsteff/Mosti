package logik.kundenverwaltung;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;

public class NullableFormatter extends DefaultFormatter {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField.AbstractFormatter base;

	public NullableFormatter(JFormattedTextField.AbstractFormatter base) {
		this.base = base;
	}

	public void install(JFormattedTextField ftf) {
		base.install(ftf);
	}

	public void uninstall() {
		base.uninstall();
	}

	public Object stringToValue(String text) throws ParseException {
		if (text == null || text.trim().length() == 0) {
			return null;
		}
		return base.stringToValue(text);
	}

	public String valueToString(Object value) throws ParseException {
		return base.valueToString(value);
	}
}
