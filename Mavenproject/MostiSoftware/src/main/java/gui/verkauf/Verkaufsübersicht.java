package gui.verkauf;

import gui.kundenverwaltung.KundeListCellRenderer;
import gui.kundenverwaltung.KundeListModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.text.DateFormatter;

import logik.kundenverwaltung.Formats;
import logik.kundenverwaltung.Kunde;
import logik.kundenverwaltung.NullableFormatter;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

@SuppressWarnings("serial")
public class Verkaufs�bersicht extends JFrame {

	private JTextField txtKundenname;
	private JFormattedTextField txtDatum;
	private JFormattedTextField txtAnfangsdatum;
	private JFormattedTextField txtEnddatum;
	private JList listKunde;
	private Verk�ufeFrame verk�ufeframe;
	private JRadioButton buttonAlleKunden;
	private JRadioButton buttonBestimmterKunde;
	private JRadioButton buttonZeitraum;
	private JRadioButton buttonBestimmterTag;
	private JRadioButton buttonGesamterZeitraum;
	private KundeListModel model;
	private JLabel lblKundenname;
	private JScrollPane scrollpane;
	private JLabel lblDatum;
	private JLabel lblAnfangsdatum;
	private JLabel lblEnddatum;

	public Verkaufs�bersicht() {
		setTitle("�bersicht �ber Verk�ufe erstellen");
		setSize(530, 450);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		buttonAlleKunden = new JRadioButton("alle Kunden");
		buttonAlleKunden.setFont(buttonAlleKunden.getFont().deriveFont(16f));
		buttonAlleKunden.setSelected(true);
		buttonAlleKunden.setModel(new �bersichtButtonModel(true));
		buttonAlleKunden.addActionListener(new FirstButtonHandler());
		buttonAlleKunden.setBounds(6, 35, 200, 23);
		getContentPane().add(buttonAlleKunden);

		buttonBestimmterKunde = new JRadioButton("ein bestimmter Kunde");
		buttonBestimmterKunde.setFont(buttonBestimmterKunde.getFont()
				.deriveFont(16f));
		buttonBestimmterKunde.setModel(new �bersichtButtonModel(false));
		buttonBestimmterKunde.addActionListener(new FirstButtonHandler());
		buttonBestimmterKunde.setBounds(6, 61, 200, 23);
		getContentPane().add(buttonBestimmterKunde);

		lblKundenname = new JLabel("Kundenname:");
		lblKundenname.setFont(lblKundenname.getFont().deriveFont(16f));
		lblKundenname.setBounds(222, 61, 200, 19);
		getContentPane().add(lblKundenname);
		lblKundenname.setVisible(false);

		model = new KundeListModel();
		listKunde = new JList<String>(model);
		ListSelectionModel lsm = listKunde.getSelectionModel();
		lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListCellRenderer lcr = new KundeListCellRenderer();
		listKunde.setCellRenderer(lcr);
		scrollpane = new JScrollPane(listKunde);
		scrollpane.setBounds(350, 61, 150, 100);
		getContentPane().add(scrollpane);
		scrollpane.setVisible(false);

		�bersichtButtonGroup group = new �bersichtButtonGroup();
		group.add(buttonAlleKunden);
		group.add(buttonBestimmterKunde);
		group.setValue(true);

		buttonBestimmterTag = new JRadioButton("ein bestimmter Tag");
		buttonBestimmterTag.setFont(buttonBestimmterTag.getFont().deriveFont(
				16f));
		buttonBestimmterTag.setModel(new �bersichtButtonModel(true));
		buttonBestimmterTag.setSelected(true);
		buttonBestimmterTag.addActionListener(new SecondButtonHandler());
		buttonBestimmterTag.setBounds(6, 200, 200, 23);
		getContentPane().add(buttonBestimmterTag);

		buttonZeitraum = new JRadioButton("bestimmter Zeitraum");
		buttonZeitraum.setFont(buttonZeitraum.getFont().deriveFont(16f));
		buttonZeitraum.setModel(new �bersichtButtonModel(false));
		buttonZeitraum.addActionListener(new SecondButtonHandler());
		buttonZeitraum.setBounds(6, 229, 200, 23);
		getContentPane().add(buttonZeitraum);

		buttonGesamterZeitraum = new JRadioButton("gesamter Zeitraum");
		buttonGesamterZeitraum.setFont(buttonGesamterZeitraum.getFont()
				.deriveFont(16f));
		buttonGesamterZeitraum.setModel(new �bersichtButtonModel(false));
		buttonGesamterZeitraum.addActionListener(new SecondButtonHandler());
		buttonGesamterZeitraum.setBounds(6, 280, 200, 23);
		getContentPane().add(buttonGesamterZeitraum);

		�bersichtButtonGroup group2 = new �bersichtButtonGroup();
		group2.add(buttonBestimmterTag);
		group2.add(buttonZeitraum);
		group2.add(buttonGesamterZeitraum);
		group2.setValue(true);

		lblDatum = new JLabel("Datum:");
		lblDatum.setFont(lblDatum.getFont().deriveFont(16f));
		lblDatum.setBounds(222, 200, 200, 20);
		getContentPane().add(lblDatum);

		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
		NullableFormatter nf = new NullableFormatter(df);
		txtDatum = new JFormattedTextField(nf);
		txtDatum.setFont(txtDatum.getFont().deriveFont(16f));
		txtDatum.setBounds(350, 200, 150, 20);
		getContentPane().add(txtDatum);
		txtDatum.setColumns(10);

		lblAnfangsdatum = new JLabel("Anfangsdatum:");
		lblAnfangsdatum.setFont(lblAnfangsdatum.getFont().deriveFont(16f));
		lblAnfangsdatum.setBounds(222, 229, 200, 20);
		getContentPane().add(lblAnfangsdatum);
		lblAnfangsdatum.setVisible(false);

		txtAnfangsdatum = new JFormattedTextField(nf);
		txtAnfangsdatum.setFont(txtAnfangsdatum.getFont().deriveFont(16f));
		txtAnfangsdatum.setBounds(350, 229, 150, 20);
		getContentPane().add(txtAnfangsdatum);
		txtAnfangsdatum.setColumns(10);
		txtAnfangsdatum.setVisible(false);

		lblEnddatum = new JLabel("Enddatum:");
		lblEnddatum.setFont(lblEnddatum.getFont().deriveFont(16f));
		lblEnddatum.setBounds(222, 258, 200, 20);
		getContentPane().add(lblEnddatum);
		lblEnddatum.setVisible(false);

		txtEnddatum = new JFormattedTextField(nf);
		txtEnddatum.setFont(txtEnddatum.getFont().deriveFont(16f));
		txtEnddatum.setBounds(350, 258, 150, 20);
		getContentPane().add(txtEnddatum);
		txtEnddatum.setColumns(10);
		txtEnddatum.setVisible(false);

		JButton okButton = new JButton("OK");
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.setBounds(6, 370, 150, 30);
		getContentPane().add(okButton);
		okButton.addActionListener(new MyOkHandler());

		JButton abbButton = new JButton("Abbrechen");
		abbButton.setFont(abbButton.getFont().deriveFont(16f));
		abbButton.setBounds(200, 370, 150, 30);
		getContentPane().add(abbButton);
		abbButton.addActionListener(new MyAbbHandler());

		setVisible(true);
	}

