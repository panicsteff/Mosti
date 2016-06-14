package mitarbeiterverwaltungTest;

import static org.junit.Assert.assertTrue;
import logik.mitarbeiterverwaltung.Mitarbeiter;

import org.junit.Test;

public class MitarbeiterTest {
	
	@Test
	public void testSetNachname(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setNachname("Siegl");
		assertTrue(mitarbeiter.getNachname()=="Siegl");	
	}
	
	@Test
	public void testSetVorname(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setVorname("Kathi");
		assertTrue(mitarbeiter.getVorname()=="Kathi");	
	}
	
	@Test
	public void testSetStrasse(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setStrasse("Zwerghausen");
		assertTrue(mitarbeiter.getStrasse()=="Zwerghausen");	
	}
	
	@Test
	public void testSetTelefonnummer(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setTelefonnummer("Siegl");
		assertTrue(mitarbeiter.getTelefonnummer()=="Siegl");	
	}
	
	@Test
	public void testSetBenutzername(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setBenutzername("SiKa");
		assertTrue(mitarbeiter.getBenutzername()=="SiKa");	
	}
	
	@Test
	public void testSetHausnummer(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setHausnummer("156");
		assertTrue(mitarbeiter.getHausnummer()=="156");	
	}
	
	@Test
	public void testSetPLZ(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setPlz("25832");
		assertTrue(mitarbeiter.getPlz() == "25832");
	}
	
	@Test
	public void testSetStadt(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setStadt("Burgfeld");
		assertTrue(mitarbeiter.getStadt() == "Burgfeld");
	}
	
	@Test
	public void testSetAccount(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setAccount("Burgfeld");
		assertTrue(mitarbeiter.getAccount() == "Burgfeld");
	}
	
	@Test
	public void testSetMitarbeiterID(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setMitarbeiterID("1");
		assertTrue(mitarbeiter.getMitarbeiterID() == "1");
	}
	
	@Test
	public void testGetNachname(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setNachname("Siegl");
		assertTrue(mitarbeiter.getNachname()=="Siegl");	
	}
	
	@Test
	public void testGetVorname(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setVorname("Kathi");
		assertTrue(mitarbeiter.getVorname()=="Kathi");	
	}
	
	@Test
	public void testGetStrasse(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setStrasse("Zwerghausen");
		assertTrue(mitarbeiter.getStrasse()=="Zwerghausen");	
	}
	
	@Test
	public void testGetTelefonnummer(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setTelefonnummer("Siegl");
		assertTrue(mitarbeiter.getTelefonnummer()=="Siegl");	
	}
	
	@Test
	public void testGetBenutzername(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setBenutzername("SiKa");
		assertTrue(mitarbeiter.getBenutzername()=="SiKa");	
	}
	
	@Test
	public void testGetHausnummer(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setHausnummer("156");
		assertTrue(mitarbeiter.getHausnummer()=="156");	
	}
	
	@Test
	public void testGetPLZ(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setPlz("25832");
		assertTrue(mitarbeiter.getPlz() == "25832");
	}
	
	@Test
	public void testGetStadt(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setStadt("Burgfeld");
		assertTrue(mitarbeiter.getStadt() == "Burgfeld");
	}
	
	@Test
	public void testGetAccount(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setAccount("Burgfeld");
		assertTrue(mitarbeiter.getAccount() == "Burgfeld");
	}
	
	@Test
	public void testGetMitarbeiterID(){
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setMitarbeiterID("1");
		assertTrue(mitarbeiter.getMitarbeiterID() == "1");

}
