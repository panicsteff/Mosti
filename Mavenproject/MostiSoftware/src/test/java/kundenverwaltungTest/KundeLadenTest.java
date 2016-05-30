package kundenverwaltungTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import kundenverwaltung.Kunde;
import kundenverwaltung.KundeDB;

import org.junit.Test;

public class KundeLadenTest {

	@Test
	public void test() {
		
		KundeDB kundeDB = new KundeDB();
		Kunde result = null;
		try {
			result = kundeDB.kundenLaden().get(2); // Kunde Meier
		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim Laden aus der Datenbank");
			e.printStackTrace();
		}
		
		assertEquals("Meier", result.getNachname());
		assertEquals("Marc", result.getVorname());
		
	}
		

}
