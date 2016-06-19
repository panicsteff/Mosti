package tresterTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import logik.trester.Tresterabrechnung;
import logik.trester.Tresterverwaltung;
import org.junit.Test;

public class TresterBestimmterTagTest {

	@Test
	public void tresterVonBestimmtemTagTest() throws ParseException {
		Tresterverwaltung tv = new Tresterverwaltung();
		java.util.Date utilDate = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("18.06.2016");
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		ArrayList<Tresterabrechnung> result = tv.ladeTagesTresterVerkäufe(date);
		
		for(Tresterabrechnung ta : result){
			assertEquals(date, ta.getDate());
		}
	}
	

}
