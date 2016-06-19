package tresterTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import logik.trester.Tresterabrechnung;
import logik.trester.Tresterverwaltung;
import org.junit.Test;

import persistenz.KundeDB;

public class TresterKundeZeitraumTest {

	@Test
	public void tresterKundeZeitraumTest() throws FileNotFoundException, ParseException {
		Tresterverwaltung tv = new Tresterverwaltung();
		java.util.Date utilDate1 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("01.04.2016");
		java.sql.Date date1 = new java.sql.Date(utilDate1.getTime());
		java.util.Date utilDate2 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("20.06.2016");
		java.sql.Date date2 = new java.sql.Date(utilDate2.getTime());
		
		KundeDB kundeDB = new KundeDB();
		Kunde kunde = null;
		kunde = kundeDB.kundenLaden().get(2); // Kunde Meier, ID: 6
		System.out.println(kunde.getKundenID());
		
		ArrayList<Tresterabrechnung> result = tv.ladeKundenTrestereinkaufZeitraum(kunde, date1, date2);
		
		for(Tresterabrechnung ta : result){
			assertEquals(6, ta.getKundenID());
			assertTrue(date1.getTime() <= ta.getDate().getTime());
			assertTrue(ta.getDate().getTime() <= date2.getTime());
		}
	}

}
