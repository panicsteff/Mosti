package mitarbeiterverwaltung;

import account.M_Account;

public class Mitarbeiter {

	private String nachname;
	private String vorname;
	private String strasse;
	private String hausnummer;
	private String plz;
	private String stadt;
	private String telefonnummer;
	private int mitarbeiterID;
	private String benutzername;
	private M_Account account;

	public Mitarbeiter(String nachname, String vorname, String strasse,
			String hausnummer, String plz, String stadt, String telefonnummer, int mitarbeiterId, String benutzername)
	{
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setPlz(plz);
		setStadt(stadt);
		setTelefonnummer(telefonnummer);
		setMitarbeiterID(mitarbeiterId);
		setBenutzername(benutzername);
		setAccount(new M_Account(benutzername, "test"));
	}
	
	public Mitarbeiter(){
		
	}

	String getNachname() {
		return nachname;
	}

	void setNachname(String nachname) {
		this.nachname = nachname;
	}

	String getVorname() {
		return vorname;
	}

	void setVorname(String vorname) {
		this.vorname = vorname;
	}

	String getStrasse() {
		return strasse;
	}

	void setStrasse(String adresse) {
		this.strasse = adresse;
	}

	String getTelefonnummer() {
		return telefonnummer;
	}

	void setTelefonnummer(String telefonummer) {
		this.telefonnummer = telefonummer;
	}

	String getBenutzername() {
		return benutzername;
	}

	void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	String getHausnummer() {
		return hausnummer;
	}

	void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	String getPlz() {
		return plz;
	}

	void setPlz(String plz) {
		this.plz = plz;
	}

	String getStadt() {
		return stadt;
	}

	void setStadt(String stadt) {
		this.stadt = stadt;
	}

	M_Account getAccount() {
		return account;
	}

	void setAccount(M_Account account) {
		this.account = account;
	}

	int getMitarbeiterID() {
		return mitarbeiterID;
	}

	void setMitarbeiterID(int mitarbeiterID) {
		this.mitarbeiterID = mitarbeiterID;
	}
}
