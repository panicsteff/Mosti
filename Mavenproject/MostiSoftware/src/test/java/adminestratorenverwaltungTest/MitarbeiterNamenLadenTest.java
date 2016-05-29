package adminestratorenverwaltungTest;

import static org.junit.Assert.*;

import org.junit.Test;

import administratorverwaltung.SchichtLogik;

public class MitarbeiterNamenLadenTest {

	@Test
	public void testMitarbeiterNamenLaden(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		String resultat = schichtlogik.mitarbeiternameLaden(1);
		
		assertEquals(resultat, "Kunc, Stöff-Stöff");
	}
	
	

}
