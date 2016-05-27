package kassenfunktion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.ArrayList;




import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import kundenverwaltung.Formats;
import kundenverwaltung.Kunde;
import kundenverwaltung.KundeDB;
import lagerverwaltung.Produkt;
import lagerverwaltung.ProduktSortiment;
import verkaufsverwaltung.Verkauf;
import verkaufsverwaltung.Verkaufsposition;
import verkaufsverwaltung.Verkaufsverwaltung;
import verkaufsverwaltung.VerkäufeFrame;
import dienstleistungenverwaltung.DLSortiment;
import dienstleistungenverwaltung.Dienstleistung;

public class KassenFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProduktTableModel zusatzTableModel;
	private ProduktTableModel abfüllTableModel;
	private DienstleistungenTableModel dienstTableModel;
	private ListSelectionModel kassenSelectionModel1;
	private ListSelectionModel kassenSelectionModel2;
	private ListSelectionModel kassenSelectionModel3;
	private JLabel label;
	private double total;
	private JTextField totalText;
	private ArrayList<Produkt> aliste;
	private ArrayList<Produkt> zliste;
	private ArrayList<Dienstleistung> dienstleistungen;
	private Verkauf einkauf;
	private Verkaufsposition DLposition;
	private Verkaufsposition produktPosition;
	private int literzahl;
	private Kunde kunde;
	private ArrayList<Verkaufsposition> einkaufsliste;
	private Verkaufsverwaltung vVerwaltung;
	private KundeDB kundeDB; //nur zum Test

	public KassenFrame(DLSortiment dlsortiment,ProduktSortiment psortiment,
			Verkaufsverwaltung verkaufsverwaltung) {

		aliste = psortiment.getAbfuellSortiment();
		zliste = psortiment.getZProduktSortiment();
		dienstleistungen = dlsortiment.getDLSortiment();
		this.vVerwaltung = verkaufsverwaltung;
		
		kundeDB = new KundeDB(); //nur zum Test

		initVerkaufsmengen();

		setTitle("Abrechnung für <Kundenname>");
		setSize(700, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(8, 1));

		dienstTableModel = new DienstleistungenTableModel(dienstleistungen);
		JTable dienstTable = new JTable(dienstTableModel);

		abfüllTableModel = new ProduktTableModel(aliste);
		JTable abfüllTable = new JTable(abfüllTableModel);

		zusatzTableModel = new ProduktTableModel(zliste);
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
		
		kassenSelectionModel1 = dienstTable.getSelectionModel();
		kassenSelectionModel1
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		kassenSelectionModel2 = abfüllTable.getSelectionModel();
		kassenSelectionModel2
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		kassenSelectionModel3 = zusatzTable.getSelectionModel();
		kassenSelectionModel3
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
			if (d.getLiterzahl() != 0)
				d.setLiterzahl(0);
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
			einkaufsliste = new ArrayList<Verkaufsposition>();
			dlZuEinkauf(dienstleistungen); // gekaufte DL hinzufügen
			produkteZuEinkauf(aliste); // gekaufte Abfüllmaterialien hinzufügen
			produkteZuEinkauf(zliste); // gekaufte Zusatzprodukte hinzufügen
			new VerkäufeFrame(einkaufsliste);
			
			try {
				kunde = kundeDB.kundenLaden().get(2);
				System.out.println("KundenID: " +kunde.getKundenID());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Fehler dB");
				e.printStackTrace();
			}

			java.util.Date datum = new Date();
			java.sql.Date d = new java.sql.Date(datum.getTime());
			System.out.println(d);
			einkauf = new Verkauf(kunde, d, einkaufsliste);
			vVerwaltung.addVerkauf(einkauf);

//			total = berechneGesamtTotal();
//			einkauf.setSumme(total);
//			einkauf.setLiterzahl(literzahl);
			
			System.out.println("Einkauf abgeschlossen");
			new VerkäufeFrame(einkaufsliste);
			
			//kundeneinkäufe.printKundeneinkäufe();
			//Tresterabrechnung tA = new Tresterabrechnung(kundeneinkäufe);
			//tA.printTresterAbrechnung();
			dispose();
		}
	}

	private void produkteZuEinkauf(ArrayList<Produkt> liste) {
		for (Produkt p : liste) {
			if (p.getVerkaufsMenge() > 0) {
				produktPosition = new Verkaufsposition(p.getName(),
						p.getPreis(), p.getVerkaufsMenge(), p.getLiterzahl());
				p.setVorratsmenge(p.getVorratsmenge()-p.getVerkaufsMenge());
				//einkauf.addEinkaufsposition(produktPosition);
				einkaufsliste.add(produktPosition);
				p.printVerkaufsposition();
			}
		}
	}

	private void dlZuEinkauf(ArrayList<Dienstleistung> liste) {
		literzahl = 0;
		for (Dienstleistung d : liste) {
			if (d.getLiterzahl() > 0) {
				DLposition = new Verkaufsposition(d.getName(), d.getPreis(),
						d.getVerkaufsMenge(), d.getLiterzahl()
						);
				//einkauf.addEinkaufsposition(DLposition);
				einkaufsliste.add(DLposition);
				literzahl = literzahl + d.getLiterzahl();
				d.printVerkaufsposition();
			}
		}
	}

}