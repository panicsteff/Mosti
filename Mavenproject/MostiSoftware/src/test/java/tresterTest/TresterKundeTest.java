package tresterTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import logik.trester.Tresterabrechnung;
import logik.trester.Tresterverwaltung;
import org.junit.Test;

import persistenz.KundeDB;

public class TresterKundeTest {

	@Test
	public void tresterBestimmterKundeTest() throws FileNotFoundException {
		Tresterverwaltung tv = new Tresterverwaltung();
		KundeDB kundeDB = new KundeDB();
		Kunde kunde = null;
		kunde = kundeDB.kundenLaden().get(6); // Kunden-ID 6
		System.out.println(kunde.getKundenID());
		
		ArrayList<Tresterabrechnung> result = tv.ladeAlleTresterEinkäufeVonKunde(kunde);
		for(Tresterabrechnung ta : result){
			assertEquals(6, ta.getKundenID());
		}
	}

}
