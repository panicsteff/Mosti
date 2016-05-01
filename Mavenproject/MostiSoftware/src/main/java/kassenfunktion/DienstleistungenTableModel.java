package kassenfunktion;

import javax.swing.table.AbstractTableModel;

import dienstleistungProdukt.Dienstleistung;

public class DienstleistungenTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dienstleistung[] dienstleistungen;

	public DienstleistungenTableModel() {
		// this.produktsortiment = produkte;
		dienstleistungen = Dienstleistung.listeDienstleistungen;
	}

	public String getColumnName(int col) {

		switch (col) {
		case 0:
			return dienstleistungen[0].getName();
		case 1:
			return dienstleistungen[1].getName();
		case 2:
			return dienstleistungen[2].getName();
		default:
			return null;
		}
	}

	public int getRowCount() {
		return 1;
	}

	public int getColumnCount() {
		return 3;
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return dienstleistungen[col].getVerkaufsMenge();
		case 1:
			return dienstleistungen[col].getVerkaufsMenge();
		case 2:
			return dienstleistungen[col].getVerkaufsMenge();

		default:
			return null;
		}
	}

	public void setValueAt(Object eintrag, int row, int col) {

		switch (col) {
		case 0:
			dienstleistungen[col].setVerkaufsMenge((Integer) eintrag);
		case 1:
			dienstleistungen[col].setVerkaufsMenge((Integer) eintrag);
		case 2:
			dienstleistungen[col].setVerkaufsMenge((Integer) eintrag);
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
		Dienstleistung d = dienstleistungen[row];
		return d;

	}
	
	public double berechneTeilpreis(){
		double sum = 0;
		for (int i = 0; i < Dienstleistung.listeDienstleistungen.length; i++) {
			sum = sum
					+ ((Integer) (getValueAt(0, i)) * Dienstleistung.listeDienstleistungen[i]
							.getPreisProLiter());
			System.out.println((Integer) (getValueAt(0, i))
					+ " und "
					+ Dienstleistung.listeDienstleistungen[i]
							.getPreisProLiter());
		}
		
		return sum;
	}

}