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

import lagerverwaltung.Produkt;
import dienstleistungenverwaltung.Dienstleistung;
import trester.Tresterabrechnung;
import verkaufsverwaltung.Einkauf;
import verkaufsverwaltung.Einkaufsposition;
import verkaufsverwaltung.Kundeneinkäufe;

public class Abrechnungsrahmen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProduktTableModel zusatzTableModel;
	private ProduktTableModel abfüllTableModel;
	private DienstleistungenTableModel dienstTableModel;
	private JLabel label;
	private double total;
	private JTextField totalText;
	private ArrayList<Produkt> aliste;
	private ArrayList<Produkt> zliste;
	private ArrayList<Dienstleistung> dienstleistungen;
	private Einkauf einkauf;
	private Kundeneinkäufe kundeneinkäufe;
	private Einkaufsposition DLposition;
	private Einkaufsposition produktPosition;
	private int literzahl;

	public Abrechnungsrahmen(ArrayList<Dienstleistung> dienstleistungen,
			ArrayList<Produkt> abfüllProduktSortiment,
			ArrayList<Produkt> zusatzProduktSortiment,
			Kundeneinkäufe kundeneinkäufe) {

		this.aliste = abfüllProduktSortiment;
		this.zliste = zusatzProduktSortiment;
		this.dienstleistungen = dienstleistungen;
		this.kundeneinkäufe = kundeneinkäufe;

		initVerkaufsmengen();

		setTitle("Abrechnung für <Kundenname>");
		setSize(700, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(8, 1));

		dienstTableModel = new DienstleistungenTableModel(dienstleistungen);
		JTable dienstTable = new JTable(dienstTableModel);

		abfüllTableModel = new ProduktTableModel(abfüllProduktSortiment);
		JTable abfüllTable = new JTable(abfüllTableModel);

		zusatzTableModel = new ProduktTableModel(zusatzProduktSortiment);
		JTable zusatzTable = new JTable(zusatzTableModel);

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

		

		totalText = new JTextField(13);
		totalText.setHorizontalAlignment(JTextField.RIGHT);
		totalText.setEditable(false);
		total = berechneGesamtTotal();
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

		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Einkäufe"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(contentPanel);
		add(titlepane);

		setVisible(true);
	}

	private class AbbruchHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class AktualisiereSummeHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			total = berechneGesamtTotal();
			// System.out.println(total);
			totalText.setText(String.valueOf(total));
		}
	}

	private double berechneGesamtTotal() {

		return (dienstTableModel.berechneTeilpreis() + 
				abfüllTableModel.berechneTeilpreis() + 
				zusatzTableModel.berechneTeilpreis());
	}

	

	private void initVerkaufsmengen() {
		for (Dienstleistung d : dienstleistungen) {
			if (d.getVerkaufsMenge() != 0)
				d.setVerkaufsMenge(0);
		}

		for (Produkt p : aliste) {
			if (p.getVerkaufsMenge() != 0)
				p.setVerkaufsMenge(0);
		}

		for (Produkt p : zliste) {
			if (p.getVerkaufsMenge() != 0)
				p.setVerkaufsMenge(0);
		}
	}

	private class EinkaufAbschließenHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			einkauf = new Einkauf(); //
			dlZuEinkauf(dienstleistungen); // gekaufte DL hinzufügen
			produkteZuEinkauf(aliste); // gekaufte Abfüllmaterialien hinzufügen
			produkteZuEinkauf(zliste); // gekaufte Zusatzprodukte hinzufügen

			total = berechneGesamtTotal();
			einkauf.setSumme(total);
			einkauf.setLiterzahl(literzahl);
			kundeneinkäufe.addEinkauf(einkauf);
			System.out.println("Einkauf abgeschlossen");
			kundeneinkäufe.printKundeneinkäufe();
			Tresterabrechnung tA = new Tresterabrechnung(kundeneinkäufe);
			tA.printTresterAbrechnung();
			// initVerkaufsmengen();

			dispose();

		}
	}

	private void produkteZuEinkauf(ArrayList<Produkt> liste) {
		for (Produkt p : liste) {
			if (p.getVerkaufsMenge() > 0) {
				produktPosition = new Einkaufsposition(p.getName(),
						p.getPreis(), p.getVerkaufsMenge());
				einkauf.addEinkauf(produktPosition);
				p.printEinkaufsposition();
			}
		}
	}

	private void dlZuEinkauf(ArrayList<Dienstleistung> liste) {
		literzahl = 0;
		for (Dienstleistung d : liste) {
			if (d.getVerkaufsMenge() > 0) {
				DLposition = new Einkaufsposition(d.getName(), d.getPreis(),
						d.getVerkaufsMenge());
				einkauf.addEinkauf(DLposition);
				literzahl = literzahl + d.getVerkaufsMenge();
				d.printEinkaufsposition();
			}
		}
	}

}