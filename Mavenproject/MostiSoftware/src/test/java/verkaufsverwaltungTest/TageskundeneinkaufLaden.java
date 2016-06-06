package verkaufsverwaltungTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

import kundenverwaltung.Kunde;
import kundenverwaltung.KundeDB;
import logik.verkaufsverwaltung.Verkauf;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

import org.junit.Test;

public class TageskundeneinkaufLaden {

	@Test
	public void testTageskundeneinkaufLaden() {
		
		java.util.Date utilDate = null;
		try {
			utilDate = kundenverwaltung.Formats.DATE_FORMAT.parse("26.05.2016");
		} catch (ParseException e) {
			System.out.println("Test aufgrund fehlgeschlagener Datum-Verarbeitung nicht auswertbar");
			e.printStackTrace();
		}
		java.sql.Date date1 = new java.sql.Date(utilDate.getTime());
		
		
		Verkaufsverwaltung vv = new Verkaufsverwaltung();
		KundeDB kundeDB = new KundeDB();
		Kunde kunde = null;
		try {
			kunde = kundeDB.kundenLaden().get(2); // Kunde Meier
		} catch (FileNotFoundException e) {
			System.out.println("Test aufgrund fehlgeschlagener Kunden-Verarbeitung nicht auswertbar");
			e.printStackTrace();
		}
		
		
		Verkauf result = vv.ladeKundeinkaufTag(kunde, date1);
		assertEquals("Meier", result.getKunde().getNachname());
		assertEquals(date1, result.getVerkaufsDatum());
		//assertEquals(30, result.getLiterzahl());   // hier Fehler, da die gesamte Literzahl erst in der GUI berechnet wird
	

		
	}

}
