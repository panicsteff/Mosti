package account;

import javax.swing.JOptionPane;

public class M_Account {

	private String benutzername;
	private String passwort;

	public M_Account(String benutzername, String passwort) {
		this.setBenutzername(benutzername);
		this.passwort = passwort;
	}

	void anmelden(String passwort) {
		if (this.passwort.equals(passwort)) {
			
			//new M_Startseite();
		} else {
			JOptionPane.showMessageDialog(null, "Falsches Passwort");
		}
	}

	String getBenutzername() {
		return benutzername;
	}

	void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
}