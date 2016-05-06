package dienstleistungenverwaltung;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import verkaufsverwaltung.Dienstleistung;
import kundenverwaltung.NullableFormatter;
import lagerverwaltung.FoFormat;

import com.sun.glass.ui.Pixels.Format;

class DLHinzufuegenDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JFormattedTextField txtPreis;
	private Dienstleistung dienstleistung;
	private List<Dienstleistung> liste;
	private Format format;

	public DLHinzufuegenDialog(JFrame parent, List<Dienstleistung> auflistung) {

		super(parent);

		this.liste = auflistung;

		setTitle("Neue Dienstleistung erstellen");
		setSize(350, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);

		setLayout(new GridLayout(3, 2));

		add(new JLabel("Name:"));
		add(txtName = new JTextField());

		add(new JLabel("Preis pro Liter [€]:"));
		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
		NullableFormatter nf = new NullableFormatter(nuf);
		add(txtPreis = new JFormattedTextField(nf));

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new MyOKHandler());
		add(okButton);

		JButton abbButton = new JButton("Abbrechen");
		abbButton.addActionListener(new MyAbbHandler());
		add(abbButton);

		setVisible(true);
	}

	private class MyOKHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			dienstleistung = new Dienstleistung(txtName.getText(), Double.parseDouble(txtPreis.getText()),0);
			liste.add(dienstleistung);
			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}

