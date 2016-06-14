package administratorenverwaltungTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import logik.schichtverwaltung.Schicht;
import logik.schichtverwaltung.SchichtLogik;

import org.junit.Test;

public class SchichtSortierenTest {

	@Test
	public void testSchichtSortieren() {
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		for(int i=0; i<4; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.setSchichtId(5);
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

}
