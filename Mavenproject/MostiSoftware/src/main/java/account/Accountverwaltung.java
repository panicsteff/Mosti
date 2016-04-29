package account;

import mitarbeiterverwaltung.Mitarbeiter;
import mitarbeiterverwaltung.Mitarbeiterliste;

public class Accountverwaltung {

	public static boolean benutzernamenSuchen(String benutzernameEingabe, String passwortEingabe) {

		Mitarbeiterliste liste = new Mitarbeiterliste();
		Mitarbeiter[] mitarbeiterliste = liste.getListe();
		for (int i = 0; i < 5; i++) {
			Mitarbeiter m = mitarbeiterliste[i];
			if (m.getBenutzername().equals(benutzernameEingabe)) {
				M_Account account = m.getAccount();
				account.anmelden(passwortEingabe);
				return true;
				
			}
		}
		return false;
		
	}
}
