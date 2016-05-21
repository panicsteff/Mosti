package terminplanung;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TermineTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> terminliste;
	private int anzeigeseite;
	private Konfigurationswerte k;
	

	TermineTableModel(ArrayList<Termin> terminliste) {
		this.terminliste = terminliste;
		anzeigeseite = 1;
		k = new Konfigurationswerte();  
	}

	public String getColumnName(int col){
		switch(col){
		case 0: return "Uhrzeit";
		case 1: return "Kunde";
		
		default: return null;
		}
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return k.getZeilenanzahlProSeite();
	}

	public Object getValueAt(int row, int col) {
		
		int reihe = (anzeigeseite - 1) * k.getZeilenanzahlProSeite() + row;
		
		switch (col) {
		case 0:
			return terminliste.get(reihe).getTerminId();				
			
		case 1:
			return terminliste.get(reihe).getKundenId();
			
		default:
			return null;
		}
	}
	
	ArrayList<Termin> getTermine(int row, int anzahl){
		ArrayList<Termin> liste = new ArrayList<Termin>();
		for(int i=0; i<anzahl; i++){
			liste.add(terminliste.get(row+i));
		}
	
		return liste;
	}
	
	ArrayList<Termin> getAlleTermine(){
		return terminliste;
	}
	
	void erhoeheAnzeigeseite(){
		anzeigeseite++;
	}
	
	void erniedrigeAnzeigeseite(){
		anzeigeseite--;
	}
	
	
}
