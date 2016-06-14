package administratorenverwaltungTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logik.schichtverwaltung.SchichtLogik;

import org.junit.Test;

public class MitarbeiterIdLadenTest {

	@Test
	public void testMitarbeiterIdLaden(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Integer> erwartet = new ArrayList<Integer>();
		erwartet.add(2);
		erwartet.add(3);
		ArrayList<Integer> resultat = schichtlogik.mitarbeiterIdLaden("S");
		
		assertEquals(resultat, erwartet);
		
		
	}
	
	

}
