package gui.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logik.account.Accountverwaltung;
import logik.mitarbeiterverwaltung.Mitarbeiter;

public class PasswortBearbeitenFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPasswordField alttxt;
	private JPasswordField neutxt;
	private JPasswordField wiedertxt;
	private Accountverwaltung aVerwaltung;
	private Mitarbeiter mit;
	
	public PasswortBearbeitenFrame(Mitarbeiter m){
		setSize(480, 300);
		setTitle("Passwort ändern");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		aVerwaltung = new Accountverwaltung();
		mit = m;
		
		JLabel alt = new JLabel("Altes Passwort:");
		alt.setFont(alt.getFont().deriveFont(16f));
		alt.setBounds(10, 10, 190, 40);
		add(alt);
		
		alttxt = new JPasswordField();
		alttxt.setBounds(250, 10, 190, 40);
		alttxt.setFont(alttxt.getFont().deriveFont(16f));
		add(alttxt);
		
		JLabel neu = new JLabel("Neues Passwort:");
		neu.setFont(neu.getFont().deriveFont(16f));
		neu.setBounds(10, 60, 190, 40);
		add(neu);
		
		neutxt = new JPasswordField();
		neutxt.setFont(neutxt.getFont().deriveFont(16f));
		neutxt.setBounds(250, 60, 190, 40);
		add(neutxt);
		
		JLabel wieder = new JLabel("Neues Passwort wiederholen:");
		wieder.setFont(wieder.getFont().deriveFont(16f));
		wieder.setBounds(10, 110, 240, 40);
		add(wieder);
		
		wiedertxt = new JPasswordField();
		wiedertxt.setFont(wiedertxt.getFont().deriveFont(16f));
		wiedertxt.setBounds(250, 110, 190, 40);
		add(wiedertxt);
		
		JButton speichern = new JButton("Speichern");
		speichern.setBounds(10, 170, 230, 40);
		speichern.setFont(speichern.getFont().deriveFont(16f));
		add(speichern);
		speichern.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				boolean result = aVerwaltung.prüfePasswort(alttxt.getText(), neutxt.getText(), wiedertxt.getText(), mit.getMitarbeiterID());
				if(result == true){
					PasswortBearbeitenFrame.this.dispose();
				}
			}
		});
		
		JButton abbrechen = new JButton("Abbrechen");
		abbrechen.setBounds(231, 170, 230, 40);
		abbrechen.setFont(abbrechen.getFont().deriveFont(16f));
		add(abbrechen);
		abbrechen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PasswortBearbeitenFrame.this.dispose();
			}
		});
		
		
		setVisible(true);
	}
	
	public static void main (String[] avgs){
		Mitarbeiter m = new Mitarbeiter();
		m.setMitarbeiterID(2);
		m.setBenutzername("Helmi");
		new PasswortBearbeitenFrame(m);
	}

}
