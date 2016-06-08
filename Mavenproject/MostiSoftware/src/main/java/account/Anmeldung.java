package account;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Anmeldung extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtbenutzername;
	private JPasswordField txtpasswort;
	private JButton einloggen;
	private JButton abbrechen;
	private Accountverwaltung accountverwaltung;

	public Anmeldung() {
		setTitle("Hallo bei Mosti - Anmeldung");
		setSize(300, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(3, 2));
		this.setLocation(700, 400);

		accountverwaltung = new Accountverwaltung();
		
		add(new JLabel("Benutzername:"));
		add(txtbenutzername = new JTextField());
		add(new JLabel("Passwort"));
		add(txtpasswort = new JPasswordField());
		add(einloggen = new JButton("Einloggen"));
		einloggen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean anmeldestatus = false;
				anmeldestatus = accountverwaltung.anmelden(
						txtbenutzername.getText(), txtpasswort.getText());
				boolean istAdmin = accountverwaltung.isAdmin(txtbenutzername.getText());

				if(anmeldestatus == true){
					new M_Startseite(istAdmin);
					dispose();
				}
			}
		});
		add(abbrechen = new JButton("Abbrechen"));
		abbrechen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});

		setVisible(true);
	}
	
	public static void main (String[] avgs){
		new Anmeldung();
	}
	

}
