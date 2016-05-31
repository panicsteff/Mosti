package administratorverwaltung;

import java.sql.Date;
import java.util.ArrayList;


public class SchichtLogik {
	
	private SchichtplanDB schichtplanDb;
	private Konfigurationswerte k;
	
	public SchichtLogik(){
		k = new Konfigurationswerte();
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
		
		for(int i=0; i<k.getSchichtenProTag(); i++){
			for(int j=0; j<liste.size(); j++){
				if(liste.get(j).getUhrzeit() == k.getArbeitsbeginn()+(i*k.getSchichtDauer())){
					sortiert.add(liste.get(j));
				}
			}
		}
		return sortiert;
	}
	
	public ArrayList<Schicht> schichtenMergen(ArrayList<Schicht> liste){
		ArrayList<Schicht> neueListe = new ArrayList<Schicht>();
		int j=0;
		for(int i=k.getArbeitsbeginn(); i<k.getArbeitsende(); i = i+k.getSchichtDauer()){
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
		return k.getMitarbeiterProSchicht();
	}
	
	int getSchichtenProTag(){
		return k.getSchichtenProTag();
	}
	
	public int berechneUhrzeit(int i){
		int spalte = i%k.getSchichtenProTag();
		int uhrzeit = k.getArbeitsbeginn() + spalte*k.getSchichtDauer();
		return uhrzeit;
	}
}
