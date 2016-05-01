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
	
	public String getNachname(){
		return nachname;
	}
	
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	
	public String getVorname(){
		return vorname;
	}
	
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	
	public String getStrasse(){
		return strasse;
	}
	
	public void setStrasse(String strasse){
		this.strasse = strasse;
	}
	
	
	public String getPlz(){
		return plz;
	}
	
	public void setPlz(String plz){
		this.plz = plz;
	}
	
	public String getWohnort(){
		return wohnort;
	}
	
	public void setWohnort(String ort){
		this.wohnort = ort;
	}
	
	public String getTel(){
		return telefonnummer;
	}
	
	public void setTel(String telefonnummer){
		this.telefonnummer = telefonnummer;
	}

	public int getKundenID() {
		return kundenID;
	}

	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}
	
}
