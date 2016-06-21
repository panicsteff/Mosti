package schichtverwaltungTest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import logik.schichtverwaltung.Schicht;
import logik.schichtverwaltung.SchichtLogik;

import org.junit.Test;

public class SchichtLogikTest {

	
	@Test
	public void testMitarbeiterIdLaden(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Integer> erwartet = new ArrayList<Integer>();
		erwartet.add(1);
		erwartet.add(9);
		erwartet.add(17);
		ArrayList<Integer> resultat = schichtlogik.mitarbeiterIdLaden("K");
		
		assertEquals(resultat, erwartet);
	}
	
	@Test
	public void testMitarbeiterNamenLaden(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		assertEquals(schichtlogik.mitarbeiternameLaden(1), "Kunc, Stöff-Stöff");
		assertEquals(schichtlogik.mitarbeiternameLaden(3), "");
		
	}
	
	@Test
	public void testSchichtenMergen(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		for(int i=0; i<4; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.addSchichtId(5);
			s.setUhrzeit(540 + 300*(i/2));
			s.addMitarbeiterId(5 + i);
			liste.add(s);
		}
		
		
		ArrayList<Schicht> resultat = schichtlogik.schichtenMergen(liste);
		
		assertEquals((Integer) 5, resultat.get(0).getMitarbeiterId(0));
		assertEquals((Integer) 6, resultat.get(0).getMitarbeiterId(1));
		assertEquals((Integer) 7, resultat.get(1).getMitarbeiterId(0));
		assertEquals((Integer) 8, resultat.get(1).getMitarbeiterId(1));
	}
	
	@Test
	 public void testSchichtLaden(){
		
		 SchichtLogik s = new SchichtLogik();
		 ArrayList<Schicht> resultat = s.schichtLaden(1464300000000l);
		 
		 assertEquals((Integer) 341, resultat.get(0).getSchichtId(0));
		 assertEquals(1464300000000l, resultat.get(0).getDatum().getTime());
		 assertEquals(540, resultat.get(0).getUhrzeit());
		 assertEquals((Integer) 1, resultat.get(0).getMitarbeiterId(0));
	 }
	
	@Test
	public void testSchichtSortieren() {
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		for(int i=0; i<4; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.addSchichtId(5);
			s.setUhrzeit(540 + (i%2)*300);
			s.addMitarbeiterId(5);
			liste.add(s);
		}
	
		
		ArrayList<Schicht> resultat = schichtlogik.schichtenSortieren(liste);
		
		assertEquals(540, resultat.get(0).getUhrzeit());
		assertEquals(540, resultat.get(1).getUhrzeit());
		assertEquals(840, resultat.get(2).getUhrzeit());
		assertEquals(840, resultat.get(3).getUhrzeit());
	}

	@Test
	public void  testBerechneUhrzeit(){
		SchichtLogik schichtlogik = new SchichtLogik();
		int resultat = schichtlogik.berechneUhrzeit(1);
		
		assertEquals(840, resultat);
	}
		
}
