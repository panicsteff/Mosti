package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import account.Accountverwaltung;

public class Anmeldung extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtbenutzername;
	private JTextField txtpasswort;
	private JButton einloggen;

	public Anmeldung() {
		setTitle("Hallo bei Mosti - Anmeldung");
		setSize(500, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(3, 2));

		add(new JLabel("Benutzername:"));
		add(txtbenutzername = new JTextField());
		add(new JLabel("Passwort"));
		add(txtpasswort = new JTextField());
		add(einloggen = new JButton("Einloggen"));
		einloggen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean anmeldestatus = false;
				anmeldestatus = Accountverwaltung.benutzernamenSuchen(
						txtbenutzername.getText(), txtpasswort.getText());

				if (anmeldestatus == false) {
					JOptionPane.showMessageDialog(Anmeldung.this,
							"Der Benutzername ist nicht vorhanden");
				}
			}

		});

		setVisible(true);
	}

}
