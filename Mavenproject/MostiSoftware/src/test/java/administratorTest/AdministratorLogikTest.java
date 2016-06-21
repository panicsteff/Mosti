package administratorTest;

import static org.junit.Assert.*;
import logik.administratorverwaltung.AdministratorLogik;

import org.junit.Test;

public class AdministratorLogikTest {

	@Test
	public void getZeitslotTest() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getZeitslot(), 5);
	}
	
	@Test
	public void getArbeitsbeginn() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getArbeitsbeginn(), 540);
	}
	
	@Test
	public void getArbeitsende() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getArbeitsende(), 1140);
	}

	@Test
	public void getAufteilung() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getAufteilung(), 10);
	}

	@Test
	public void getZeilenanzahlProSeite() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getZeilenanzahlProSeite(), 12);
	}

	@Test
	public void getMitarbeiterProSchicht() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getMitarbeiterProSchicht(), 3);
	}

	@Test
	public void getSchichtenProTag() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getSchichtenProTag(), 2);
	}

	@Test
	public void getSchichtDauer() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getSchichtDauer(), 300);
	}

	@Test
	public void getPressdauer() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getPressdauer(), 5.0, 0.0);
	}

	@Test
	public void getAbfülldauer() {
		new AdministratorLogik();
		assertEquals(AdministratorLogik.getAbfülldauer(), 10.0, 0.0);
	}

}
