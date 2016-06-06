package administratorenverwaltungTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import administratorverwaltung.Schicht;
import administratorverwaltung.SchichtLogik;

public class SchichtenMergenTest {

	@Test
	public void testSchichtenMergen(){
		
		SchichtLogik schichtlogik = new SchichtLogik();
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		for(int i=0; i<4; i++){
			Schicht s = new Schicht();
			s.setDatum(new Date(123456l));
			s.setSchichtId(5);
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
}
