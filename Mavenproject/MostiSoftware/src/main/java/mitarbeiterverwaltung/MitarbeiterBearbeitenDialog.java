package mitarbeiterverwaltung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MitarbeiterBearbeitenDialog extends JDialog {

	
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
			mitarbeiter.setNachname(beautify(txtNachname.getText()));
			mitarbeiter.setVorname(beautify(txtVorname.getText()));
			mitarbeiter.setStrasse(beautify(txtStrasse.getText()));
			mitarbeiter.setHausnummer(beautify(txtHausnummer.getText()));
			mitarbeiter.setPlz((String)txtPlz.getValue());
			mitarbeiter.setStadt(beautify(txtStadt.getText()));
			mitarbeiter.setTelefonnummer(beautify(txtTel.getText()));
			mitarbeiter.setBenutzername(beautify(txtBenutzername.getText()));
			dispose();
		}
	}
	
	class MyCancelHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}
	
	
	
	private Mitarbeiter mitarbeiter;
	
	private JTextField txtNachname;
	private JTextField txtVorname;
	private JTextField txtStrasse;
	private JTextField txtHausnummer;
	private JFormattedTextField txtPlz;
	private JTextField txtStadt;
	private JTextField txtTel;
	private JTextField txtBenutzername;
	public MitarbeiterBearbeitenDialog(JFrame parent, Mitarbeiter mitarbeiter){
		super(parent);
		this.mitarbeiter = mitarbeiter;
		
		setModal(true);
		setTitle("Mitarbeiter bearbeiten");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel main = new JPanel();
		main.setLayout(null);
		main.setPreferredSize(new Dimension(270, 410));
		
		JScrollPane scrollpane = new JScrollPane(main);
		add(scrollpane);
		
		JLabel  label;
		
		label = new JLabel("Nachname:");
		label.setBounds(10, 10, 100, 20);
		main.add(label);
		
		txtNachname = new JTextField(mitarbeiter.getNachname());
		txtNachname.setBounds(110, 10, 150, 20);
		main.add(txtNachname);
		
		label = new JLabel("Vorname:");
		label.setBounds(10, 40, 100, 20);
		main.add(label);
		
		txtVorname = new JTextField(mitarbeiter.getVorname());
		txtVorname.setBounds(110, 40, 150, 20);
		main.add(txtVorname);
		
		
//		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
//		NullableFormatter nf = new NullableFormatter(df);
		
		
		label = new JLabel("Straﬂe:");
		label.setBounds(10, 100, 100, 20);
		main.add(label);
		
		txtStrasse = new JTextField(mitarbeiter.getStrasse());
		txtStrasse.setBounds(110, 100, 150, 20);
		main.add(txtStrasse);
		
		
		label = new JLabel("Hausnummer:");
		label.setBounds(10,130,100,20);
		main.add(label);
		
		txtHausnummer = new JTextField(mitarbeiter.getHausnummer());
		txtHausnummer.setBounds(110,130,150,20);
		main.add(txtHausnummer);
		
		
		label = new JLabel("Plz:");
		label.setBounds(10, 160, 100, 20);
		main.add(label);
		
//		MaskFormatter mf = null;
//		try{
//			mf = new MaskFormatter("#####");
//		}
//		catch(ParseException e){
//			System.out.println(e);
//			
//		}
//		nf = new NullableFormatter(mf);
		txtPlz = new JFormattedTextField();
		txtPlz.setValue(mitarbeiter.getPlz());
		txtPlz.setBounds(110, 160, 40, 20);
		main.add(txtPlz);
		
		label = new JLabel("Ort:");
		label.setBounds(10, 190, 100, 20);
		main.add(label);
		
		txtStadt = new JTextField(mitarbeiter.getStadt());
		txtStadt.setBounds(110, 190, 100, 20);
		main.add(txtStadt);
		
		label = new JLabel("Telefonnummer:");
		label.setBounds(10, 220, 100, 20);
		main.add(label);
		
		txtTel = new JTextField(mitarbeiter.getTelefonnummer());
		txtTel.setBounds(110,220,100,20);
		main.add(txtTel);
		
		label = new JLabel("Benutzername:");
		label.setBounds(10,250,100,20);
		main.add(label);
		
		txtBenutzername = new JTextField(mitarbeiter.getBenutzername());
		txtBenutzername.setBounds(110,250,100,20);
		main.add(txtBenutzername);
		
		
		
		JPanel pane = new JPanel();
		pane.setBounds(10, 360, 250, 40);
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		main.add(pane);
		
		JButton cmdok = new JButton("OK");
		pane.add(cmdok);
		JButton cmdcancel = new JButton("Abbrechen");
		pane.add(cmdcancel);
		
		cmdok.addActionListener(new MyOkHandler());
		cmdcancel.addActionListener(new MyCancelHandler());
		
		pack();
		setVisible(true);
	}
}
