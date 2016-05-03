package dienstleistungenverwaltung;


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

import kundenverwaltung.NullableFormatter;
import lagerverwaltung.FoFormat;

import com.sun.glass.ui.Pixels.Format;

import dienstleistungProdukt.Dienstleistung;


@SuppressWarnings("serial")
public class DLBearbeitenDialog extends JDialog {

	private JTextField txtName;
	private JFormattedTextField txtPreis;
	private Dienstleistung dienstleistung;
	private Format format;

	public DLBearbeitenDialog(JFrame parent, Dienstleistung d) {

		super(parent);
		this.dienstleistung = d;

		setTitle("Produkt bearbeiten");
		setSize(250, 450);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(6, 2));

		add(new JLabel("Name:"));
		add(txtName = new JTextField(dienstleistung.getName()));

		add(new JLabel("Einzelpreis [�]:"));
		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
		NullableFormatter nf = new NullableFormatter(nuf);
		txtPreis = new JFormattedTextField(nf);
		txtPreis.setValue(dienstleistung.getPreisProLiter());
		add(txtPreis);

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
			dienstleistung.setName(txtName.getText());
			dienstleistung.setPreisProLiter(Double.parseDouble(txtPreis.getText()));

			dispose();
		}
	}

	class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}
