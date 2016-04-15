package guischicht;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import produkte.Produkt;

public class AbfüllMaterialTableModel extends AbstractTableModel {
	
	private ArrayList<Produkt> produktsortiment;
	private static int produktAnzahl;
	
	public AbfüllMaterialTableModel(ArrayList<Produkt> produkte){
		this.produktsortiment = produkte;
		produktAnzahl = produktsortiment.size();
		
	}
	
	public String getColumnName(int col){
		
		if(col >= 0 && col < produktAnzahl){
			return produktsortiment.get(col).getName();
		}
		else 
			return null;
	
//		switch(col){
//		case 0: return produktsortiment.get(0).getName();
//		case 1: return produktsortiment.get(1).getName();
//		case 2: return produktsortiment.get(2).getName();
//		default: return null;
//		}
	}
	
	public int getRowCount(){
		return 1;
	}
	
	public int getColumnCount(){
		return produktAnzahl;
	}
	
	public Object getValueAt(int row, int col){
		
		if(col >= 0 && col < produktAnzahl){
			return 0;
		}
		else 
			return null;
		
		
		
//		switch (col){
//		case 0: return 0;
//		case 1: return 0;
//		case 2: return 0;
//		default: return null;
//		}
	}
	
	public Produkt getProdukt(int row){
		Produkt produkt = produktsortiment.get(row);
		return produkt;
		
	}



}
