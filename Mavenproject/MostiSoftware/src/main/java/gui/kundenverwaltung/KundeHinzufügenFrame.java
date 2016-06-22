package gui.kundenverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import persistenz.KundeDB;
import logik.kundenverwaltung.Formats;
import logik.kundenverwaltung.Kunde;
import logik.kundenverwaltung.NullableFormatter;



public class KundeHinzufügenFrame extends JDialog{

private static final long serialVersionUID = 1L;
	
	private JTextField txtNachname;
	private JTextField txtVorname;
	private JTextField txtStrasse;
	private JTextField txtHausnummer;
	private JTextField txtPlz;
	private JTextField txtStadt;
	private JTextField txtTelefonnummer;
	private Kunde kunde;
	private List<Kunde> liste;
	private KundeDB kdb = new KundeDB();
	
	
	public KundeHinzufügenFrame (JFrame parent, List<Kunde> auflistung){
		super(parent);
		
		liste = auflistung;
		setModal(true);
		
		
		setTitle("Neuen Kunden hinzufügen");
		setSize(350,250);
		setLocationRelativeTo(getParent());
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
		add(txtPlz = new JFormattedTextField(ff));
		
		add(new JLabel("Stadt: "));
		add(txtStadt = new JTextField());
		
		add(new JLabel("Telefonnummer: "));
		add(txtTelefonnummer = new JTextField());
		
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
	
	private class MyOKHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0){
			kunde = new Kunde(txtNachname.getText(), txtVorname.getText(), 
										  txtStrasse.getText(), txtHausnummer.getText(), txtPlz.getText(), txtStadt.getText(), txtTelefonnummer.getText(), 0);
			if(liste != null){
				liste.add(kunde);
			}
			kdb.kundeEinfügen(kunde);
			
			dispose();
		}
	}
	
	private class MyCancelHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}
	
}
