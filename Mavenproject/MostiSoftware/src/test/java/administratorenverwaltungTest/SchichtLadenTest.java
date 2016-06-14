package administratorenverwaltungTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import logik.schichtverwaltung.Schicht;
import logik.schichtverwaltung.SchichtLogik;

import org.junit.Test;

public class SchichtLadenTest {

	@Test
	 public void testSchichtLaden(){
		
		 SchichtLogik s = new SchichtLogik();
		 ArrayList<Schicht> resultat = s.schichtLaden(1464300000000l);
		 
		 assertEquals(314, resultat.get(0).getSchichtId());
		 assertEquals(1464300000000l, resultat.get(0).getDatum().getTime());
		 assertEquals(540, resultat.get(0).getUhrzeit());
		 assertEquals((Integer) 1, resultat.get(0).getMitarbeiterId(0));
	 }

}
