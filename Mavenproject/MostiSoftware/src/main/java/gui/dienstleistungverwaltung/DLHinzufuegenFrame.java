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

import logik.dienstleistungverwaltung.DLSortiment;
import logik.dienstleistungverwaltung.Dienstleistung;

import com.sun.glass.ui.Pixels.Format;

@SuppressWarnings("serial")
class DLHinzufuegenFrame extends JDialog {

	private JTextField txtName;
	private JFormattedTextField txtPreis;
	private Dienstleistung dienstleistung;
	private Format format;
	private DLSortiment dlSortiment;

	DLHinzufuegenFrame(JFrame parent, DLSortiment d) {

		super(parent);

		this.dlSortiment = d;

		setTitle("Neue Dienstleistung erstellen");
		setSize(350, 250);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);

		setLayout(new GridLayout(3, 2));

		JLabel name = new JLabel("Name:");
		name.setFont(name.getFont().deriveFont(16f));
		add(name);
		add(txtName = new JTextField());
		txtName.setFont(txtName.getFont().deriveFont(16f));

		JLabel preis = new JLabel("Preis pro Liter [�]:");
		preis.setFont(preis.getFont().deriveFont(16f));
		add(preis);

		txtPreis = new JFormattedTextField(new DecimalFormat("0.00"));
		txtPreis.setFont(txtPreis.getFont().deriveFont(16f));
		add(txtPreis);

		JButton okButton = new JButton("OK");
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.addActionListener(new MyOKHandler());
		add(okButton);

		JButton abbButton = new JButton("Abbrechen");
		abbButton.setFont(abbButton.getFont().deriveFont(16f));
		abbButton.addActionListener(new MyAbbHandler());
		add(abbButton);

		setVisible(true);
	}

	private class MyOKHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			try {
				Object preis_objekt = txtPreis.getValue();
				Double preis = Double.parseDouble(preis_objekt + "");

				dienstleistung = new Dienstleistung(txtName.getText(), preis, 0);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Bitte �berpr�fen Sie die Eingaben.", "Meldung",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				return;

			}
			dlSortiment.addDienstleistung(dienstleistung);
			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}
