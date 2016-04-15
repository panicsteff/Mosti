package account;

import javax.swing.JOptionPane;

public class M_Account {

	private String benutzername;
	private String passwort;

	public M_Account(String benutzername, String passwort) {
		this.setBenutzername(benutzername);
		this.passwort = passwort;
	}

	public void benutzerSuchen(String benutzername){
		
	}
	public void anmelden(String passwort) {
		if (this.passwort.equals(passwort)) {
			JOptionPane.showMessageDialog(null, "viel spass mit Mosti");
		} else {
			JOptionPane.showMessageDialog(null, "Falsches Passwort");
		}
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
}
