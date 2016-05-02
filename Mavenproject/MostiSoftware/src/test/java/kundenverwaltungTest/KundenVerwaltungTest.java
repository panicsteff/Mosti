package kundenverwaltungTest;

import kundenverwaltung.KundenVerwaltung;

public class KundenVerwaltungTest {

	public static void main(String[] avg){
		//kundenLaden();
		//kundenSpeichern();
		kundeBearbeiten();
	}
	
	
	
	
	public static void kundenLaden(){
		//auch leere Felder in der Datenbank
		new KundenVerwaltung();
		
		//falscher Pfad zur Datenbank
		//new KundenVerwaltung("test");
		
		//keine Datensätze vorhanden
		new KundenVerwaltung();
	}
	
	public static void kundenSpeichern(){
		new KundenVerwaltung();
		
		// falsche Eingaben
		new KundenVerwaltung();
	}
	
	public static void kundeBearbeiten(){
		new KundenVerwaltung();
	}
	
}
