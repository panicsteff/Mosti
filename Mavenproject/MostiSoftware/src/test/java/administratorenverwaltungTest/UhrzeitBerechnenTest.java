package administratorenverwaltungTest;

import static org.junit.Assert.assertEquals;
import logik.schichtverwaltung.SchichtLogik;

import org.junit.Test;

public class UhrzeitBerechnenTest {

	@Test
	public void  testBerechneUhrzeit(){
		SchichtLogik schichtlogik = new SchichtLogik();
		int resultat = schichtlogik.berechneUhrzeit(1);
		
		assertEquals(840, resultat);
	}

}
