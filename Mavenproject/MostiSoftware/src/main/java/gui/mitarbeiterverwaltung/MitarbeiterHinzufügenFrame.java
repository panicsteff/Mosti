package gui.mitarbeiterverwaltung;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import logik.kundenverwaltung.Formats;
import logik.kundenverwaltung.NullableFormatter;
import logik.mitarbeiterverwaltung.Mitarbeiter;

import org.apache.commons.codec.binary.Base64;

import persistenz.MitarbeiterDB;

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
	private ArrayList<Mitarbeiter> liste;
	private MitarbeiterDB mdb =  new MitarbeiterDB();
	

	MitarbeiterHinzufügenFrame (JFrame parent, ArrayList<Mitarbeiter> auflistung){
		super(parent);
		
		liste = auflistung;
		setModal(true);
		
		
		setTitle("Neuen Mitarbeiter hinzufügen");
		setSize(350,250);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(new GridLayout(10,2));
		
		add(new JLabel("Nachname: "));
		add(txtNachname = new JTextField());
		
		add(new JLabel("Vorname: "));
		add(txtVorname = new JTextField());
		
		add(new JLabel("Straße: "));
		add(txtStrasse = new JTextField());
		
		add(new JLabel("Hausnummer: "));
		add(txtHausnummer = new JTextField());
		
		
		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
		NullableFormatter nf = new NullableFormatter(df);
		
		MaskFormatter mf = null;
		try{
			mf = new MaskFormatter("#####");
		}
		catch(ParseException e){
			System.out.println(e);
			}
		
		NullableFormatter ff = new NullableFormatter(mf);
		add(new JLabel("PLZ: "));
		add(txtPlz = new JFormattedTextField(ff));
		
		add(new JLabel("Stadt: "));
		add(txtStadt = new JTextField());
		
		add(new JLabel("Telefonnummer: "));
		add(txtTelefonnummer = new JTextField());
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2,1));
		add(jp);
		
		jp.add(new JLabel("Benutzername:"));
		add(txtBenutzername = new JTextField());
		JLabel jl = new JLabel("(Bitte nur Kleinbuchstaben verwenden!)");
		jp.add(jl);
		jl.setFont(new Font( "Dialog", Font.BOLD, 10));
	
		add(new JLabel());
		add(new JLabel());
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new MyOKHandler());
		add(okButton);
		
		JButton cmdButton = new JButton("Abbrechen");
		cmdButton.addActionListener(new MyCancelHandler());
		add(cmdButton);
		
		pack();
		setVisible(true);
	}
	
	private class MyOKHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0){
			
			if(benutzernamePrüfen(txtBenutzername.getText()) == false){
				JOptionPane.showMessageDialog(MitarbeiterHinzufügenFrame.this, "Benutzername ist bereits vorhanden. Bitte wählen Se einen anderen");
				return;
			}
			
			//String passwort = new String(Base64.encodeBase64(txtBenutzername.getText().getBytes()));
			mitarbeiter = new Mitarbeiter(txtNachname.getText(), txtVorname.getText(), 
										  txtStrasse.getText(), txtHausnummer.getText(), 
										  txtPlz.getText(), txtStadt.getText(), txtTelefonnummer.getText(),
										  0, txtBenutzername.getText().toLowerCase(), new String(Base64.encodeBase64(txtBenutzername.getText().getBytes())));
			
			
			//mitarbeiter.setPasswort(mitarbeiter.getBenutzername());
			JOptionPane.showMessageDialog(MitarbeiterHinzufügenFrame.this, "Benutzername: " + mitarbeiter.getBenutzername() + "\n Passwort: " + mitarbeiter.getBenutzername());
		
			
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
	
	private boolean benutzernamePrüfen(String benutzername){
		boolean frei = true;
		frei = mdb.benutzernamenSuchen(benutzername);
		return frei;
	}
	
}
