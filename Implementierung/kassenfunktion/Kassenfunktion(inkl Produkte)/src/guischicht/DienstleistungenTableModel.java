package guischicht;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import produkte.Produkt;
import dienstleistung.Dienstleistung;

public class DienstleistungenTableModel extends AbstractTableModel {
	
	private ArrayList<Produkt> produktsortiment;
	private Dienstleistung[] dienstleistungen;
	
	public DienstleistungenTableModel(ArrayList<Produkt> produkte){
		this.produktsortiment = produkte;
		dienstleistungen = Dienstleistung.listeDienstleistungen;
	}
	
	public String getColumnName(int col){
		
		switch(col){
		case 0: return dienstleistungen[0].getName();
		case 1: return dienstleistungen[1].getName();
		case 2: return dienstleistungen[2].getName();
		default: return null;
		}
	}
	
	public int getRowCount(){
		return 1;
	}
	
	public int getColumnCount(){
		return 3;
	}
	
	public Object getValueAt(int row, int col){
		switch (col){
		case 0: return 0;
		case 1: return 0;
		case 2: return 0;

		default: return null;
		}
	}
	
	public Dienstleistung getDL(int row){
		Dienstleistung d = dienstleistungen[row];
		return d;
		
	}


}
