package adminestratorenverwaltungTest;


import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import administratorverwaltung.Schicht;
import administratorverwaltung.SchichtLogik;

public class schichtTest {
	
	 @Test
	 public void testSchichtLaden(){
		
		 SchichtLogik s = new SchichtLogik();
		 ArrayList<Schicht> result = s.schichtLaden(1464300000000l);
		 Schicht schicht = new Schicht();
		 schicht.setSchichtId(314);
		 schicht.setDatum(new Date(1464300000000l));
		 schicht.setUhrzeit(540);
		 schicht.addMitarbeiterId(1);
		 ArrayList<Schicht> erwartet = new ArrayList<Schicht>();
		 erwartet.add(schicht);
		 assertEquals(result, erwartet);
		 
	 }

}
