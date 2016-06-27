package gui.dienstleistungverwaltung;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import logik.dienstleistungverwaltung.Dienstleistung;
import logik.produktverwaltung.FoFormat;


class DLBearbeitenFrame extends JDialog {

	private JFormattedTextField txtPreis;
	private Dienstleistung dienstleistung;
	private JTextField txtName;

	DLBearbeitenFrame(JFrame parent, Dienstleistung d) {

		super(parent);
		this.dienstleistung = d;

		setTitle("Dienstleistung bearbeiten");
		setSize(350, 250);
		setLocationRelativeTo(getParent());
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(3, 2));

		add(new JLabel("Name:"));
		add(txtName = new JTextField(dienstleistung.getName()));

		add(new JLabel("Einzelpreis [€]:"));
//		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
//		NullableFormatter nf = new NullableFormatter(nuf);
//		txtPreis = new JFormattedTextField(nf);
//		txtPreis.setValue(dienstleistung.getPreis()*100); // *100, da sonst fehlerhafter Wert durch CellRenderer
//		add(txtPreis);
		
		//txtPreis =new JFormattedTextField(new DecimalFormat("0.##"));
		txtPreis =new JFormattedTextField(FoFormat.preisformat);
		txtPreis.setValue((Double)dienstleistung.getPreis());
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
			try{
			Object preis_objekt = txtPreis.getValue();
			Double preis = Double.parseDouble(preis_objekt+"");
			dienstleistung.setName(txtName.getText());
			dienstleistung.setPreis(preis);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,
					"Bitte überprüfen Sie die Eingaben.", "Meldung",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return;
			
		}
			//dienstleistung.setPreis((Double)txtPreis.getValue());
			//dienstleistung.setPreis(Double.parseDouble(txtPreis.getText()));

			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}

