package adminestratorenverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mitarbeiterverwaltung.Formats;

public class SchichtErstellenDialog extends JDialog{
	
	class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent k){
			eingabe = eingabe + k.getKeyChar();
			mitarbeiterId = schichtDb.mitarbeiterIdLaden(eingabe);
			mitarbeitertxt.setText(schichtDb.mitarbeiterNamenLaden(mitarbeiterId));
		}
	}
	
	class MyOkListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			for(Schicht s: schichtliste){
				s.setMitarbeiterId(mitarbeiterId);
			}
			SchichtErstellenDialog.this.dispose();
			
		}
	}
	
	
	private static final long serialVersionUID = 1L;
	private JTextField dauertxt;
	private JTextField uhrzeittxt;
	private JTextField mitarbeitertxt;
	private ArrayList<Schicht> schichtliste;
	private SchichtplanDB schichtDb;
	private String eingabe = "";
	private int mitarbeiterId;

	public SchichtErstellenDialog(int dauer, Date date, ArrayList<Schicht> s, String uhrzeitAnzeige){
				
		setModal(true);
		
		setTitle("Schicht anlegen");
		setSize(400,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		schichtliste = s;
		schichtDb = new SchichtplanDB();
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(5,2));
		
		JLabel kunde = new JLabel("Kunde:");
		kunde.setFont(kunde.getFont().deriveFont(16f));
		mitarbeitertxt = new JTextField();
		mitarbeitertxt.setFont(mitarbeitertxt.getFont().deriveFont(16f));
		mitarbeitertxt.addKeyListener(new MyKeyListener());
		
		JLabel datum = new JLabel("Datum:");
		datum.setFont(datum.getFont().deriveFont(16f));
		JTextField datumtxt = new JTextField(Formats.DATE_FORMAT.format(date));
		datumtxt.setFont(datumtxt.getFont().deriveFont(16f));
		
		JLabel uhrzeit = new JLabel("Uhrzeit:");
		uhrzeit.setFont(uhrzeit.getFont().deriveFont(16f));
		uhrzeittxt = new JTextField(uhrzeitAnzeige);						
		uhrzeittxt.setFont(uhrzeittxt.getFont().deriveFont(16f));
		
		JLabel dauerlabel = new JLabel("Dauer:");
		dauerlabel.setFont(dauerlabel.getFont().deriveFont(16f));
		dauertxt = new JTextField(dauer + " min");
		dauertxt.setFont(dauertxt.getFont().deriveFont(16f));
		
		JButton speichern = new JButton("Speichern");
		speichern.addActionListener(new MyOkListener());
		
		
		content.add(kunde);
		content.add(mitarbeitertxt);
		content.add(datum);
		content.add(datumtxt);
		content.add(uhrzeit);
		content.add(uhrzeittxt);
		content.add(dauerlabel);
		content.add(dauertxt);
		content.add(speichern);
		
		add(content);
		
		setVisible(true);
		
	}
	

}