	private class MyOkHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Verkaufsverwaltung verkaufsverwaltung = new Verkaufsverwaltung();

			if (buttonAlleKunden.isSelected() == true) {
				if (buttonBestimmterTag.isSelected() == true) { // alle Verk�ufe
																// an einem
																// bestimmten
																// Tag
					if (pr�feDatumsauswahl(txtDatum) == false)
						return;
					java.util.Date utilDate = (java.util.Date) txtDatum
							.getValue();
					java.sql.Date date = new java.sql.Date(utilDate.getTime());
					verk�ufeframe = new Verk�ufeFrame(
							verkaufsverwaltung.ladeTagesVerk�ufe2(date));
				}

				else if (buttonZeitraum.isSelected() == true) { // alle Verk�ufe
																// in bestimmten
																// Zeitraum
					if (pr�feDatumsauswahl(txtAnfangsdatum)
							&& pr�feDatumsauswahl(txtEnddatum) == false)
						return;
					java.util.Date utilDate = (java.util.Date) txtAnfangsdatum
							.getValue();
					java.sql.Date date1 = new java.sql.Date(utilDate.getTime());
					utilDate = (java.util.Date) txtEnddatum.getValue();
					java.sql.Date date2 = new java.sql.Date(utilDate.getTime());
					verk�ufeframe = new Verk�ufeFrame(
							verkaufsverwaltung.ladeAlleVerk�ufeZeitraum2(date1,
									date2));
				}

				else { // alle Verk�ufe insgesamt
					verk�ufeframe = new Verk�ufeFrame(
							verkaufsverwaltung.ladeAlleVerk�ufe2());
				}
			}

