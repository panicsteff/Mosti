package adminestratorenverwaltungTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import administratorverwaltung.Schicht;
import administratorverwaltung.SchichtLogik;

public class SchichtTest2 {

	@Test
	public void testSchichtSortieren() {
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		ArrayList<Schicht> erwartet = new ArrayList<Schicht>();
		for(int i=0; i<4; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.setSchichtId(5);
			s.setUhrzeit(540 + (i%2)*300);
			s.addMitarbeiterId(5);
			liste.add(s);
		}
		
		for(int i=0; i<2; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.setSchichtId(5);
			s.setUhrzeit(540 + (i%2)*300);
			s.addMitarbeiterId(5);
			erwartet.add(s);
			erwartet.add(s);
		}
		
		ArrayList<Schicht> resultat = schichtlogik.schichtenSortieren(liste);
		
		assertEquals(resultat, erwartet);
	}
	
	public void testSchichtenMergen(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		ArrayList<Schicht> erwartet = new ArrayList<Schicht>();
		for(int i=0; i<4; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.setSchichtId(5);
			s.setUhrzeit(540 + 300*(i/2));
			s.addMitarbeiterId(5 + i);
			liste.add(s);
		}
		
		for(int i=0; i<2; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.setSchichtId(5);
			s.setUhrzeit(540 + (i/2)*300);
			s.addMitarbeiterId(5 + i/2);
			s.addMitarbeiterId(6 + i/2);
			erwartet.add(s);
		}
		
		ArrayList<Schicht> resultat = schichtlogik.schichtenMergen(liste);
		
		assertEquals(resultat, erwartet);
	}
	
	public void testMitarbeiterIdLaden(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Integer> erwartet = new ArrayList<Integer>();
		erwartet.add(2);
		erwartet.add(3);
		ArrayList<Integer> resultat = schichtlogik.mitarbeiterIdLaden("S");
		
		assertEquals(resultat, erwartet);
		
		
	}
	
	public void testMitarbeiterNamenLaden(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		String resultat = schichtlogik.mitarbeiternameLaden(1);
		
		assertEquals(resultat, "Kunc, Stöff-Stöff");
	}
	
	public void  testBerechneUhrzeit(){
		SchichtLogik schichtlogik = new SchichtLogik();
		int resultat = schichtlogik.berechneUhrzeit(2);
		
		assertEquals(resultat, 840);
	}

}
