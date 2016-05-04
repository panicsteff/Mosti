package account;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Anmeldung extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtbenutzername;
	private JTextField txtpasswort;
	private JButton einloggen;
	private Accountverwaltung accountverwaltung;

	public Anmeldung() {
		setTitle("Hallo bei Mosti - Anmeldung");
		setSize(300, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(3, 2));

		accountverwaltung = new Accountverwaltung();
		
		add(new JLabel("Benutzername:"));
		add(txtbenutzername = new JTextField());
		add(new JLabel("Passwort"));
		add(txtpasswort = new JTextField());
		add(einloggen = new JButton("Einloggen"));
		einloggen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean anmeldestatus = false;
				anmeldestatus = accountverwaltung.mitarbeiterSuchen(
						txtbenutzername.getText(), txtpasswort.getText());

				if(anmeldestatus == true){
					//new M_Startseite();
					dispose();
				}
			}

		});

		setVisible(true);
	}
	
	public static void main (String[] avg){
		
		new Anmeldung();
	}

}
