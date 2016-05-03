package mitarbeiterverwaltung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MitarbeiterTableModel extends AbstractTableModel {


private ArrayList<Mitarbeiter> mitarbeitern;

	
	public Class<?> getColumnClass(int col){
		switch(col){
		default: return String.class;
		}
	}
	
	public String getColumnName(int col){
		switch(col){
		case 0: return "Nachname";
		case 1: return "Vorname";
		case 2: return "Straße";
		case 3: return "Hausnummer";
		case 4: return "Plz";
		case 5: return "Stadt";
		case 6: return "Telefonnummer";
		default: return null;
		}
	}
	
	public int getRowCount(){
		return mitarbeitern != null ? mitarbeitern.size():0;
	}
	
	public int getColumnCount(){
		return 7;
	}
	
	public Object getValueAt(int row, int col){
		Mitarbeiter mitarbeiter = mitarbeitern.get(row); // ??? 
		switch(col){
		
		case 0: return mitarbeiter.getNachname();
		case 1: return mitarbeiter.getVorname();
		case 2: return mitarbeiter.getStrasse();
		case 3: return mitarbeiter.getHausnummer();
		case 4: return mitarbeiter.getPlz();
		case 5: return mitarbeiter.getStadt();
		case 6: return mitarbeiter.getTelefonnummer();
		default: return null;
		}
	}
	
	public ArrayList<Mitarbeiter> getMitarbeiter(){
		return mitarbeitern;
	}
	
	public void setMitarbeiter(ArrayList<Mitarbeiter> mitarbeiter){
		this.mitarbeitern = mitarbeiter;
	}
	
	public Mitarbeiter getMitarbeiter(int row){
		return mitarbeitern.get(row);
	}
}
