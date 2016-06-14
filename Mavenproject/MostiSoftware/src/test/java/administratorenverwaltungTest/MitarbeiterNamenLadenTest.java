package administratorenverwaltungTest;

import static org.junit.Assert.*;
import logik.schichtverwaltung.SchichtLogik;

import org.junit.Test;

public class MitarbeiterNamenLadenTest {

	@Test
	public void testMitarbeiterNamenLaden(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		String resultat = schichtlogik.mitarbeiternameLaden(1);
		
		assertEquals(resultat, "Kunc, Stöff-Stöff");
	}
	
	

}
