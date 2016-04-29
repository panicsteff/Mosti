package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import terminplanung.Termin;

public class TerminBearbeitenFrame extends JFrame{

	private Termin termin;
	
	
	public TerminBearbeitenFrame(Termin t){
		
		setTitle("Termin bearbeiten");
		setSize(300,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		termin = t;
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(3,2));
		
		JLabel kunde = new JLabel("Kunde:");
		JTextField kundetxt = new JTextField(termin.getKundenId());						//irgentwann mal vor und nachname vom kunden
		
		JLabel datum = new JLabel("Datum");
		JTextField datumtxt = new JTextField(termin.getDatum().toString());
		
		JLabel uhrzeit = new JLabel("Uhrzeit");
		JTextField uhrzeittxt = new JTextField(termin.getUhrzeit().toString());
		
		content.add(kunde);
		content.add(kundetxt);
		content.add(datum);
		content.add(datumtxt);
		content.add(uhrzeit);
		content.add(uhrzeittxt);
		
		add(content);
		
		setVisible(true);
	}
	
}
