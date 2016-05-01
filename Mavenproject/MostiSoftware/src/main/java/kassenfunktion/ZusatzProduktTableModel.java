package kassenfunktion;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dienstleistungProdukt.Produkt;

public class ZusatzProduktTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Produkt> produktsortiment;

	public ZusatzProduktTableModel(ArrayList<Produkt> produkte) {
		this.produktsortiment = produkte;
	}

	public String getColumnName(int col) {

		return produktsortiment.get(col).getName();							//hinzugefügt von Irmi
		
//		switch (col) {
//		case 0:
//			return produktsortiment.get(0).getName();
//		case 1:
//			return produktsortiment.get(1).getName();
//		case 2:
//			return produktsortiment.get(2).getName();
//		default:
//			return null;
//		}
	}

	public int getRowCount() {
		return 1;
	}

	public int getColumnCount() {
		return produktsortiment.size();											//Irmi
		
		//return 3;
	}

	public Object getValueAt(int row, int col) {
		
		return produktsortiment.get(col).getVerkaufsMenge();					//
		
//		switch (col) {
//		case 0:
//			return produktsortiment.get(col).getVerkaufsMenge();
//		case 1:
//			return produktsortiment.get(col).getVerkaufsMenge();
//		case 2:
//			return produktsortiment.get(col).getVerkaufsMenge();
//		default:
//			return null;
//		}
	}

	public void setValueAt(Object eintrag, int row, int col) {

		produktsortiment.get(col).setVerkaufsMenge((Integer) eintrag);					//
		
//		switch (col) {
//		case 0:
//			produktsortiment.get(col).setVerkaufsMenge((Integer) eintrag);
//		case 1:
//			produktsortiment.get(col).setVerkaufsMenge((Integer) eintrag);
//		case 2:
//			produktsortiment.get(col).setVerkaufsMenge((Integer) eintrag);
//		}
		this.fireTableDataChanged();

	}

	public Class<?> getColumnClass(int columnIndex) {
		return Integer.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public Produkt getProdukt(int row) {
		Produkt produkt = produktsortiment.get(row);
		return produkt;

	}

	public double berechneTeilpreis(){
		double sum = 0;
		for (int i = 0; i < produktsortiment.size(); i++) {
			sum = sum
					+ ((Integer) (getValueAt(0, i)) * produktsortiment
							.get(i).getPreis());
			System.out.println((Integer) (getValueAt(0, i))
					+ " und " + produktsortiment.get(i).getPreis());
		}
		
		return sum;
	}
}
