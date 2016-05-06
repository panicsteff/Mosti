package kassenfunktion;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import verkaufsverwaltung.Produkt;

public class ProduktTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Produkt> produktsortiment;
	private static int produktAnzahl;

	public ProduktTableModel(ArrayList<Produkt> produkte) {
		this.produktsortiment = produkte;
		produktAnzahl = getColumnCount();

	}

	public Class<?> getColumnClass(int columnIndex) {
		return Integer.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public String getColumnName(int col) {

		if (col >= 0 && col < produktAnzahl) {
			return produktsortiment.get(col).getName();
		} else
			return null;

		// switch(col){
		// case 0: return produktsortiment.get(0).getName();
		// case 1: return produktsortiment.get(1).getName();
		// case 2: return produktsortiment.get(2).getName();
		// default: return null;
		// }
	}

	public int getRowCount() {
		return 1;
	}

	public int getColumnCount() {
		return produktsortiment != null? produktsortiment.size() : 0;
	}

	public Object getValueAt(int row, int col) {

		if (col >= 0 && col < produktAnzahl) {
			return produktsortiment.get(col).getVerkaufsMenge();
		} else
			return null;

		// switch (col){
		// case 0: return 0;
		// case 1: return 0;
		// case 2: return 0;
		// default: return null;
		// }
	}

	public void setValueAt(Object eintrag, int row, int col) {

		if ((Integer) eintrag <0){
			JOptionPane.showMessageDialog(null, "Keine negativen Werte erlaubt!");
			return;
		}
		if (col >= 0 && col < produktAnzahl) {
			produktsortiment.get(col).setVerkaufsMenge((Integer) eintrag);
		}
		this.fireTableDataChanged();

	}

	public Produkt getProdukt(int row) {
		Produkt produkt = produktsortiment.get(row);
		return produkt;

	}
	
	public double berechneTeilpreis(){
		double sum = 0;
		for (int i = 0; i < produktAnzahl; i++) {
			sum = sum
					+ ((Integer) (getValueAt(0, i)) * produktsortiment
							.get(i).getPreis());
			System.out.println((Integer) (getValueAt(0, i))
					+ " und " + produktsortiment.get(i).getPreis());

		}
		sum = Math.round(sum*100);
		sum = sum/100;
		return sum;
	}

}
