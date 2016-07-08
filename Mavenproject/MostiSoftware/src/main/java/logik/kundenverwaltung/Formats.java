package logik.kundenverwaltung;

import java.text.SimpleDateFormat;

public interface Formats {
	final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"dd.MM.yyyy");
	final static SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
}
