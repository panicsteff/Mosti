package gui.trester;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logik.trester.Tresterverwaltung;

@SuppressWarnings("serial")
public class TresterpreisBearbeitenFrame extends JFrame {

	private JFormattedTextField txtPreis;
	private JLabel label;
	private Tresterverwaltung tv;

	public TresterpreisBearbeitenFrame(Tresterverwaltung tv) {

		this.tv = tv;
		setTitle("Tresterpreis verwalten");
		setSize(700, 200);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(3, 2));
		label = new JLabel("Aktueller Tresterpreis pro 1000 L Saft [€]: ");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);
		JFormattedTextField textfield = new JFormattedTextField(
				new DecimalFormat("0.00"));
		textfield.setValue(Math.round(tv.getPreisPro1000L() * 100.0) / 100.0);
		textfield.setFont(textfield.getFont().deriveFont(16f));
		add(textfield);
		textfield.setEditable(false);

		label = new JLabel("Neuer Tresterpreis pro 1000 L [€]:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

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
				tv.setPreisPro1000L(preis);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Bitte überprüfen Sie die Eingaben.", "Meldung",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				return;
			}
			System.out.println("Preis pro 1000 L: " + tv.getPreisPro1000L());
			// Tresterabrechnung ta = new Tresterabrechnung(tv, 2000);
			// System.out.println("Tresterpreis: "+ta.getPreis());
			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();

		}

	}

	// public static void main(String[] args){
	// TresterFrame t = new TresterFrame(new Tresterverwaltung());
	//
	// }

}
