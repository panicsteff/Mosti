package mitarbeiterverwaltung;

import account.M_Account;

public class Mitarbeiter {

	private String nachname;
	private String vorname;
	private String strasse;
	private int hausnummer;
	private int plz;
	private String stadt;
	private String telefonnummer;
	private String benutzername;
	private M_Account account;

	public Mitarbeiter(String nachname, String vorname, String strasse,
			int hausnummer, int plz, String stadt, String telefonnummer,
			String benutzername) {
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setPlz(plz);
		setStadt(stadt);
		setTelefonnummer(telefonnummer);
		setBenutzername(benutzername);
		setAccount(new M_Account(benutzername, "test"));
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String adresse) {
		this.strasse = adresse;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonummer) {
		this.telefonnummer = telefonummer;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public int getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public M_Account getAccount() {
		return account;
	}

	public void setAccount(M_Account account) {
		this.account = account;
	}
}
