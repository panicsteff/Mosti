package kundenverwaltung;

import java.util.Date;

public class Kunde {
	private String nachname;
	private String vorname;
	private String strasse;
	private String plz;
	private String wohnort;
	private String telefonnummer;
	private int kundenID;
	
	
	public Kunde(String nachname, String vorname, String strasse, String PLZ, String ort, String telefonnummer, int kundenID){
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setPlz(PLZ);
		setWohnort(ort);
		setTel(telefonnummer);
		setKundenID(kundenID);
		
	}
	
	public Kunde(){
		
	}
	
	String getNachname(){
		return nachname;
	}
	
	void setNachname(String nachname){
		this.nachname = nachname;
	}
	
	String getVorname(){
		return vorname;
	}
	
	void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	
	String getStrasse(){
		return strasse;
	}
	
	void setStrasse(String strasse){
		this.strasse = strasse;
	}
	
	
	String getPlz(){
		return plz;
	}
	
	void setPlz(String plz){
		this.plz = plz;
	}
	
	String getWohnort(){
		return wohnort;
	}
	
	void setWohnort(String ort){
		this.wohnort = ort;
	}
	
	String getTel(){
		return telefonnummer;
	}
	
	void setTel(String telefonnummer){
		this.telefonnummer = telefonnummer;
	}

	int getKundenID() {
		return kundenID;
	}

	void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}
	
}
