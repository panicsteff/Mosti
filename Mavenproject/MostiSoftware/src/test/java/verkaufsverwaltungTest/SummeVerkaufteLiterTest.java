package verkaufsverwaltungTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

import org.junit.Test;

public class SummeVerkaufteLiterTest {

	@Test
	public void summeVerkaufteLiterTest() throws ParseException {
		Verkaufsverwaltung vv = new Verkaufsverwaltung();
		java.util.Date utilDate = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("19.06.2016");
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		
		int result = vv.getLitersummeGesamterTag(date);
		assertEquals(100, result);
		
	}

}
