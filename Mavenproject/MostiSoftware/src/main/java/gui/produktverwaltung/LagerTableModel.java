
package gui.produktverwaltung;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import logik.produktverwaltung.Produkt;



class LagerTableModel extends AbstractTableModel{
		
		
		private static final long serialVersionUID = 1L;
		
		private List <Produkt> pliste;
		
		public LagerTableModel(List <Produkt> liste){
			pliste = liste;
		}

		public int getColumnCount() {
			return 5;
		}
		
		public String getColumnName(int col){
			switch(col){
			case 0: return "Name";
			case 1: return "Einzelpreis";
			case 2: return "Vorrätige Menge";
			case 3: return "Untergrenze";
			case 4: return "Abfüllmaterial";
			default: return null;
			}
		}
		
		
		public int getRowCount() {
			//return (produktliste != null? produktliste.size() : 0);
			return (pliste != null? pliste.size() : 0);
		}

		public Object getValueAt(int row, int col) {
			
			Produkt p = pliste.get(row);
			switch (col){
			case 0: return p.getName();
			case 1: return p.getPreis();
			case 2: return p.getVorratsmenge();
			case 3: return p.getUntergrenze();
			case 4: return p.isAbfüllmaterial();
			default: return null;
			}
		}
		
		public Class<?> getColumnClass (int col){
			switch(col){
			case 0: return String.class;
			case 1: return Double.class;
			case 2: return Integer.class;
			case 3: return Integer.class;
			case 4: return Boolean.class;
			default: return null;
			}
		}
		
		public Produkt getProdukt (int row){
			return pliste.get(row);
		}
		
		List<Produkt> getProdukte(){
			return pliste;
		}
		
		void setProdukte (List<Produkt> liste){
			pliste = liste;
		}

}
