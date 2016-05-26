package dienstleistungenverwaltung;

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
	
	void addDienstleistung(Dienstleistung d) {
		dldb.dienstleistungHinzufügen(d);
		dliste.add(d);
	}
	
	void dienstleistungenAktualisieren(){
		dldb.dlUpdaten(dliste);
	}
	
	void deleteDienstleistung(Dienstleistung d) {
		dldb.dienstleistungLöschen(d);
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




