package kundenverwaltung;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


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
			int antwort = JOptionPane.showConfirmDialog(KundeBearbeitenDialog.this, "Wollen sie wirklich speichern?");
			if(antwort == JOptionPane.OK_OPTION){
				kunde.setNachname(beautify(txtNachname.getText()));
				kunde.setVorname(beautify(txtVorname.getText()));
				kunde.setStrasse(beautify(txtStrasse.getText()));
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
	private JFormattedTextField txtPlz;
	private JTextField txtWohnort;
	private JTextField txtTel;
	
	public KundeBearbeitenDialog(JFrame parent, Kunde kunde){
		super(parent);
		this.kunde = kunde;
		
		setModal(true);
		setTitle("Kunde bearbeiten");
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
		
		txtNachname = new JTextField(kunde.getNachname());
		txtNachname.setBounds(110, 10, 150, 20);
		main.add(txtNachname);
		
		label = new JLabel("Vorname:");
		label.setBounds(10, 40, 100, 20);
		main.add(label);
		
		txtVorname = new JTextField(kunde.getVorname());
		txtVorname.setBounds(110, 40, 150, 20);
		main.add(txtVorname);
		
		
//		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
//		NullableFormatter nf = new NullableFormatter(df);
		
		
		label = new JLabel("Straﬂe:");
		label.setBounds(10, 100, 100, 20);
		main.add(label);
		
		txtStrasse = new JTextField(kunde.getStrasse());
		txtStrasse.setBounds(110, 100, 150, 20);
		main.add(txtStrasse);
		
		label = new JLabel("Plz:");
		label.setBounds(10, 130, 100, 20);
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
		txtPlz.setValue(kunde.getPlz());
		txtPlz.setBounds(110, 130, 40, 20);
		main.add(txtPlz);
		
		label = new JLabel("Ort:");
		label.setBounds(10, 160, 100, 20);
		main.add(label);
		
		txtWohnort = new JTextField(kunde.getWohnort());
		txtWohnort.setBounds(110, 160, 150, 20);
		main.add(txtWohnort);
		
		label = new JLabel("Telefonnummer:");
		label.setBounds(10, 180, 100, 20);
		main.add(label);
		
		txtTel = new JTextField(kunde.getTel());
		txtTel.setBounds(110,180,170,20);
		main.add(txtTel);
		
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
