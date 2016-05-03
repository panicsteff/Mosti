package kassenfunktion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dienstleistungProdukt.Dienstleistung;
import dienstleistungProdukt.Einkauf;
import dienstleistungProdukt.Einkaufsposition;
import dienstleistungProdukt.Produkt;

public class Abrechnungsrahmen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZusatzProduktTableModel zusatzTableModel;
	private AbfüllMaterialTableModel abfüllTableModel;
	private DienstleistungenTableModel dienstTableModel;
	private JLabel label;
	private JTable zusatzTable;
	private double total;
	private JTextField totalText;
	private ArrayList<Produkt> abfüllProduktSortiment;
	private ArrayList<Produkt> zusatzProduktSortiment;
	private ArrayList<Dienstleistung> dienstleistungen;
	private Einkauf einkauf;

	public Abrechnungsrahmen(ArrayList<Dienstleistung> dienstleistungen, ArrayList<Produkt> abfüllProduktSortiment,
			ArrayList<Produkt> zusatzProduktSortiment) {

		this.abfüllProduktSortiment = abfüllProduktSortiment;
		this.zusatzProduktSortiment = zusatzProduktSortiment;
		this.dienstleistungen = dienstleistungen;

		setTitle("Abrechnung für <Kundenname>");
		setSize(700, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(8, 1));

		dienstTableModel = new DienstleistungenTableModel(dienstleistungen);
		JTable dienstTable = new JTable(dienstTableModel);

		abfüllTableModel = new AbfüllMaterialTableModel(abfüllProduktSortiment);
		JTable abfüllTable = new JTable(abfüllTableModel);

		zusatzTableModel = new ZusatzProduktTableModel(zusatzProduktSortiment);
		zusatzTable = new JTable(zusatzTableModel);
		// zusatzTable.getColumnModel().getColumn(0).setCellRenderer(new
		// AnzahlTableCellRenderer());
		// zusatzTable.getColumnModel().getColumn(2).setCellRenderer(new
		// AnzahlTableCellRenderer());

		JScrollPane tableContainer1 = new JScrollPane(dienstTable);
		JScrollPane tableContainer2 = new JScrollPane(abfüllTable);
		JScrollPane tableContainer3 = new JScrollPane(zusatzTable);

		label = new JLabel("Literzahlen");
		label.setFont(label.getFont().deriveFont(18f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		contentPanel.add(label);
		contentPanel.add(tableContainer1);

		label = new JLabel("Abfüll-Materialien");
		label.setFont(label.getFont().deriveFont(18f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		contentPanel.add(label);
		contentPanel.add(tableContainer2);

		label = new JLabel("Weitere Produkte");
		label.setFont(label.getFont().deriveFont(18f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		contentPanel.add(label);
		contentPanel.add(tableContainer3);

		JPanel summePanel = new JPanel();
		summePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		summePanel.add(new JLabel("Gesamtsumme in Euro: "));

		double sum1 = 0;
//		for (int i = 0; i < Dienstleistung.listeDienstleistungen.length; i++) {
//			sum1 = sum1
//					+ ((Integer) (dienstTableModel.getValueAt(0, i)) * Dienstleistung.listeDienstleistungen[i]
//							.getPreisProLiter());
//			System.out.println((Integer) (dienstTableModel.getValueAt(0, i))
//					+ " und "
//					+ Dienstleistung.listeDienstleistungen[i]
//							.getPreisProLiter());
//		}
		sum1 = dienstTableModel.berechneTeilpreis();									//
		System.out.println();
		double sum2 = 0;
//		for (int i = 0; i < abfüllProduktSortiment.size(); i++) {
//			sum2 = sum2
//					+ ((Integer) (abfüllTableModel.getValueAt(0, i)) * abfüllProduktSortiment
//							.get(i).getPreis());
//			System.out.println((Integer) (abfüllTableModel.getValueAt(0, i))
//					+ " und " + abfüllProduktSortiment.get(i).getPreis());
//
//		}
		sum2 = abfüllTableModel.berechneTeilpreis();									//
		System.out.println();
		double sum3 = 0;
//		for (int i = 0; i < zusatzProduktSortiment.size(); i++) {
//			sum3 = sum3
//					+ ((Integer) (zusatzTableModel.getValueAt(0, i)) * zusatzProduktSortiment
//							.get(i).getPreis());
//			System.out.println((Integer) (zusatzTableModel.getValueAt(0, i))
//					+ " und " + zusatzProduktSortiment.get(i).getPreis());
//		}
		sum3 = zusatzTableModel.berechneTeilpreis();									//
		total = sum1 + sum2 + sum3;

		totalText = new JTextField(13);
		totalText.setHorizontalAlignment(JTextField.RIGHT);
		totalText.setEditable(false);
		totalText.setText(String.valueOf(total));

		summePanel.add(totalText);
		contentPanel.add(summePanel);

		JPanel buttonPanel = new JPanel();
		JButton abschlussButton = new JButton("Einkauf abschließen");
		JButton aktualisiereSummeButton = new JButton(
				"Kostensumme aktualisieren");
		JButton abbrechButton = new JButton("Abbrechen");
		abbrechButton.addActionListener(new AbbruchHandler());
		aktualisiereSummeButton
				.addActionListener(new AktualisiereSummeHandler());
		abschlussButton.addActionListener(new EinkaufAbschließenHandler());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(abbrechButton);
		buttonPanel.add(aktualisiereSummeButton);
		buttonPanel.add(abschlussButton);
		contentPanel.add(buttonPanel);

		// getContentPane().add(contentPanel);

		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Einkäufe"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(contentPanel);
		add(titlepane);

		setVisible(true);

	}

	class AbbruchHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	class AktualisiereSummeHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double sum1 = 0;
//			for (int i = 0; i < Dienstleistung.listeDienstleistungen.length; i++) {
//				sum1 = sum1
//						+ ((Integer) (dienstTableModel.getValueAt(0, i)) * Dienstleistung.listeDienstleistungen[i]
//								.getPreisProLiter());
//			}
			sum1 = dienstTableModel.berechneTeilpreis();									//
			double sum2 = 0;
//			for (int i = 0; i < abfüllProduktSortiment.size(); i++) {
//				sum2 = sum2
//						+ ((Integer) (abfüllTableModel.getValueAt(0, i)) * abfüllProduktSortiment
//								.get(i).getPreis());
//			}
			sum2 = abfüllTableModel.berechneTeilpreis();									//
			double sum3 = 0;
//			for (int i = 0; i < zusatzProduktSortiment.size(); i++) {
//				sum3 = sum3
//						+ ((Integer) (zusatzTableModel.getValueAt(0, i)) * zusatzProduktSortiment
//								.get(i).getPreis());
//			}
			sum3 = zusatzTableModel.berechneTeilpreis();									//
			total = sum1 + sum2 + sum3;

			System.out.println(total);
			totalText.setText(String.valueOf(total));
		}
	}

	class EinkaufAbschließenHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Einkaufsposition e;
			einkauf = new Einkauf();
			System.out
					.println(zusatzProduktSortiment.get(0).getVerkaufsMenge());

			for (int i = 0; i < dienstleistungen.size(); i++) {
				e = new Einkaufsposition(
						dienstleistungen.get(i).getName(),
						dienstleistungen.get(i)
								.getVerkaufsMenge());
				dienstleistungen.get(i).setVerkaufsMenge(0);
			}
			for (int i = 0; i < abfüllProduktSortiment.size(); i++) {
				e = new Einkaufsposition(abfüllProduktSortiment.get(i)
						.getName(), abfüllProduktSortiment.get(i)
						.getVerkaufsMenge());
				abfüllProduktSortiment.get(i).setVerkaufsMenge(0);
				einkauf.addEinkauf(e);
			}
			for (int i = 0; i < zusatzProduktSortiment.size(); i++) {
				e = new Einkaufsposition(zusatzProduktSortiment.get(i)
						.getName(), zusatzProduktSortiment.get(i)
						.getVerkaufsMenge());
				zusatzProduktSortiment.get(i).setVerkaufsMenge(0);
				einkauf.addEinkauf(e);
			}

			total = dienstTableModel.berechneTeilpreis() + abfüllTableModel.berechneTeilpreis() + zusatzTableModel.berechneTeilpreis();
			einkauf.setSumme(total);											
			einkauf.printEinkauf();
//			System.out
//					.println(zusatzProduktSortiment.get(0).getVerkaufsMenge());
			dispose();

		}

	}

}