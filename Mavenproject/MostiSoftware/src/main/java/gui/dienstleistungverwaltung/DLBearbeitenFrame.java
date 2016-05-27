package gui.dienstleistungverwaltung;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import dienstleistungenverwaltung.Dienstleistung;
import kundenverwaltung.NullableFormatter;
import lagerverwaltung.FoFormat;


class DLBearbeitenFrame extends JDialog {

	private JFormattedTextField txtPreis;
	private Dienstleistung dienstleistung;
	private JTextField txtName;

	DLBearbeitenFrame(JFrame parent, Dienstleistung d) {

		super(parent);
		this.dienstleistung = d;

		setTitle("Produkt bearbeiten");
		setSize(350, 250);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(3, 2));

		add(new JLabel("Name:"));
		add(txtName = new JTextField(dienstleistung.getName()));

		add(new JLabel("Einzelpreis [€]:"));
		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
		NullableFormatter nf = new NullableFormatter(nuf);
		txtPreis = new JFormattedTextField(nf);
		txtPreis.setValue(dienstleistung.getPreis());
		add(txtPreis);

		JButton okButton = new JButton("OK");
		add(okButton);
		okButton.addActionListener(new MyOkHandler());

		JButton abbButton = new JButton("Abbrechen");
		add(abbButton);
		abbButton.addActionListener(new MyAbbHandler());

		//pack();
		setVisible(true);
	}

	private class MyOkHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			dienstleistung.setName(txtName.getText());
			dienstleistung.setPreis(Double.parseDouble(txtPreis.getText()));

			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}

