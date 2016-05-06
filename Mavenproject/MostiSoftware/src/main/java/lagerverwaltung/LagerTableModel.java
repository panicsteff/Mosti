package lagerverwaltung;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dienstleistungProdukt.Produkt;



public class LagerTableModel extends AbstractTableModel{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		//private List <Produkt> produktliste;
		private List <Produkt> aliste;
		private List <Produkt> zliste;
		
//		public LagerTableModel(List <Produkt> liste){
//			produktliste = liste;
//		}
		
		public LagerTableModel(List <Produkt> aliste, List <Produkt> zliste){
			this.aliste = aliste;
			this.zliste = zliste;
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
			return (aliste != null? aliste.size() : 0) + (zliste != null? zliste.size() : 0);
		}

		public Object getValueAt(int row, int col) {
			Produkt p;
			if(row < (aliste != null? aliste.size() : 0))
				p = aliste.get(row);
			else
				p = zliste.get(row - (aliste != null? aliste.size() : 0));
			
			//Produkt p = produktliste.get(row);
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
			if(row < (aliste != null? aliste.size() : 0))
				return aliste.get(row);
			else
				return zliste.get(row-(aliste != null? aliste.size() : 0));
			
			//return produktliste.get(row);
		}
		
		public List<Produkt> getZusatzProdukte(){
			return zliste;
		}
		
		public void setZusatzProdukte (List<Produkt> liste){
			zliste = liste;
		}
		
		public List<Produkt> getAbfuellProdukte(){
			return aliste;
		}
		
		public void setAbfuellProdukte (List<Produkt> liste){
			aliste = liste;
		}

	


}
