package logik.mitarbeiterverwaltung;

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
	private String passwort;

	public Mitarbeiter(String nachname, String vorname, String strasse,
			String hausnummer, String plz, String stadt, String telefonnummer,
			int mitarbeiterId, String benutzername, String passwort) {
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setPlz(plz);
		setStadt(stadt);
		setTelefonnummer(telefonnummer);
		setMitarbeiterID(mitarbeiterId);
		setBenutzername(benutzername);
		setPasswort(passwort);
	}

	public Mitarbeiter() {

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

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public int getMitarbeiterID() {
		return mitarbeiterID;
	}

	public void setMitarbeiterID(int mitarbeiterID) {
		this.mitarbeiterID = mitarbeiterID;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPasswort() {
		return passwort;
	}
}
