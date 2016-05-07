package terminplanung;

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

import kundenverwaltung.Formats;

public class TerminErstellenDialog extends JDialog{

	class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent k){
			eingabe = eingabe + k.getKeyChar();
			kundenId = terminDb.kundenIdLaden(eingabe);
			kundetxt.setText(terminDb.kundenNamenLaden(kundenId));
		}
	}
	
	class MyOkListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			for(Termin t: terminliste){
				t.setKundenId(kundenId);
			}
			TerminErstellenDialog.this.dispose();
			
		}
	}
	
	
	private static final long serialVersionUID = 1L;
	private JTextField dauertxt;
	private JTextField uhrzeittxt;
	private JTextField kundetxt;
	private ArrayList<Termin> terminliste;
	private TerminDB terminDb;
	private String eingabe = "";
	private int kundenId;

	public TerminErstellenDialog(int dauer, Date date, ArrayList<Termin> t, String uhrzeitAnzeige){
				
		setModal(true);
		
		setTitle("Termin anlegen");
		setSize(400,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		terminliste = t;
		terminDb = new TerminDB();
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(5,2));
		
		JLabel kunde = new JLabel("Kunde:");
		kunde.setFont(kunde.getFont().deriveFont(16f));
		kundetxt = new JTextField();
		kundetxt.setFont(kundetxt.getFont().deriveFont(16f));
		kundetxt.addKeyListener(new MyKeyListener());
		
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
		content.add(kundetxt);
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
