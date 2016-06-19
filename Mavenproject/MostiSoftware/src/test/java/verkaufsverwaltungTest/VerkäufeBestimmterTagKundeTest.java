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

public class VerkäufeBestimmterTagKundeTest {

	@Test
	public void test() throws ParseException {
		Verkaufsverwaltung vv = new Verkaufsverwaltung();
		java.util.Date utilDate = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("26.05.2016");
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		
		KundeDB kundeDB = new KundeDB();
		Kunde kunde = null;
		try {
			kunde = kundeDB.kundenLaden().get(2); // Kunde Meier
			System.out.println(kunde.getKundenID());
		} catch (FileNotFoundException e) {
			System.out.println("Test aufgrund fehlgeschlagener Kunden-Verarbeitung nicht auswertbar");
			e.printStackTrace();
		}
		ArrayList<VerkaufspositionPlus> result = vv.ladeKundeinkaufTag2(kunde, date);
		
		for(VerkaufspositionPlus v : result){
			assertEquals(date, v.getDate());
			assertEquals(6, v.getKundenID());
		}
	}

}
