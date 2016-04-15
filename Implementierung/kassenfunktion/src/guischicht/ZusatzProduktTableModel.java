package guischicht;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import produkte.Produkt;
import dienstleistung.Dienstleistung;

public class ZusatzProduktTableModel extends AbstractTableModel{

		
		private ArrayList<Produkt> produktsortiment;
		
		public ZusatzProduktTableModel(ArrayList<Produkt> produkte){
			this.produktsortiment = produkte;
		}
		
		public String getColumnName(int col){
		
			switch(col){
			case 0: return produktsortiment.get(0).getName();
			case 1: return produktsortiment.get(1).getName();
			case 2: return produktsortiment.get(2).getName();
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
		
		public Produkt getProdukt(int row){
			Produkt produkt = produktsortiment.get(row);
			return produkt;
			
		}


}
