package gui.terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logik.kundenverwaltung.Formats;
import logik.terminplanung.Termin;
import logik.terminplanung.TerminLogik;

public class TerminBearbeitenDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private Termin termin;
	private ArrayList<Termin> alleTermine;
	
	public TerminBearbeitenDialog(Termin t, int länge, String name, String zeit, ArrayList<Termin> aTermine){
		setSize(320,400);
		setTitle("Termin bearbeiten");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setModal(true);
		
		termin = t;
		alleTermine = aTermine;
		
		JLabel kunde = new JLabel("Kunde");
		kunde.setBounds(0,0,200,40);
		kunde.setFont(kunde.getFont().deriveFont(16f));
		add(kunde);
		
		JTextField kundetxt = new JTextField(name);
		kundetxt.setBounds(100, 0, 200, 40);
		kundetxt.setFont(kundetxt.getFont().deriveFont(16f));
		add(kundetxt);
		
		JLabel datum = new JLabel("Datum:");
		datum.setFont(datum.getFont().deriveFont(16f));
		datum.setBounds(0, 40, 200, 40);
		add(datum);
		
		JTextField datumtxt = new JTextField(Formats.DATE_FORMAT.format(t.getDatum()));
		datumtxt.setFont(datumtxt.getFont().deriveFont(16f));
		datumtxt.setBounds(100, 40, 200, 40);
		add(datumtxt);

		JLabel uhrzeit = new JLabel("Uhrzeit:");
		uhrzeit.setFont(uhrzeit.getFont().deriveFont(16f));
		uhrzeit.setBounds(0, 80, 200, 40);
		add(uhrzeit);
		
		JTextField uhrzeittxt = new JTextField(zeit);
		uhrzeittxt.setFont(uhrzeittxt.getFont().deriveFont(16f));
		uhrzeittxt.setBounds(100, 80, 200, 40);
		add(uhrzeittxt);

		JLabel dauer = new JLabel("Dauer:");
		dauer.setFont(dauer.getFont().deriveFont(16f));
		dauer.setBounds(0, 120, 200, 40);
		add(dauer);
		
		JTextField dauertxt = new JTextField(länge + " min");
		dauertxt.setFont(dauertxt.getFont().deriveFont(16f));
		dauertxt.setBounds(100, 120, 200, 40);
		add(dauertxt);
		
		JButton löschen = new JButton("Löschen");
		löschen.setBounds(0, 160, 200, 40);
		add(löschen);
		löschen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int result  = JOptionPane.showConfirmDialog(TerminBearbeitenDialog.this, "Wollen sie den Termin wirklich löschen?");
				if(result == JOptionPane.YES_OPTION){
					for(Termin t: alleTermine){
						if(t.getTerminId() == termin.getTerminId()){
							t.setKundenId(0);
						}
					}
					TerminLogik.terminLöschen(termin);
					TerminBearbeitenDialog.this.dispose();
				}
			}
		});
		
		JButton verschieben = new JButton("Ok");
		verschieben.setBounds(200, 160, 200, 40);
		add(verschieben);
		verschieben.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TerminBearbeitenDialog.this.dispose();
			}
		});
		
		setVisible(true);
	}
}
