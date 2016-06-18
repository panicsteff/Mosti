package gui.trester;

import gui.kundenverwaltung.KundeListCellRenderer;
import gui.kundenverwaltung.KundeListModel;
import gui.verkauf.Verkaufsübersicht;


import gui.verkauf.VerkäufeFrame;
import gui.verkauf.ÜbersichtButtonGroup;
import gui.verkauf.ÜbersichtButtonModel;

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
import logik.trester.Tresterverwaltung;


@SuppressWarnings("serial")
public class TresterübersichtFrame  extends JFrame {

	private JTextField txtKundenname;
	private JFormattedTextField txtDatum;
	private JFormattedTextField txtAnfangsdatum;
	private JFormattedTextField txtEnddatum;
	private JList listKunde;
	private TresterFrame tresterframe;
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

	public TresterübersichtFrame() {
		setTitle("Übersicht über Trester erstellen");
		setSize(530, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		buttonAlleKunden = new JRadioButton("alle Kunden");
		buttonAlleKunden.setSelected(true);
		buttonAlleKunden.setModel(new ÜbersichtButtonModel(true));
		buttonAlleKunden.addActionListener(new FirstButtonHandler());
		buttonAlleKunden.setBounds(6, 35, 150, 23);
		getContentPane().add(buttonAlleKunden);

		buttonBestimmterKunde = new JRadioButton("ein bestimmter Kunde");
		buttonBestimmterKunde.setModel(new ÜbersichtButtonModel(false));
		buttonBestimmterKunde.addActionListener(new FirstButtonHandler());
		buttonBestimmterKunde.setBounds(6, 61, 150, 23);
		getContentPane().add(buttonBestimmterKunde);

		lblKundenname = new JLabel("Kundenname:");
		lblKundenname.setBounds(222, 61, 150, 19);
		getContentPane().add(lblKundenname);
		lblKundenname.setVisible(false);

		model = new KundeListModel();
		listKunde = new JList<String>(model);
		ListSelectionModel lsm = listKunde.getSelectionModel();
		lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListCellRenderer lcr = new KundeListCellRenderer();
		listKunde.setCellRenderer(lcr);
		scrollpane = new JScrollPane(listKunde);
		scrollpane.setBounds(330, 61, 150, 100);
		getContentPane().add(scrollpane);
		scrollpane.setVisible(false);

		ÜbersichtButtonGroup group = new ÜbersichtButtonGroup();
		group.add(buttonAlleKunden);
		group.add(buttonBestimmterKunde);
		group.setValue(true);

		buttonBestimmterTag = new JRadioButton("ein bestimmter Tag");
		buttonBestimmterTag.setModel(new ÜbersichtButtonModel(true));
		buttonBestimmterTag.setSelected(true);
		buttonBestimmterTag.addActionListener(new SecondButtonHandler());
		buttonBestimmterTag.setBounds(6, 200, 150, 23);
		getContentPane().add(buttonBestimmterTag);

		buttonZeitraum = new JRadioButton("bestimmter Zeitraum");
		buttonZeitraum.setModel(new ÜbersichtButtonModel(false));
		buttonZeitraum.addActionListener(new SecondButtonHandler());
		buttonZeitraum.setBounds(6, 229, 150, 23);
		getContentPane().add(buttonZeitraum);

		buttonGesamterZeitraum = new JRadioButton("gesamter Zeitraum");
		buttonGesamterZeitraum.setModel(new ÜbersichtButtonModel(false));
		buttonGesamterZeitraum.addActionListener(new SecondButtonHandler());
		buttonGesamterZeitraum.setBounds(6, 280, 150, 23);
		getContentPane().add(buttonGesamterZeitraum);

		ÜbersichtButtonGroup group2 = new ÜbersichtButtonGroup();
		group2.add(buttonBestimmterTag);
		group2.add(buttonZeitraum);
		group2.add(buttonGesamterZeitraum);
		group2.setValue(true);

		lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(222, 200, 150, 14);
		getContentPane().add(lblDatum);

		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
		NullableFormatter nf = new NullableFormatter(df);
		txtDatum = new JFormattedTextField(nf);
		txtDatum.setBounds(330, 200, 86, 20);
		getContentPane().add(txtDatum);
		txtDatum.setColumns(10);

		lblAnfangsdatum = new JLabel("Anfangsdatum:");
		lblAnfangsdatum.setBounds(222, 229, 150, 14);
		getContentPane().add(lblAnfangsdatum);
		lblAnfangsdatum.setVisible(false);

		txtAnfangsdatum = new JFormattedTextField(nf);
		txtAnfangsdatum.setBounds(330, 229, 86, 20);
		getContentPane().add(txtAnfangsdatum);
		txtAnfangsdatum.setColumns(10);
		txtAnfangsdatum.setVisible(false);

		lblEnddatum = new JLabel("Enddatum:");
		lblEnddatum.setBounds(222, 258, 150, 14);
		getContentPane().add(lblEnddatum);
		lblEnddatum.setVisible(false);

		txtEnddatum = new JFormattedTextField(nf);
		txtEnddatum.setBounds(330, 258, 86, 20);
		getContentPane().add(txtEnddatum);
		txtEnddatum.setColumns(10);
		txtEnddatum.setVisible(false);

		JButton okButton = new JButton("OK");
		okButton.setBounds(6, 370, 150, 30);
		getContentPane().add(okButton);
		okButton.addActionListener(new MyOkTresterHandler());

		JButton abbButton = new JButton("Abbrechen");
		abbButton.setBounds(200, 370, 150, 30);
		getContentPane().add(abbButton);
		abbButton.addActionListener(new MyAbbHandler());

		setVisible(true);
		
	}

	private class MyOkTresterHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Tresterverwaltung tresterverwaltung = new Tresterverwaltung();

			if (buttonAlleKunden.isSelected() == true) {
				if (buttonBestimmterTag.isSelected() == true) { // alle Verkäufe
																// an einem
																// bestimmten
																// Tag
					if(prüfeDatumsauswahl(txtDatum) == false)
						return;
					java.util.Date utilDate = (java.util.Date) txtDatum
							.getValue();
					java.sql.Date date = new java.sql.Date(utilDate.getTime());
					tresterframe = new TresterFrame(
							tresterverwaltung.ladeTagesTresterVerkäufe(date));
				}

				else if (buttonZeitraum.isSelected() == true) { // alle Verkäufe
																// in bestimmten
																// Zeitraum
					if(prüfeDatumsauswahl(txtAnfangsdatum) && prüfeDatumsauswahl(txtEnddatum) == false)
						return;
					java.util.Date utilDate = (java.util.Date) txtAnfangsdatum
							.getValue();
					java.sql.Date date1 = new java.sql.Date(utilDate.getTime());
					utilDate = (java.util.Date) txtEnddatum.getValue();
					java.sql.Date date2 = new java.sql.Date(utilDate.getTime());
					tresterframe = new TresterFrame(
							tresterverwaltung.ladeAlleTresterverkäufeZeitraum(date1,
									date2));
				}

				else { // alle Verkäufe insgesamt
					tresterframe = new TresterFrame(
							tresterverwaltung.ladeAlleTresterverkäufe());
				}
			}

			else {
				if (buttonBestimmterTag.isSelected() == true) { // Verkäufe von
																// bestimmten
																// Kunden an
																// einem
																// bestimmten
																// Tag
					int index = listKunde.getSelectedIndex();
					if (prüfeKundenauswahl(index) == false) {
						return;
					}
					Kunde kunde = model.getKunde(index);
					if(prüfeDatumsauswahl(txtDatum) == false)
						return;
					java.util.Date utilDate = (java.util.Date) txtDatum
							.getValue();
					java.sql.Date date = new java.sql.Date(utilDate.getTime());
					tresterframe = new TresterFrame(tresterverwaltung
							.ladeKundentrestereinkaufTag(kunde, date));
				}

				else if (buttonZeitraum.isSelected() == true) { // Verkäufe von
																// bestimmten
																// Kunden in
																// bestimmten
																// Zeitraum
					int index = listKunde.getSelectedIndex();

					if (prüfeKundenauswahl(index) == false) {
						return;
					}
					Kunde kunde = model.getKunde(index);
					if(prüfeDatumsauswahl(txtAnfangsdatum) && prüfeDatumsauswahl(txtEnddatum) == false)
						return;
					 
					java.util.Date utilDate = (java.util.Date) txtAnfangsdatum
							.getValue();
					java.sql.Date date1 = new java.sql.Date(utilDate.getTime());
					utilDate = (java.util.Date) txtEnddatum.getValue();
					java.sql.Date date2 = new java.sql.Date(utilDate.getTime());
					tresterframe = new TresterFrame(
							tresterverwaltung.ladeKundenTrestereinkaufZeitraum(kunde,
									date1, date2));
				}

				else { // Verkäufe von bestimmten Kunden insgesamt
					int index = listKunde.getSelectedIndex();

					if (prüfeKundenauswahl(index) == false) {
						return;
					}
					Kunde kunde = model.getKunde(index);
					tresterframe = new TresterFrame(
							tresterverwaltung.ladeAlleTresterEinkäufeVonKunde(kunde));
				}
			}

			dispose();
		}
	}

	private boolean prüfeKundenauswahl(int index) {
		if (index < 0) {
			JOptionPane.showMessageDialog(this,
					"Bitte wählen Sie einen Kunden aus der Liste aus.",
					"Fehler", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		else
			return true;
	}
	
	private boolean prüfeDatumsauswahl(JFormattedTextField textfield) {
		if (textfield.getText().length()<= 0) {
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

	public static void main(String[] args) {
		new TresterübersichtFrame();

	}
}

