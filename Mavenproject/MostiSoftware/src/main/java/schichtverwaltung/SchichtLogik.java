package schichtverwaltung;

import java.sql.Date;
import java.util.ArrayList;

import persistenz.SchichtplanDB;
import administratorverwaltung.AdministratorLogik;


public class SchichtLogik {
	
	private SchichtplanDB schichtplanDb;
	
	public SchichtLogik(){
		schichtplanDb = new SchichtplanDB();
	}
	
	public ArrayList<Schicht> schichtLaden(long datum){
		Date d = new Date(datum);
		ArrayList<Schicht> schichtliste = schichtplanDb.schichtLaden(d);
		schichtliste = schichtenSortieren(schichtliste);
		schichtliste = schichtenMergen(schichtliste);
		return schichtliste;
		
	}
	
	void schichtSpeichern(Date datum, int mitarbeiterId, int uhrzeit){
		schichtplanDb.schichtSpeichern(datum, mitarbeiterId, uhrzeit);
	}
	
	public ArrayList<Schicht> schichtenSortieren(ArrayList<Schicht> liste){
		ArrayList<Schicht> sortiert = new ArrayList<Schicht>();
		
		for(int i=0; i<getSchichtenProTag(); i++){
			for(int j=0; j<liste.size(); j++){
				if(liste.get(j).getUhrzeit() == getArbeitsbeginn()+(i*getSchichtDauer())){
					sortiert.add(liste.get(j));
				}
			}
		}
		return sortiert;
	}
	
	public ArrayList<Schicht> schichtenMergen(ArrayList<Schicht> liste){
		ArrayList<Schicht> neueListe = new ArrayList<Schicht>();
		int j=0;
		for(int i=getArbeitsbeginn(); i<getArbeitsende(); i = i+getSchichtDauer()){
			if(j<liste.size() && liste.get(j).getUhrzeit() == i){
				Schicht s = liste.get(j);
				j++;
				while(j<liste.size() && liste.get(j).getUhrzeit() == i){
					s.addMitarbeiterId(liste.get(j).getMitarbeiterId(0));
					j++;
				}
				neueListe.add(s);
			}else{
				Schicht s = new Schicht();
				s.setUhrzeit(i);
				neueListe.add(s);
			}
		}
		return neueListe;
	}
	
	public ArrayList<Integer> mitarbeiterIdLaden(String name){
		return schichtplanDb.mitarbeiterIdLaden(name);
	}
	
	public String mitarbeiternameLaden(int id){
		return schichtplanDb.mitarbeiterNamenLaden(id);
	}
	
	int getMitarbeiterProSchicht(){
		return AdministratorLogik.getMitarbeiterProSchicht();
	}
	
	int getSchichtenProTag(){
		return AdministratorLogik.getSchichtenProTag();
	}
	
	int getArbeitsbeginn(){
		return AdministratorLogik.getArbeitsbeginn();
	}
	
	int getSchichtDauer(){
		return AdministratorLogik.getSchichtDauer();
	}
	
	int getArbeitsende(){
		return AdministratorLogik.getArbeitsende();
	}
	
	public int berechneUhrzeit(int i){
		int spalte = i%getSchichtenProTag();
		int uhrzeit = getArbeitsbeginn() + spalte*getSchichtDauer();
		return uhrzeit;
	}
}
