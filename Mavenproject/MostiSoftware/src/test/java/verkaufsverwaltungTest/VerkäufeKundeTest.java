package verkaufsverwaltungTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import logik.verkaufsverwaltung.VerkaufspositionPlus;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

import org.junit.Test;

import persistenz.KundeDB;

public class VerkäufeKundeTest {

	@Test
	public void verkäufeBestimmterKundeTest() throws FileNotFoundException {
		Verkaufsverwaltung vv = new Verkaufsverwaltung();
		KundeDB kundeDB = new KundeDB();
		Kunde kunde = null;
		kunde = kundeDB.kundenLaden().get(1); // Kunden-ID 5
		System.out.println(kunde.getKundenID());
		
		ArrayList<VerkaufspositionPlus> result = vv.ladeAlleEinkäufeVonKunde2(kunde);
		for(VerkaufspositionPlus v : result){
			assertEquals((long)5, (long)v.getKundenID());
		}
	}

}
