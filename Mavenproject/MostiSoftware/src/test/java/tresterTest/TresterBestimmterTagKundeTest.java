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

public class TresterBestimmterTagKundeTest {

	@Test
	public void tresterTagKundeTest() throws ParseException {
		Tresterverwaltung tv = new Tresterverwaltung();
		java.util.Date utilDate = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("26.05.2016");
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		
		KundeDB kundeDB = new KundeDB();
		Kunde kunde = null;
		try {
			kunde = kundeDB.kundenLaden().get(2); // Kunde Meier, ID: 6
			System.out.println(kunde.getKundenID());
		} catch (FileNotFoundException e) {
			System.out.println("Test aufgrund fehlgeschlagener Kunden-Verarbeitung nicht auswertbar");
			e.printStackTrace();
		}
		ArrayList<Tresterabrechnung> result = tv.ladeKundentrestereinkaufTag(kunde, date);
		
		for(Tresterabrechnung ta : result){
			assertEquals(date, ta.getDate());
			assertEquals(6, ta.getKundenID());
		}
	}

}
