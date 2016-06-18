package gui.terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import logik.terminplanung.Termin;
import logik.terminplanung.TerminLogik;

public class TermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> terminliste;
	private int anzeigeseite;
	private TerminLogik terminlogik;
	

	public TermineTableModel(ArrayList<Termin> terminliste, int as) {
		this.terminliste = terminliste;
		anzeigeseite = as; 
		terminlogik = new TerminLogik();
	}

	public String getColumnName(int col){
		switch(col){
		case 0: return "Uhrzeit";
		case 1: return "Kunde";
		case 2: return "Menge";
		case 3: return "in Flaschen";
		
		default: return null;
		}
	}
	
	public Class<?> getColumnClass(int col){
		switch(col){
		case 0: return String.class;
		case 1: return String.class;
		case 2: return String.class;
		case 3: return Boolean.class;
		
		default: return null;
		}
	}
	
	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return terminlogik.getZeilenAnzahlProSeite();
	}

	public Object getValueAt(int row, int col) {
		
		int reihe = (anzeigeseite - 1) * terminlogik.getZeilenAnzahlProSeite() + row;
		
		switch (col) {
		case 0:
			return terminliste.get(reihe).getUhrzeit();				
			
		case 1:
			return terminliste.get(reihe).getKundenId();
		
		case 2:
			if(terminliste.get(reihe).getKundenId() == 0){
				return "";
			} else{
				return terminliste.get(reihe).getMenge();
			}				
			
		case 3:
			return terminliste.get(reihe).isInFlaschen();
			
			
			
		default:
			return null;
		}
	}
	
	public ArrayList<Termin> getTermine(int row, int anzahl){
		ArrayList<Termin> liste = new ArrayList<Termin>();
		try{
			for(int i=0; i<anzahl; i++){
				liste.add(terminliste.get(row+i));
			}
		} catch(IndexOutOfBoundsException e){
			;
		}
		return liste;
	}
	
	public ArrayList<Termin> getAlleTermine(){
		return terminliste;
	}
	
	public void erhoeheAnzeigeseite(){
		anzeigeseite++;
	}
	
	public void erniedrigeAnzeigeseite(){
		anzeigeseite--;
	}
	
	
}
