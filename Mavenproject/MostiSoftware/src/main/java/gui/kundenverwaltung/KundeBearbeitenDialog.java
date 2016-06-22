package gui.kundenverwaltung;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import logik.kundenverwaltung.Formats;
import logik.kundenverwaltung.Kunde;
import logik.kundenverwaltung.NullableFormatter;


public class KundeBearbeitenDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;

	class MyOkHandler implements ActionListener{
		private String beautify(String s){
			if(s == null){
				return null;
			}
			s = s.trim();
			if(s.trim().length() == 0){
				return null;
			}
			return s;
		}
		
		public void actionPerformed(ActionEvent e){
			int antwort = JOptionPane.showConfirmDialog(KundeBearbeitenDialog.this, "Wollen Sie wirklich speichern?");
			if(antwort == JOptionPane.OK_OPTION){
				kunde.setNachname(beautify(txtNachname.getText()));
				kunde.setVorname(beautify(txtVorname.getText()));
				kunde.setStrasse(beautify(txtStrasse.getText()));
				kunde.setHausnummer(beautify(txtHausnummer.getText()));
				kunde.setPlz((String)txtPlz.getValue());
				kunde.setWohnort(beautify(txtWohnort.getText()));
				kunde.setTel(beautify(txtTel.getText()));
				dispose();
			}
			if(antwort == JOptionPane.NO_OPTION){
				dispose();
			}
			if(antwort == JOptionPane.CANCEL_OPTION){
				;
			}
			if(antwort == JOptionPane.CLOSED_OPTION){
				;
			}
			
		}
	}
	
	class MyCancelHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}
	
	
	
	private Kunde kunde;
	
	private JTextField txtNachname;
	private JTextField txtVorname;
	private JTextField txtStrasse;
	private JTextField txtHausnummer;
	private JFormattedTextField txtPlz;
	private JTextField txtWohnort;
	private JTextField txtTel;
	
	public KundeBearbeitenDialog(JFrame parent, Kunde kunde){
		super(parent);
		this.kunde = kunde;
		
		setModal(true);
		setTitle("Kunde bearbeiten");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(500,400);
		setLocationRelativeTo(getParent());
		
		setLayout(new GridLayout(9,2));
		
		JLabel  label;
		
		label = new JLabel("Nachname:");
		add(label);
		
		txtNachname = new JTextField(kunde.getNachname());
		add(txtNachname);
		
		label = new JLabel("Vorname:");
		add(label);
		
		txtVorname = new JTextField(kunde.getVorname());
		add(txtVorname);
	
	
		label = new JLabel("Stra�e:");
		add(label);
		
		txtStrasse = new JTextField(kunde.getStrasse());
		add(txtStrasse);
		
		label = new JLabel("Hausnummer:");
		add(label);
		
		txtHausnummer = new JTextField(kunde.getHausnummer());
		add(txtHausnummer);
		
		label = new JLabel("Plz:");
		add(label);
		
		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
		NullableFormatter nf = new NullableFormatter(df);
		
		MaskFormatter mf = null;
		try{
			mf = new MaskFormatter("#####");
		}
		catch(ParseException e){
			System.out.println(e);
			
		}
		nf = new NullableFormatter(mf);
		txtPlz = new JFormattedTextField(nf);
		txtPlz.setValue(kunde.getPlz());
		add(txtPlz);
		
		label = new JLabel("Ort:");
		add(label);
		
		txtWohnort = new JTextField(kunde.getWohnort());
		add(txtWohnort);
		
		label = new JLabel("Telefonnummer:");
		add(label);
		
		txtTel = new JTextField(kunde.getTel());
		add(txtTel);
		
		/*JPanel pane = new JPanel();
		
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(pane);*/
		add(new JLabel());
		add(new JLabel());
		
		JButton cmdok = new JButton("OK");
		add(cmdok);
		JButton cmdcancel = new JButton("Abbrechen");
		add(cmdcancel);
		
		cmdok.addActionListener(new MyOkHandler());
		cmdcancel.addActionListener(new MyCancelHandler());
		
		pack();
		setVisible(true);
	}
}
