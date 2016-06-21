package schichtverwaltungTest;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import logik.schichtverwaltung.Schicht;
import logik.schichtverwaltung.SchichtLogik;

import org.junit.Test;

public class SchichtTest {
	
	@Test
	public void getMitarbeiterIdTest(){
		Schicht s = new Schicht();
		s.addMitarbeiterId(4);
		s.addMitarbeiterId(3);
		
		ArrayList<Integer> eingabe = new ArrayList<Integer>();
		eingabe.add(1);
		eingabe.add(2);
		eingabe.add(3);
		eingabe.add(-1);
		
		ArrayList<Integer> ergebnis = new ArrayList<Integer>();
		ergebnis.add(3);
		ergebnis.add(4);
		ergebnis.add(0);
		ergebnis.add(0);
		
		for(int i=0; i<4; i++){
			assertEquals(s.getMitarbeiterId(eingabe.get(i)), ergebnis.get(i));
		}
		
	}

	@Test
	public void addMitarbeiterIdTest(){
		Schicht s = new Schicht();
		s.addMitarbeiterId(5);
		
		assertEquals(s.getMitarbeiterId(0), (Integer)5);
	}
	
	@Test
	void getAnzahlMitarbeiterTest(){
		Schicht s = new Schicht();
		s.addMitarbeiterId(5);
		s.addMitarbeiterId(5);
		s.addMitarbeiterId(5);
		s.addMitarbeiterId(5);
		
		assertEquals(s.getAnzahlMitarbeiter(), 4);
	}

	@Test
	public void getSchichtIdTest() {
		Schicht s = new Schicht();
		s.addSchichtId(5);
		assertEquals(s.getSchichtId(0), (Integer) 5);
		
	}

	@Test
	public void getUhrzeitTest() {
		Schicht s = new Schicht();
		s.setUhrzeit(540);
		assertEquals(s.getUhrzeit(), 540);
	}


}
