package dienstleistungenverwaltung;

import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class DLTableModel extends AbstractTableModel{
		
		private List <Dienstleistung> dliste;
		
		DLTableModel(List <Dienstleistung> liste){
			dliste = liste;
		}

		public int getColumnCount() {
			return 2;
		}
		
		public String getColumnName(int col){
			switch(col){
			case 0: return "Name";
			case 1: return "Preis pro Liter";
			default: return null;
			}
		}

		public int getRowCount() {
			return (dliste != null? dliste.size() : 0);
		}

		public Object getValueAt(int row, int col) {
			Dienstleistung d = dliste.get(row);
			switch (col){
			case 0: return d.getName();
			case 1: return d.getPreis();
			default: return null;
			}
		}
		
		 public Class<?> getColumnClass (int col){
			switch(col){
			case 0: return String.class;
			case 1: return Double.class;
			default: return null;
			}
		}
		
		Dienstleistung getDienstleistung (int row){
			return dliste.get(row);
		}
		
		List<Dienstleistung> getDienstleistungen(){
			return dliste;
		}
		
}

