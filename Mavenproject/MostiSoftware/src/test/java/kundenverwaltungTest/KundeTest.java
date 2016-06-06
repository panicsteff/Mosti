package kundenverwaltungTest;

import kundenverwaltung.Kunde;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;


public class KundeTest {

	@Test
	public void testSetNachname(){
	Kunde kunde = new Kunde();
	kunde.setNachname("Kunc");
	assertTrue(kunde.getNachname() == "Kunc");
	}
	
	@Test
	public void testSetVorname(){
		Kunde kunde = new Kunde();
		kunde.setVorname("Steffi");
		assertTrue(kunde.getVorname() == "Steffi");
		}
	
	@Test
	public void testSetStrasse(){
		Kunde kunde = new Kunde();
		kunde.setStrasse("irgendwo 55");
		assertTrue(kunde.getStrasse() == "irgendwo 55");
		}
	
	@Test
	public void testSetPlz(){
		Kunde kunde = new Kunde();
		kunde.setPlz("25789");
		assertTrue(kunde.getPlz() == "25789");
		}
	
	@Test
	public void testSetWohnort(){
		Kunde kunde = new Kunde();
		kunde.setWohnort("Regensburg");
		assertTrue(kunde.getWohnort() == "Regensburg");
		}
	
	@Test
	public void testSetTel(){
		Kunde kunde = new Kunde();
		kunde.setTel("123456");
		assertTrue(kunde.getTel() == "123456");
		}
	
	@Test
	public void testSetKundenID(){
		Kunde kunde = new Kunde();
		kunde.setKundenID(5);
		assertTrue(kunde.getKundenID() == 5);
		}
	
	@Test
	public void testGetNachname(){
		Kunde kunde = new Kunde();
		kunde.setNachname("Kunc");
		assertTrue(kunde.getNachname() == "Kunc");
		}
	
	@Test
	public void testGetVorname(){
		Kunde kunde = new Kunde();
		kunde.setVorname("Steffi");
		assertTrue(kunde.getVorname() == "Steffi");
		}
	
	@Test
	public void testGetStrasse(){
		Kunde kunde = new Kunde();
		kunde.setStrasse("irgendwo 55");
		assertTrue(kunde.getStrasse() == "irgendwo 55");
		}
	
	@Test
	public void testGetPlz(){
		Kunde kunde = new Kunde();
		kunde.setPlz("25789");
		assertTrue(kunde.getPlz() == "25789");
		}
	
	@Test
	public void testGetWohnort(){
		Kunde kunde = new Kunde();
		kunde.setWohnort("Regensburg");
		assertTrue(kunde.getWohnort() == "Regensburg");
		}
	
	@Test
	public void testGetTel(){
		Kunde kunde = new Kunde();
		kunde.setTel("123456");
		assertTrue(kunde.getTel() == "123456");
		}
	
	@Test
	public void testGetKundenID(){
		Kunde kunde = new Kunde();
		kunde.setKundenID(5);
		assertTrue(kunde.getKundenID() == 5);
		}
}
