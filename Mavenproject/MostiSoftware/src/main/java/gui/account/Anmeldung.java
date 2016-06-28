package gui.account;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logik.account.Accountverwaltung;
import logik.mitarbeiterverwaltung.Mitarbeiter;

public class Anmeldung extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtbenutzername;
	private JPasswordField txtpasswort;
	private JButton einloggen;
	private JButton abbrechen;
	private Accountverwaltung accountverwaltung;

	public Anmeldung() {
		setTitle("Hallo bei Mosti - Anmeldung");
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 2));
		this.setLocation(700, 400);
		setLocationRelativeTo(getParent());

		accountverwaltung = new Accountverwaltung();
		
		JLabel benutzername = new JLabel("Benutzername:");
		benutzername.setFont(benutzername.getFont().deriveFont(16f));
		add(benutzername);
		txtbenutzername = new JTextField();
		txtbenutzername.setFont(txtbenutzername.getFont().deriveFont(16f));
		add(txtbenutzername);
		JLabel passwort = new JLabel("Passwort:");
		passwort.setFont(passwort.getFont().deriveFont(16f));
		add(passwort);
		txtpasswort = new JPasswordField();
		txtpasswort.setFont(txtpasswort.getFont().deriveFont(16f));
		add(txtpasswort);
		einloggen = new JButton("Einloggen");
		einloggen.setFont(einloggen.getFont().deriveFont(16f));
		add(einloggen);
		einloggen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mitarbeiter mitarbeiter;
				mitarbeiter = accountverwaltung.anmelden(
						txtbenutzername.getText(), txtpasswort.getText());


				if(mitarbeiter != null){
					boolean istAdmin = accountverwaltung.isAdmin(mitarbeiter.getBenutzername());
					new M_Startseite(istAdmin, mitarbeiter);
					dispose();
				}
			}
		});
		abbrechen = new JButton("Abbrechen");
		abbrechen.setFont(abbrechen.getFont().deriveFont(16f));
		add(abbrechen);
		abbrechen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});

		setVisible(true);
	}

}
