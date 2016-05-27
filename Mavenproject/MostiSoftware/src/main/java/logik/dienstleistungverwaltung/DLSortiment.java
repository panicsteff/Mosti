package logik.dienstleistungverwaltung;

import java.util.ArrayList;

import persistenz.DienstleistungenDB;

public class DLSortiment {

	private static ArrayList<Dienstleistung> dliste;
	private static DienstleistungenDB dldb;

	public DLSortiment() {
		dldb = new DienstleistungenDB(); 
		dliste = dldb.dienstleistungenLaden();
		printDLSortiment();
	}
	
	public void addDienstleistung(Dienstleistung d) {
		dldb.dienstleistungHinzuf�gen(d);
		dliste.add(d);
	}
	
	public void dienstleistungenAktualisieren(){
		dldb.dlUpdaten(dliste);
	}
	
	public void deleteDienstleistung(Dienstleistung d) {
		dldb.dienstleistungL�schen(d);
		dliste.remove(d);
	}

	public ArrayList<Dienstleistung> getDLSortiment() {
		return dliste;
	}
	
	public void printDLSortiment(){
		for(Dienstleistung d: dliste){
			System.out.print(d.getName() + " ");
		}
		System.out.println();
	}
	
}




