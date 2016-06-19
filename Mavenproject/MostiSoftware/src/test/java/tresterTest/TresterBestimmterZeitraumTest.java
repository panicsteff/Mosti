package tresterTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import logik.trester.Tresterabrechnung;
import logik.trester.Tresterverwaltung;
import org.junit.Test;

public class TresterBestimmterZeitraumTest {

	@Test
	public void tresterBestimmterZeitraumTest() throws ParseException {
		Tresterverwaltung tv = new Tresterverwaltung();
		java.util.Date utilDate1 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("01.05.2016");
		java.sql.Date date1 = new java.sql.Date(utilDate1.getTime());
		java.util.Date utilDate2 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("18.06.2016");
		java.sql.Date date2 = new java.sql.Date(utilDate2.getTime());
		
		ArrayList<Tresterabrechnung> result = tv.ladeAlleTresterverkäufeZeitraum(date1, date2);
		
		for(Tresterabrechnung ta : result){
			assertTrue(date1.getTime() <= ta.getDate().getTime());
			assertTrue(ta.getDate().getTime() <= date2.getTime());
		}
	}

}