			else {
				if (buttonBestimmterTag.isSelected() == true) { // Verk�ufe von
																// bestimmten
																// Kunden an
																// einem
																// bestimmten
																// Tag
					int index = listKunde.getSelectedIndex();
					if (pr�feKundenauswahl(index) == false) {
						return;
					}
					Kunde kunde = model.getKunde(index);
					if (pr�feDatumsauswahl(txtDatum) == false)
						return;
					java.util.Date utilDate = (java.util.Date) txtDatum
							.getValue();
					java.sql.Date date = new java.sql.Date(utilDate.getTime());
					verk�ufeframe = new Verk�ufeFrame(
							verkaufsverwaltung.ladeKundeinkaufTag2(kunde, date));
				}

				else if (buttonZeitraum.isSelected() == true) { // Verk�ufe von
																// bestimmten
																// Kunden in
																// bestimmten
																// Zeitraum
					int index = listKunde.getSelectedIndex();

					if (pr�feKundenauswahl(index) == false) {
						return;
					}
					Kunde kunde = model.getKunde(index);
					if (pr�feDatumsauswahl(txtAnfangsdatum)
							&& pr�feDatumsauswahl(txtEnddatum) == false)
						return;

					java.util.Date utilDate = (java.util.Date) txtAnfangsdatum
							.getValue();
					java.sql.Date date1 = new java.sql.Date(utilDate.getTime());
					utilDate = (java.util.Date) txtEnddatum.getValue();
					java.sql.Date date2 = new java.sql.Date(utilDate.getTime());
					verk�ufeframe = new Verk�ufeFrame(
							verkaufsverwaltung.ladeKundeneinkaufZeitraum2(
									kunde, date1, date2));
				}

				else { // Verk�ufe von bestimmten Kunden insgesamt
					int index = listKunde.getSelectedIndex();

					if (pr�feKundenauswahl(index) == false) {
						return;
					}
					Kunde kunde = model.getKunde(index);
					verk�ufeframe = new Verk�ufeFrame(
							verkaufsverwaltung.ladeAlleEink�ufeVonKunde2(kunde));
				}
			}

			dispose();
		}
	}

	private boolean pr�feKundenauswahl(int index) {
		if (index < 0) {
			JOptionPane.showMessageDialog(this,
					"Bitte w�hlen Sie einen Kunden aus der Liste aus.",
					"Fehler", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		else
			return true;
	}

	private boolean pr�feDatumsauswahl(JFormattedTextField textfield) {
		if (textfield.getText().length() <= 0) {
			JOptionPane.showMessageDialog(this,
					"Bitte geben Sie ein Datum der Form dd.MM.yyyy ein.",
					"Fehler", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		else
			return true;
	}

	private class MyAbbHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class FirstButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (buttonBestimmterKunde.isSelected() == true) {
				lblKundenname.setVisible(true);
				scrollpane.setVisible(true);
			} else {
				lblKundenname.setVisible(false);
				scrollpane.setVisible(false);
			}
		}
	}

	private class SecondButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (buttonBestimmterTag.isSelected() == true) {
				lblDatum.setVisible(true);
				txtDatum.setVisible(true);
			} else {
				lblDatum.setVisible(false);
				txtDatum.setVisible(false);
			}

			if (buttonZeitraum.isSelected() == true) {
				lblAnfangsdatum.setVisible(true);
				lblEnddatum.setVisible(true);
				txtAnfangsdatum.setVisible(true);
				txtEnddatum.setVisible(true);
			} else {
				lblAnfangsdatum.setVisible(false);
				lblEnddatum.setVisible(false);
				txtAnfangsdatum.setVisible(false);
				txtEnddatum.setVisible(false);
			}
		}
	}

	// public static void main(String[] args) {
	// Verkaufs�bersicht window = new Verkaufs�bersicht();
	//
	// }
}
