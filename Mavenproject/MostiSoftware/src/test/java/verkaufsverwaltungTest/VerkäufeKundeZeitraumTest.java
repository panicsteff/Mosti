package verkaufsverwaltungTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import logik.verkaufsverwaltung.VerkaufspositionPlus;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

import org.junit.Test;

import persistenz.KundeDB;

public class VerkäufeKundeZeitraumTest {

	@Test
	public void verkäufeKundeZeitraumTest() throws FileNotFoundException, ParseException {
		Verkaufsverwaltung vv = new Verkaufsverwaltung();
		java.util.Date utilDate1 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("01.04.2016");
		java.sql.Date date1 = new java.sql.Date(utilDate1.getTime());
		java.util.Date utilDate2 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("20.06.2016");
		java.sql.Date date2 = new java.sql.Date(utilDate2.getTime());
		
		KundeDB kundeDB = new KundeDB();
		Kunde kunde = null;
		kunde = kundeDB.kundenLaden().get(2); // Kunde Meier, ID: 6
		System.out.println(kunde.getKundenID());
		
		ArrayList<VerkaufspositionPlus> result = vv.ladeKundeneinkaufZeitraum2(kunde, date1, date2);
		
		for(VerkaufspositionPlus v : result){
			assertEquals(6, v.getKundenID());
			assertTrue(date1.getTime() <= v.getDate().getTime());
			assertTrue(v.getDate().getTime() <= date2.getTime());
		}
	}

}
