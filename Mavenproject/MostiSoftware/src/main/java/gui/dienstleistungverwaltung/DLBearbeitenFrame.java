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

import logik.dienstleistungverwaltung.Dienstleistung;

@SuppressWarnings("serial")
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

		JLabel name = new JLabel("Name:");
		name.setFont(name.getFont().deriveFont(16f));
		add(name);
		add(txtName = new JTextField(dienstleistung.getName()));
		txtName.setFont(txtName.getFont().deriveFont(16f));

		JLabel preis = new JLabel("Einzelpreis [€]:");
		preis.setFont(preis.getFont().deriveFont(16f));
		add(preis);
		// NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
		// NullableFormatter nf = new NullableFormatter(nuf);
		// txtPreis = new JFormattedTextField(nf);
		// txtPreis.setValue(dienstleistung.getPreis()*100); // *100, da sonst
		// fehlerhafter Wert durch CellRenderer
		// add(txtPreis);

		txtPreis = new JFormattedTextField(new DecimalFormat("0.00"));
		txtPreis.setFont(txtPreis.getFont().deriveFont(16f));
		// txtPreis =new JFormattedTextField(FoFormat.preisformat);
		txtPreis.setValue((Double) dienstleistung.getPreis());
		add(txtPreis);

		JButton okButton = new JButton("OK");
		add(okButton);
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.addActionListener(new MyOkHandler());

		JButton abbButton = new JButton("Abbrechen");
		add(abbButton);
		abbButton.setFont(abbButton.getFont().deriveFont(16f));
		abbButton.addActionListener(new MyAbbHandler());

		// pack();
		setVisible(true);
	}

	private class MyOkHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			try {
				Object preis_objekt = txtPreis.getValue();
				Double preis = Double.parseDouble(preis_objekt + "");
				dienstleistung.setName(txtName.getText());
				dienstleistung.setPreis(preis);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Bitte überprüfen Sie die Eingaben.", "Meldung",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				return;

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
