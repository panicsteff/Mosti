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
		
		JLabel label;
		
		add(label = new JLabel("Nachname: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtNachname = new JTextField());
		txtNachname.setFont(txtNachname.getFont().deriveFont(16f));
		
		add(label = new JLabel("Vorname: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtVorname = new JTextField());
		txtVorname.setFont(txtVorname.getFont().deriveFont(16f));
		
		add(label = new JLabel("Straße: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtStrasse = new JTextField());
		txtStrasse.setFont(txtStrasse.getFont().deriveFont(16f));
		
		add(label = new JLabel("Hausnummer: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtHausnummer = new JTextField());
		txtHausnummer.setFont(txtHausnummer.getFont().deriveFont(16f));
	
		add(label = new JLabel("PLZ: "));
		label.setFont(label.getFont().deriveFont(16f));
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
		txtPlz.setFont(txtPlz.getFont().deriveFont(16f));
		
		add(label = new JLabel("Stadt: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtStadt = new JTextField());
		txtStadt.setFont(txtStadt.getFont().deriveFont(16f));
		
		add(label = new JLabel("Telefonnummer: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtTelefonnummer = new JTextField());
		txtTelefonnummer.setFont(txtTelefonnummer.getFont().deriveFont(16f));
		
		add(new JLabel());
		add(new JLabel());
		
		JButton okButton = new JButton("OK");
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.addActionListener(new MyOKHandler());
		add(okButton);
		
		JButton cmdButton = new JButton("Abbrechen");
		cmdButton.setFont(cmdButton.getFont().deriveFont(16f));
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
