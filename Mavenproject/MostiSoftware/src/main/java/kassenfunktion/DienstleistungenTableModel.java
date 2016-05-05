package kassenfunktion;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dienstleistungProdukt.Dienstleistung;
import dienstleistungProdukt.Produkt;

public class DienstleistungenTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList <Dienstleistung> dienstleistungen;
	private static int DLAnzahl;

	public DienstleistungenTableModel(ArrayList <Dienstleistung> dienstleistungen) {
		this.dienstleistungen = dienstleistungen;
		DLAnzahl = getColumnCount();
	}

	public String getColumnName(int col) {
		if (col >= 0 && col < DLAnzahl) {
			return dienstleistungen.get(col).getName();
		} else
			return null;
	}

	public int getRowCount() {
		return 1;
	}

	public int getColumnCount() {
		return dienstleistungen != null? dienstleistungen.size() : 0;
	}

	public Object getValueAt(int row, int col) {
		if (col >= 0 && col < DLAnzahl) {
			return dienstleistungen.get(col).getVerkaufsMenge();
		} else
			return null;
	}

	public void setValueAt(Object eintrag, int row, int col) {

		if (col >= 0 && col < DLAnzahl) {
			dienstleistungen.get(col).setVerkaufsMenge((Integer) eintrag);
		}
		this.fireTableDataChanged();

	}

	public Class<?> getColumnClass(int columnIndex) {
		return Integer.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public Dienstleistung getDL(int row) {
		Dienstleistung d = dienstleistungen.get(row);
		return d;

	}
	
	public double berechneTeilpreis(){
		double sum = 0;
		for (int i = 0; i < DLAnzahl; i++) {
			sum = sum
					+ ((Integer) (getValueAt(0, i)) * dienstleistungen.get(i)
							.getPreis());
			System.out.println((Integer) (getValueAt(0, i))
					+ " und "
					+ dienstleistungen.get(i)
							.getPreis());
		}
		
		return sum;
	}

}