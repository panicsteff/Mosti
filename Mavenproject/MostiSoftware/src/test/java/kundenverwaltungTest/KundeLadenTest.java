package kundenverwaltungTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import kundenverwaltung.Kunde;
import kundenverwaltung.KundeDB;

import org.junit.Test;

public class KundeLadenTest {

	@Test
	public void kundeLadenTest() {
		
		KundeDB kundeDB = new KundeDB();
		Kunde result = null;
		try {
			result = kundeDB.kundenLaden().get(2); // Kunde Meier
		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim Laden aus der Datenbank");
			e.printStackTrace();
		}
		
		assertEquals("Marc", result.getVorname());
		assertEquals("Meier", result.getNachname());
		assertEquals("Müllntalleitn 4", result.getStrasse());
		assertEquals("84490", result.getPlz());
		assertEquals("Auf der Heide", result.getWohnort());
		assertEquals("035721844", result.getTel());
		
	}
		

}
