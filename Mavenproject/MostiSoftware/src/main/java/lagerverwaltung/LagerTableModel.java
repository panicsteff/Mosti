package lagerverwaltung;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Dienstleistung_Produkt.Produkt;


public class LagerTableModel extends AbstractTableModel{
		
		private List <Produkt> produktliste;
		
		public LagerTableModel(List <Produkt> liste){
			produktliste = liste;
		}

		public int getColumnCount() {
			return 4;
		}
		
		public String getColumnName(int col){
			switch(col){
			case 0: return "Name";
			case 1: return "Einzelpreis";
			case 2: return "Menge";
			case 3: return "Untergrenze";
			default: return null;
			}
		}

		public int getRowCount() {
			return (produktliste != null? produktliste.size() : 0);
		}

		public Object getValueAt(int row, int col) {
			Produkt p = produktliste.get(row);
			switch (col){
			case 0: return p.getName();
			case 1: return p.getPreis();
			case 2: return p.getMenge();
			case 3: return p.getUntergrenze();
			default: return null;
			}
		}
		
		public Class<?> getColumnClass (int col){
			switch(col){
			case 0: return String.class;
			case 1: return Double.class;
			case 2: return Integer.class;
			case 3: return Integer.class;
			default: return null;
			}
		}
		
		public Produkt getProdukt (int row){
			return produktliste.get(row);
		}
		
		public List<Produkt> getProdukte(){
			return produktliste;
		}
		
		public void setGetraenke (List<Produkt> liste){
			produktliste = liste;
		}

	


}
