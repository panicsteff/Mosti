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
		
		ArrayList<Mitarbeiter> lösung = new ArrayList<Mitarbeiter>();
		Mitarbeiter m1 = new Mitarbeiter();
		m1.setBenutzername("helmi");
		m1.setMitarbeiterID(2);
		lösung.add(m1);
		Mitarbeiter m2 = new Mitarbeiter();
		m2.setBenutzername("admin");
		m2.setMitarbeiterID(4);
		lösung.add(m2);
		lösung.add(null);
		lösung.add(null);
		lösung.add(null);
		lösung.add(null);
		lösung.add(null);
		
		for(int i=0; i<2; i++){
			assertEquals(av.anmelden(eingaben.get(2*i), eingaben.get(2*i + 1)).getBenutzername(), lösung.get(i).getBenutzername());
			assertEquals(av.anmelden(eingaben.get(2*i), eingaben.get(2*i + 1)).getMitarbeiterID(), lösung.get(i).getMitarbeiterID());
		}
		
		for(int i=2; i<7; i++){
			assertEquals(av.anmelden(eingaben.get(2*i), eingaben.get(2*i + 1)), null);
		}
	}
	
	@Test 
	public void prüfePasswortTest(){
		Accountverwaltung av = new Accountverwaltung();
		ArrayList<String> passwörter = new ArrayList<String>();
		passwörter.add("ultrageheim");
		passwörter.add("geheim");
		passwörter.add("geheim");
		passwörter.add("ultrageheim");
		passwörter.add("geheim");
		passwörter.add("geheim");
		passwörter.add("ulrtägeheim");
		passwörter.add("geheim");
		passwörter.add("geheim");
		passwörter.add("ultrageheim");
		passwörter.add("geheim");
		passwörter.add("geHeim");
		passwörter.add("topsecret");
		passwörter.add("topsecret");
		passwörter.add("secret");
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(2);
		ids.add(4);
		ids.add(2);
		ids.add(2);
		ids.add(4);
		
		ArrayList<Boolean> lösung = new ArrayList<Boolean>();
		lösung.add(true);
		lösung.add(false);
		lösung.add(false);
		lösung.add(false);
		lösung.add(false);
		
		for(int i = 0; i<5; i++){
			assertEquals(av.prüfePasswort(passwörter.get(i*3), passwörter.get(i*3 + 1), passwörter.get(i*3 + 2), ids.get(i)), lösung.get(i));
		}
		
	}
}
