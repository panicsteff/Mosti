package accountTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logik.account.Accountverwaltung;
import logik.mitarbeiterverwaltung.Mitarbeiter;

import org.junit.Test;

public class AccountverwaltungTest {

	@Test
	public void anmeldenTest() {
		Accountverwaltung av = new Accountverwaltung();
		ArrayList<String> eingaben = new ArrayList<String>();
		eingaben.add("helmi");
		eingaben.add("ultrageheim");
		eingaben.add("admin");
		eingaben.add("topsecret");
		eingaben.add("helmi");
		eingaben.add("topsecret");
		eingaben.add("helmi");
		eingaben.add("ulttrageheimm");
		eingaben.add("Helmi");
		eingaben.add("ultrageheim");
		eingaben.add("Admin");
		eingaben.add("topsecret");
		eingaben.add("admin");
		eingaben.add("Topsecret");
		
		ArrayList<Mitarbeiter> l�sung = new ArrayList<Mitarbeiter>();
		Mitarbeiter m1 = new Mitarbeiter();
		m1.setBenutzername("helmi");
		m1.setMitarbeiterID(2);
		l�sung.add(m1);
		Mitarbeiter m2 = new Mitarbeiter();
		m2.setBenutzername("admin");
		m2.setMitarbeiterID(4);
		l�sung.add(m2);
		l�sung.add(null);
		l�sung.add(null);
		l�sung.add(null);
		l�sung.add(null);
		l�sung.add(null);
		
		for(int i=0; i<2; i++){
			assertEquals(av.anmelden(eingaben.get(2*i), eingaben.get(2*i + 1)).getBenutzername(), l�sung.get(i).getBenutzername());
			assertEquals(av.anmelden(eingaben.get(2*i), eingaben.get(2*i + 1)).getMitarbeiterID(), l�sung.get(i).getMitarbeiterID());
		}
		
		for(int i=2; i<7; i++){
			assertEquals(av.anmelden(eingaben.get(2*i), eingaben.get(2*i + 1)), null);
		}
	}
	
	@Test 
	public void pr�fePasswortTest(){
		Accountverwaltung av = new Accountverwaltung();
		ArrayList<String> passw�rter = new ArrayList<String>();
		passw�rter.add("ultrageheim");
		passw�rter.add("geheim");
		passw�rter.add("geheim");
		passw�rter.add("ultrageheim");
		passw�rter.add("geheim");
		passw�rter.add("geheim");
		passw�rter.add("ulrt�geheim");
		passw�rter.add("geheim");
		passw�rter.add("geheim");
		passw�rter.add("ultrageheim");
		passw�rter.add("geheim");
		passw�rter.add("geHeim");
		passw�rter.add("topsecret");
		passw�rter.add("topsecret");
		passw�rter.add("secret");
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(2);
		ids.add(4);
		ids.add(2);
		ids.add(2);
		ids.add(4);
		
		ArrayList<Boolean> l�sung = new ArrayList<Boolean>();
		l�sung.add(true);
		l�sung.add(false);
		l�sung.add(false);
		l�sung.add(false);
		l�sung.add(false);
		
		for(int i = 0; i<5; i++){
			assertEquals(av.pr�fePasswort(passw�rter.get(i*3), passw�rter.get(i*3 + 1), passw�rter.get(i*3 + 2), ids.get(i)), l�sung.get(i));
		}
		
	}
}
