package mitarbeiterverwaltungTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import logik.kundenverwaltung.KundeTableModel;

import org.junit.Test;

public class KundeTableModelTest {
 
	@Test
	public void testGetKunden(){
		ArrayList<Kunde> kunde = new ArrayList();
		Kunde k1 = new Kunde("Irmi", "Sax", "agea", "ea", "erh", "aehae", 0);
		Kunde k2 = new Kunde("Kathi", "Siegl", "doajg", "esoijta", "ewahtih", "ehah", 0);
		kunde.add(k1);
		kunde.add(k2);
		KundeTableModel ktm = new KundeTableModel();
		ktm.setKunden(kunde);
		assertTrue(ktm.getKunden() == kunde);
	
	}
}
