package mitarbeiterverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MitarbeiterHinzufügenFrame extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNachname;
	private JTextField txtVorname;
	private JTextField txtStrasse;
	private JTextField txtHausnummer;
	private JTextField txtPlz;
	private JTextField txtStadt;
	private JTextField txtTelefonnummer;
	private JTextField txtBenutzername;
	private Mitarbeiter mitarbeiter;
	private List<Mitarbeiter> liste;
	private MitarbeiterDB mdb =  new MitarbeiterDB();
	
	
	
	

	MitarbeiterHinzufügenFrame (JFrame parent, List<Mitarbeiter> auflistung){
		super(parent);
		
		liste = auflistung;
		setModal(true);
		
		
		setTitle("Neuen Mitarbeiter hinzufügen");
		setSize(350,250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(new GridLayout(9,2));
		
		add(new JLabel("Nachname: "));
		add(txtNachname = new JTextField());
		
		add(new JLabel("Vorname: "));
		add(txtVorname = new JTextField());
		
		add(new JLabel("Straße: "));
		add(txtStrasse = new JTextField());
		
		add(new JLabel("Hausnummer: "));
		add(txtHausnummer = new JTextField());
		
		add(new JLabel("PLZ: "));
		add(txtPlz = new JTextField());
		
		add(new JLabel("Stadt: "));
		add(txtStadt = new JTextField());
		
		add(new JLabel("Telefonnummer: "));
		add(txtTelefonnummer = new JTextField());
		
		add(new JLabel("Benutzername: "));
		add(txtBenutzername = new JTextField());
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new MyOKHandler());
		add(okButton);
		
		JButton cmdButton = new JButton("Abbrechen");
		cmdButton.addActionListener(new MyCancelHandler());
		add(cmdButton);
		
		pack();
		setVisible(true);
	}
	
	private class MyOKHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0){
			mitarbeiter = new Mitarbeiter(txtNachname.getText(), txtVorname.getText(), 
										  txtStrasse.getText(), txtHausnummer.getText(), 
										  txtPlz.getText(), txtStadt.getText(), txtTelefonnummer.getText(),
										  0, txtBenutzername.getText());
			liste.add(mitarbeiter);
			mdb.mitarbeiterEinfügen(mitarbeiter);
			dispose();
		}
	}
	
	private class MyCancelHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}
	
}
