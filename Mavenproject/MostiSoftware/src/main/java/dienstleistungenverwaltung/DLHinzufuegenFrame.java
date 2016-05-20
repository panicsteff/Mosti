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

import kundenverwaltung.NullableFormatter;
import lagerverwaltung.FoFormat;
import main.Angebote;

import com.sun.glass.ui.Pixels.Format;

@SuppressWarnings("serial")
class DLHinzufuegenFrame extends JDialog {

	private JTextField txtName;
	private JFormattedTextField txtPreis;
	private Dienstleistung dienstleistung;
	private Format format;
	private Angebote a;

	DLHinzufuegenFrame(JFrame parent, Angebote a) {

		super(parent);

		this.a = a;

		setTitle("Neue Dienstleistung erstellen");
		setSize(350, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);

		setLayout(new GridLayout(3, 2));

		add(new JLabel("Name:"));
		add(txtName = new JTextField());

		add(new JLabel("Preis pro Liter [�]:"));
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
			a.addDienstleistung(dienstleistung);
			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}

