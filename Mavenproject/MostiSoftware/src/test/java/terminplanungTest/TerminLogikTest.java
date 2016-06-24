package terminplanungTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

import logik.administratorverwaltung.AdministratorLogik;
import logik.terminplanung.Intervall;
import logik.terminplanung.Termin;
import logik.terminplanung.TerminLogik;

import org.junit.Test;

import persistenz.TerminDB;

public class TerminLogikTest {

	@Test
	public void inVolleTerminListeTest(){
		ArrayList<Termin> l�sung =  new ArrayList<Termin>();
		for(int i=0; i<120; i++){
			Termin t = new Termin();
			t.setKundenId(0);
			t.setUhrzeit(540 + i*5);
			l�sung.add(t);
		}	
		ArrayList<Termin> eingabe = new ArrayList<Termin>();
		Termin t2 = new Termin();
		t2.setTerminId(5);
		t2.setAnzahlZeitslots(3);
		t2.setDatum(new Date(5l));
		t2.setInFlaschen(true);
		t2.setKundenId(10);
		t2.setMenge(3);
		t2.setUhrzeit(740);
		eingabe.add(t2);
		l�sung.set(40, t2);
		
		TerminLogik tl = new TerminLogik();
		assertEquals(tl.inVolleTerminListe(eingabe), l�sung);
		
	}
	
	@Test
	public void berechneAnzeigeSeiteTest(){
		int[] uhrzeit = {540, 1140};
		int[] l�sung = {1, 11};
		
		TerminLogik tl = new TerminLogik();
		for(int i=0; i<2; i++){
			assertEquals(tl.berechneAnzeigeSeite(uhrzeit[i]), l�sung[i]);
		}
		
	}
	
	@Test
	public void berechneL�ckenTest() {

		ArrayList<Intervall> l�sung  = new ArrayList<Intervall>();
		Intervall i1 = new Intervall();
		i1.setStart(540);
		i1.setEnde(570);
		l�sung.add(i1);
	
		ArrayList<Termin> eingabe = new ArrayList<Termin>();
		for(int i=0; i<6; i++){
			Termin t = new Termin();
			t.setUhrzeit(540 + i*5);
			eingabe.add(t);
		}
		
		for(int i=0; i<4; i++){
			Termin t = new Termin();
			t.setUhrzeit(590 + i*5);
			eingabe.add(t);
		}
		
		TerminLogik tl = new TerminLogik();
		assertEquals(tl.berechneL�cken(eingabe, 325), l�sung);
			
	}
	
	@Test
	public void kundenIDLadenTest(){
		TerminLogik tl = new TerminLogik();
		
		ArrayList<Integer> l�sung = new ArrayList<Integer>();
		l�sung.add(4);
		l�sung.add(8);
		
		assertEquals(tl.kundenIDLaden("k"), l�sung);
	}
	
	@Test
	public void kundenNamenLadenTest(){
		TerminLogik tl = new TerminLogik();
		assertEquals(tl.kundenNamenLaden(4), "Knechtelsdorfer, Maurice");
		assertEquals(tl.kundenNamenLaden(0), "");
	}


}
