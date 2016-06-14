package gui.kassenfunktion;

import gui.verkauf.VerkäufeFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

import persistenz.KundeDB;
import persistenz.LagerDB;
import logik.dienstleistungverwaltung.DLSortiment;
import logik.dienstleistungverwaltung.Dienstleistung;
import logik.kundenverwaltung.Kunde;
import logik.produktverwaltung.Produkt;
import logik.produktverwaltung.ProduktSortiment;
import logik.verkaufsverwaltung.Verkauf;
import logik.verkaufsverwaltung.Verkaufsposition;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

public class KassenFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable dienstTable;
	private JTable abfüllTable;
	private JTable zusatzTable;
	private ProduktTableModel zusatzTableModel;
	private ProduktTableModel abfüllTableModel;
	private DienstleistungenTableModel dienstTableModel;
	private ListSelectionModel listSelectionModel1;
	private ListSelectionModel listSelectionModel2;
	private ListSelectionModel listSelectionModel3;
	private JLabel label;
	private double total;
	private JTextField totalText;
	private ArrayList<Produkt> aliste;
	private ArrayList<Produkt> zliste;
	private ArrayList<Dienstleistung> dienstleistungen;
	private Verkauf verkauf;
	private Verkaufsposition DLposition;
	private Verkaufsposition produktPosition;
	private int literzahl;
	private Kunde kunde;
	private ArrayList<Verkaufsposition> einkaufsliste;
	private Verkaufsverwaltung vVerwaltung;
	private KundeDB kundeDB; 
	private ProduktSortiment psortiment;
	
	private TableColumnModel columnModel1;
	private TableColumnModel columnModel2;
	private TableColumnModel columnModel3;
	
	

	public KassenFrame(DLSortiment dlsortiment,ProduktSortiment psortiment,
			Verkaufsverwaltung verkaufsverwaltung, int kundenId) {
		
		this.psortiment = psortiment;
		aliste = psortiment.getAbfuellSortiment();
		zliste = psortiment.getZProduktSortiment();
		dienstleistungen = dlsortiment.getDLSortiment();
		this.vVerwaltung = verkaufsverwaltung;
		kundeDB = new KundeDB();
		
		try {
			kunde = kundeDB.einzelnenKundeLaden(kundenId);					/////////
			System.out.println("KundenID: " +kunde.getKundenID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Fehler beim Laden des Kunden");
			e.printStackTrace();
		}
		

		initVerkaufsmengen();

		setTitle("Abrechnung für " + kunde.getVorname() + " " + kunde.getNachname());
		setSize(750, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel contentPanel = new JPanel();
		//contentPanel.setLayout(new GridLayout(8, 1));
		contentPanel.setLayout(null);

		dienstTableModel = new DienstleistungenTableModel(dienstleistungen);
		dienstTable = new JTable(dienstTableModel);
		tabellenSpaltenGrößeFestlegen(dienstTable);

		abfüllTableModel = new ProduktTableModel(aliste);
		abfüllTable = new JTable(abfüllTableModel);
		tabellenSpaltenGrößeFestlegen(abfüllTable);

		zusatzTableModel = new ProduktTableModel(zliste);
		zusatzTable = new JTable(zusatzTableModel);
		tabellenSpaltenGrößeFestlegen(zusatzTable);
		
		dienstTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				markiereZellinhalt(dienstTable);	
			}
		});
		
		abfüllTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				markiereZellinhalt(abfüllTable);	
			}
		});
		
		zusatzTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				markiereZellinhalt(zusatzTable);	
			}
		});
		
		JScrollPane tableContainer1 = new JScrollPane(dienstTable);
		JScrollPane tableContainer2 = new JScrollPane(abfüllTable);
		JScrollPane tableContainer3 = new JScrollPane(zusatzTable);

		label = new JLabel("Literzahlen");
		label.setFont(label.getFont().deriveFont(18f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		label.setBounds(6, 6, 200, 20);
		contentPanel.add(label);
		tableContainer1.setBounds(6, 30, 700, 60);
		contentPanel.add(tableContainer1);

		label = new JLabel("Abfüll-Materialien");
		label.setFont(label.getFont().deriveFont(18f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		label.setBounds(6, 120, 200, 20);
		contentPanel.add(label);
		tableContainer2.setBounds(6, 144, 700, 60);
		contentPanel.add(tableContainer2);

		label = new JLabel("Weitere Produkte");
		label.setFont(label.getFont().deriveFont(18f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		label.setBounds(6, 234, 200, 20);
		contentPanel.add(label);
		tableContainer3.setBounds(6, 258, 700, 60);
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
		summePanel.setBounds(6, 340, 700, 30);
		contentPanel.add(summePanel);

		JPanel buttonPanel = new JPanel();
		JButton abschlussButton = new JButton("Einkauf abschließen");
		JButton aktualisiereSummeButton = new JButton(
				"Kostenanzeige aktualisieren");
		JButton abbrechButton = new JButton("Abbrechen");
		abbrechButton.addActionListener(new AbbruchHandler());
		aktualisiereSummeButton
				.addActionListener(new AktualisiereSummeHandler());
		abschlussButton.addActionListener(new EinkaufAbschließenHandler());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(abbrechButton);
		buttonPanel.add(aktualisiereSummeButton);
		buttonPanel.add(abschlussButton);
		buttonPanel.setBounds(6, 370, 700, 50);
		contentPanel.add(buttonPanel);
		
		listSelectionModel1 = dienstTable.getSelectionModel();
		listSelectionModel1
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel1.addListSelectionListener(new MyListSelectionListener());
		columnModel1 = dienstTable.getColumnModel();
		columnModel1.setColumnSelectionAllowed(true);
		
		listSelectionModel2 = abfüllTable.getSelectionModel();
		listSelectionModel2
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel2.addListSelectionListener(new MyListSelectionListener());
		columnModel2 = abfüllTable.getColumnModel();
		columnModel2.setColumnSelectionAllowed(true);
		
		listSelectionModel3 = zusatzTable.getSelectionModel();
		listSelectionModel3
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel3.addListSelectionListener(new MyListSelectionListener());
		columnModel3 = zusatzTable.getColumnModel();
		columnModel3.setColumnSelectionAllowed(true);

		//JScrollPane scrollpane = new JScrollPane(contentPanel);
		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Verkäufe"));
		titlepane.setLayout(new BorderLayout());
		//scrollpane.add(contentPanel);
		//titlepane.add(scrollpane);
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
	
	private class MyListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			listSelectionModel1.clearSelection();
		}
	};
	
	private void tabellenSpaltenGrößeFestlegen(JTable table){
		int spaltenanzahl = table.getColumnCount();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		for(int i = 0; i<spaltenanzahl; i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(200);
		}
	}

	private double berechneGesamtTotal() {
		return (dienstTableModel.berechneTeilpreis() + 
				abfüllTableModel.berechneTeilpreis() + 
				zusatzTableModel.berechneTeilpreis());
	}

	private class EinkaufAbschließenHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			einkaufsliste = new ArrayList<Verkaufsposition>();
			dlZuEinkauf(dienstleistungen); // gekaufte DL hinzufügen
			produkteZuEinkauf(aliste); // gekaufte Abfüllmaterialien hinzufügen
			produkteZuEinkauf(zliste); // gekaufte Zusatzprodukte hinzufügen
			psortiment.updateProdukte();

			java.util.Date datum = new Date();
			java.sql.Date d = new java.sql.Date(datum.getTime());
			System.out.println(d);
			verkauf = new Verkauf(kunde, d, einkaufsliste);
			vVerwaltung.addVerkauf(verkauf);

//			total = berechneGesamtTotal();
//			einkauf.setSumme(total);
//			einkauf.setLiterzahl(literzahl);
			
			System.out.println("Einkauf abgeschlossen");
			new VerkäufeFrame(einkaufsliste);
			
			dispose();
		}
	}
	
	private void markiereZellinhalt(JTable table){
		int row = 0;
		int col = table.getSelectedColumn();
	
		table.editCellAt(row,col); // Editiert die Zelle

		// Selektiert den Inhalt der Zelle, sodass dieser überschrieben werden kann
		Integer i = (Integer) table.getValueAt(row, col);
		//if( i == null)
		TableCellEditor cedit = table.getCellEditor(row,col);
		Component tf = (Component) cedit.getTableCellEditorComponent(table, i, true, row, col);
		if(tf instanceof JTextField) {
		JTextField tf_ = (JTextField)tf;
		tf_.selectAll();
		tf_.requestFocusInWindow();
		}
	}

	private void produkteZuEinkauf(ArrayList<Produkt> liste) {
		for (Produkt p : liste) {
			if (p.getVerkaufsMenge() > 0) {
				produktPosition = new Verkaufsposition(p.getName(),
						p.getPreis(), p.getVerkaufsMenge(), p.getLiterzahl());
				p.setVorratsmenge(p.getVorratsmenge()-p.getVerkaufsMenge());
				psortiment.updateVerkaufsmengeVonProdukt(p.getName(), p.getVorratsmenge());
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
	
	private void initVerkaufsmengen() {
		for (Dienstleistung d : dienstleistungen) {
			if (d.getLiterzahl() != 0) d.setLiterzahl(0);
		}
		for (Produkt p : aliste) {
			if (p.getVerkaufsMenge() != 0) p.setVerkaufsMenge(0);
		}
		for (Produkt p : zliste) {
			if (p.getVerkaufsMenge() != 0) p.setVerkaufsMenge(0);
		}
	}

}