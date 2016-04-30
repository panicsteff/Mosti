package lagerverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import kundenverwaltung.Formats;
import kundenverwaltung.NullableFormatter;
import Dienstleistung_Produkt.Produkt;

import com.sun.glass.ui.Pixels.Format;

public class ProduktBearbeitenDialog extends JDialog {

	private JTextField txtName;
	private JFormattedTextField txtMenge;
	private JFormattedTextField txtUntergrenze;
	private JFormattedTextField txtPreis;
	private Produkt produkt;
	private Format format;

	public ProduktBearbeitenDialog(JFrame parent, Produkt p) {

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
		add(txtMenge = new JFormattedTextField(produkt.getMenge()));

		add(new JLabel("Untergrenze"));
		add(txtUntergrenze = new JFormattedTextField(produkt.getUntergrenze()));

		JButton okButton = new JButton("OK");
		add(okButton);
		okButton.addActionListener(new MyOkHandler());

		JButton abbButton = new JButton("Abbrechen");
		add(abbButton);
		abbButton.addActionListener(new MyAbbHandler());

		pack();
		setVisible(true);
	}

	class MyOkHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			produkt.setName(txtName.getText());
			produkt.setPreis(Double.parseDouble(txtPreis.getText()));
			produkt.setMenge(Integer.parseInt(txtMenge.getText()));
			produkt.setVerkaufsMenge(Integer.parseInt(txtMenge.getText()));

			dispose();
		}
	}

	class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}
