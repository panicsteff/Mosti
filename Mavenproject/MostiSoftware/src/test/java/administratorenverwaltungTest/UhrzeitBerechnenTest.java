package administratorenverwaltungTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import administratorverwaltung.SchichtLogik;

public class UhrzeitBerechnenTest {

	@Test
	public void  testBerechneUhrzeit(){
		SchichtLogik schichtlogik = new SchichtLogik();
		int resultat = schichtlogik.berechneUhrzeit(1);
		
		assertEquals(840, resultat);
	}

}
