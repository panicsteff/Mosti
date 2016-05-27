package gui.produktverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import kundenverwaltung.NullableFormatter;
import lagerverwaltung.FoFormat;
import lagerverwaltung.Produkt;

@SuppressWarnings("serial")
class ProduktBearbeitenFrame extends JDialog {

	private JTextField txtName;
	private JFormattedTextField txtMenge;
	private JFormattedTextField txtUntergrenze;
	private JFormattedTextField txtPreis;
	private JCheckBox cBoxIsAbfuellmaterial;
	private Produkt produkt;

	ProduktBearbeitenFrame(JFrame parent, Produkt p) {

		super(parent);
		this.produkt = p;

		setTitle("Produkt bearbeiten");
		setSize(250, 450);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(6, 2));

		add(new JLabel("Name:"));
		add(txtName = new JTextField(produkt.getName()));

		add(new JLabel("Einzelpreis [€]:"));
		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
		NullableFormatter nf = new NullableFormatter(nuf);
		txtPreis = new JFormattedTextField(nf);
		txtPreis.setValue(produkt.getPreis());
		add(txtPreis);

		add(new JLabel("Aktuelle Menge:"));
		add(txtMenge = new JFormattedTextField(produkt.getVorratsmenge()));

		add(new JLabel("Untergrenze"));
		add(txtUntergrenze = new JFormattedTextField(produkt.getUntergrenze()));
		
		add(new JLabel("Ist Abfüllmaterial:"));
		cBoxIsAbfuellmaterial = new JCheckBox();
		cBoxIsAbfuellmaterial.setSelected(produkt.isAbfüllmaterial());
		add(cBoxIsAbfuellmaterial);

		JButton okButton = new JButton("OK");
		add(okButton);
		okButton.addActionListener(new MyOkHandler());

		JButton abbButton = new JButton("Abbrechen");
		add(abbButton);
		abbButton.addActionListener(new MyAbbHandler());

		pack();
		setVisible(true);
	}

	private class MyOkHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			produkt.setName(txtName.getText());
			produkt.setPreis(Double.parseDouble(txtPreis.getText()));
			produkt.setVorratsmenge(Integer.parseInt(txtMenge.getText()));
			produkt.setUntergrenze(Integer.parseInt(txtUntergrenze.getText()));
			if(produkt.isAbfüllmaterial() != cBoxIsAbfuellmaterial.isSelected()){
				produkt.setAbfüllmaterial(cBoxIsAbfuellmaterial.isSelected());
				LagerVerwaltungFrame.hasChanged = true;
			}
			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}
