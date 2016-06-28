package gui.administratorverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import logik.administratorverwaltung.AdministratorLogik;
import logik.kundenverwaltung.NullableFormatter;

public class ÜbersichtFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField mitProSchichttxt;
	private JTextField schichtenProTagtxt;
	private JFormattedTextField beginntxt;
	private JFormattedTextField endetxt;
	private JTextField zeitslottxt;
	private JTextField anzeigetxt;
	private JTextField pressdauertxt;
	private JTextField abfülldauertxt;
	private JTextField backuptxt;
	private AdministratorLogik al;

	public ÜbersichtFrame() {
		setSize(600, 700);
		setLocationRelativeTo(getParent());
		setTitle("Konfigurationswerte");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);

		al = new AdministratorLogik();

		JLabel schicht = new JLabel("Schichten");
		schicht.setFont(schicht.getFont().deriveFont(16f));
		schicht.setBounds(50, 10, 160, 30);
		add(schicht);

		JLabel mitProSchicht = new JLabel("Mitarbeiter pro Schicht:");
		mitProSchicht.setFont(mitProSchicht.getFont().deriveFont(16f));
		mitProSchicht.setBounds(10, 50, 260, 30);
		add(mitProSchicht);

		mitProSchichttxt = new JTextField(
				AdministratorLogik.getMitarbeiterProSchicht() + "");
		mitProSchichttxt.setFont(mitProSchichttxt.getFont().deriveFont(16f));
		mitProSchichttxt.setBounds(400, 50, 160, 30);
		add(mitProSchichttxt);

		JLabel schichtenProTag = new JLabel("Schichten pro Tag:");
		schichtenProTag.setFont(schichtenProTag.getFont().deriveFont(16f));
		schichtenProTag.setBounds(10, 90, 260, 30);
		add(schichtenProTag);

		schichtenProTagtxt = new JTextField(
				AdministratorLogik.getSchichtenProTag() + "");
		schichtenProTagtxt.setFont(schichtenProTagtxt.getFont().deriveFont(16f));
		schichtenProTagtxt.setBounds(400, 90, 160, 30);
		add(schichtenProTagtxt);

		JLabel termine = new JLabel("Termine");
		termine.setFont(termine.getFont().deriveFont(16f));
		termine.setBounds(50, 150, 160, 30);
		add(termine);

		JLabel arbeitsbeginn = new JLabel("Arbeitsbeginn:");
		arbeitsbeginn.setFont(arbeitsbeginn.getFont().deriveFont(16f));
		arbeitsbeginn.setBounds(10, 190, 260, 30);
		add(arbeitsbeginn);
		
		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		NullableFormatter nf = new NullableFormatter(mf);

		beginntxt = new JFormattedTextField(nf);
		beginntxt.setValue(al.terminInString(AdministratorLogik.getArbeitsbeginn()) + "");
		beginntxt.setFont(beginntxt.getFont().deriveFont(16f));
		beginntxt.setBounds(400, 190, 160, 30);
		add(beginntxt);

		JLabel arbeitsende = new JLabel("Arbeitsende");
		arbeitsende.setFont(arbeitsende.getFont().deriveFont(16f));
		arbeitsende.setBounds(10, 230, 260, 30);
		add(arbeitsende);

		endetxt = new JFormattedTextField(nf);
		endetxt.setFont(endetxt.getFont().deriveFont(16f));
		endetxt.setValue(al.terminInString(AdministratorLogik.getArbeitsende()) + "");
		endetxt.setBounds(400, 230, 160, 30);
		add(endetxt);

		JLabel zeitslot = new JLabel("Länge eines Terminslots in min: ");
		zeitslot.setFont(zeitslot.getFont().deriveFont(16f));
		zeitslot.setBounds(10, 270, 360, 30);
		add(zeitslot);

		zeitslottxt = new JTextField(AdministratorLogik.getZeitslot() + "");
		zeitslottxt.setFont(zeitslottxt.getFont().deriveFont(16f));
		zeitslottxt.setBounds(400, 270, 160, 30);
		add(zeitslottxt);

		JLabel anzeige = new JLabel("Seitenaufteilung der Termine: ");
		anzeige.setFont(anzeige.getFont().deriveFont(16f));
		anzeige.setBounds(10, 310, 260, 30);
		add(anzeige);

		anzeigetxt = new JTextField(AdministratorLogik.getAufteilung() + "");
		anzeigetxt.setFont(anzeigetxt.getFont().deriveFont(16f));
		anzeigetxt.setBounds(400, 310, 160, 30);
		add(anzeigetxt);

		JLabel pressdauer = new JLabel(
				"Presszeit für einen Zentner Obst in min: ");
		pressdauer.setFont(pressdauer.getFont().deriveFont(16f));
		pressdauer.setBounds(10, 350, 360, 30);
		add(pressdauer);

		pressdauertxt = new JTextField(AdministratorLogik.getPressdauer() + "");
		pressdauertxt.setFont(pressdauertxt.getFont().deriveFont(16f));
		pressdauertxt.setBounds(400, 350, 160, 30);
		add(pressdauertxt);

		JLabel abfülldauer = new JLabel("Abfülldauer für Flaschen in min: ");
		abfülldauer.setFont(abfülldauer.getFont().deriveFont(16f));
		abfülldauer.setBounds(10, 390, 260, 30);
		add(abfülldauer);

		abfülldauertxt = new JTextField(AdministratorLogik.getAufteilung() + "");
		abfülldauertxt.setFont(abfülldauertxt.getFont().deriveFont(16f));
		abfülldauertxt.setBounds(400, 390, 160, 30);
		add(abfülldauertxt);
		
		JLabel backup = new JLabel("Backup");
		backup.setFont(backup.getFont().deriveFont(16f));
		backup.setBounds(50, 450, 160, 30);
		add(backup);
		
		JLabel backupdatum = new JLabel("Zeitspanne zwischen zwei Backups (Tage)");
		backupdatum.setFont(backupdatum.getFont().deriveFont(16f));
		backupdatum.setBounds(10, 490, 360, 30);
		add(backupdatum);
		
		backuptxt = new JTextField(AdministratorLogik.getBackupdauer() + "");
		backuptxt.setFont(backuptxt.getFont().deriveFont(16f));
		backuptxt.setBounds(400, 490, 160, 30);
		add(backuptxt);

		JButton speichern = new JButton("Speichern");
		speichern.setFont(speichern.getFont().deriveFont(16f));
		speichern.setBounds(10, 550, 200, 40);
		add(speichern);
		speichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int anzeige, zeitslot, beginn, ende, mitProSchicht, schichtenProTag, backup;
				double pressdauer, abfülldauer;
				try {
					anzeige = Integer.parseInt(anzeigetxt.getText());
					zeitslot = Integer.parseInt(zeitslottxt.getText());
					beginn = al.terminNachInteger(beginntxt.getText());
					ende = al.terminNachInteger(endetxt.getText());
					mitProSchicht = Integer.parseInt(mitProSchichttxt.getText());
					schichtenProTag = Integer.parseInt(schichtenProTagtxt
							.getText());
					pressdauer = Double.parseDouble(pressdauertxt.getText());
					abfülldauer = Double.parseDouble(abfülldauertxt.getText());
					backup = Integer.parseInt(backuptxt.getText());
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
				if ((ende - beginn) % zeitslot != 0) {
					JOptionPane.showMessageDialog(ÜbersichtFrame.this,
							"Bei dieser Terminslotlänge kommt es zu unfertigen Terminen. "
									+ "Bitte wählen Sie eine andere Slotlänge");
					return;
				}
				if ((ende - beginn) % (zeitslot * anzeige) != 0) {
					JOptionPane
							.showMessageDialog(
									ÜbersichtFrame.this,
									"Mit dieser Anzeigeauftelung könne die Termine nicht gleichmäßig verteilt werden."
											+ " Bitte wählen Sie eine andere Aufteilung");
					return;
				}
				al.datenSpeichern(anzeige, zeitslot, beginn, ende,
						mitProSchicht, schichtenProTag, pressdauer, abfülldauer, backup);
				ÜbersichtFrame.this.dispose();
			}
		});

		JButton abbrechen = new JButton("Abbrechen");
		abbrechen.setFont(abbrechen.getFont().deriveFont(16f));
		abbrechen.setBounds(240, 550, 200, 40);
		add(abbrechen);
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ÜbersichtFrame.this.dispose();
			}
		});
		setVisible(true);

	}

}
