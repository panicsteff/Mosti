package gui.trester;


import gui.verkauf.�bersichtButtonGroup;
import gui.verkauf.�bersichtButtonModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import logik.kundenverwaltung.Kunde;
import logik.trester.Tresterabrechnung;
import logik.trester.Tresterverwaltung;
import persistenz.KundeDB;

public class TresterabrechnungFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtNeueLiterzahl;
	private JRadioButton buttonGanzerTag;
	private JRadioButton button�nderung;
	private JLabel lblLiterzahl;
	private JLabel lblText;
	private JLabel lblLiter�nderung;
	private int gesamtliterzahl;
	private JLabel lblTresterpreis;
	private Tresterverwaltung tv;
	private Kunde kunde;
	private KundeDB kundeDB; // nur zum Test
	
	public TresterabrechnungFrame(int kundenId){
		
		kundeDB = new KundeDB(); 
		kunde = kundeDB.einzelnenKundeLaden(kundenId); // Kunde eig aus Termin�bersicht �bernehmen
	
		setTitle("Trester abrechnen f�r Kunde "+ kunde.getNachname()
				);
		setSize(480, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		tv = new Tresterverwaltung();

		lblText = new JLabel("Die Literzahl f�r den gesamten, heutigen Tag betr�gt:");
		lblText.setBounds(6, 20, 330, 19);
		getContentPane().add(lblText);
		
		lblLiterzahl = new JLabel();
		gesamtliterzahl = tv.getTagesLiterZahl();
		lblLiterzahl.setText(String.valueOf(gesamtliterzahl) + " Liter");
		lblLiterzahl.setBounds(340, 20, 150, 19);
		getContentPane().add(lblLiterzahl);
		
		lblText = new JLabel("Literzahl zur Abrechnung des Tresters:");
		lblText.setBounds(6, 60, 250, 19);
		getContentPane().add(lblText);
		
		buttonGanzerTag = new JRadioButton("Literzahl des gesamten, heutigen Tages");
		buttonGanzerTag.setSelected(true);
		buttonGanzerTag.setModel(new �bersichtButtonModel(true));
		buttonGanzerTag.setBounds(6, 90, 300, 23);
		buttonGanzerTag.addActionListener(new ButtonHandler());
		getContentPane().add(buttonGanzerTag);

		button�nderung = new JRadioButton("Literzahl �ndern");
		button�nderung.setModel(new �bersichtButtonModel(false));
		button�nderung.setBounds(6, 120, 250, 23);
		button�nderung.addActionListener(new ButtonHandler());
		getContentPane().add(button�nderung);
		
		lblLiter�nderung = new JLabel("Geben Sie die gew�nschte Literzahl ein:");
		lblLiter�nderung.setBounds(6, 150, 250, 19);
		getContentPane().add(lblLiter�nderung);
		lblLiter�nderung.setVisible(false);
		
		txtNeueLiterzahl = new JFormattedTextField();
		txtNeueLiterzahl.setBounds(270, 150, 100, 19);
		getContentPane().add(txtNeueLiterzahl);
		txtNeueLiterzahl.setVisible(false);
		
		//lblKundenname.setVisible(false);

		�bersichtButtonGroup group = new �bersichtButtonGroup();
		group.add(buttonGanzerTag);
		group.add(button�nderung);
		group.setValue(true);
		
		JButton berechneTresterpreis = new JButton("Tresterpreis berechnen");
		berechneTresterpreis.setBounds(6, 270, 200, 30);
		getContentPane().add(berechneTresterpreis);
		berechneTresterpreis.addActionListener(new MyBerechneHandler());
		
		lblText = new JLabel("Kostensumme f�r den Trester:");
		lblText.setBounds(6, 310, 250, 19);
		getContentPane().add(lblText);
		
		lblTresterpreis = new JLabel();
		lblTresterpreis.setBounds(280, 310, 250, 19);
		getContentPane().add(lblTresterpreis);
		lblTresterpreis.setVisible(false);
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(6, 370, 150, 30);
		getContentPane().add(okButton);
		okButton.addActionListener(new MyOkHandler());

		JButton abbButton = new JButton("Abbrechen");
		abbButton.setBounds(220, 370, 150, 30);
		getContentPane().add(abbButton);
		abbButton.addActionListener(new MyAbbHandler());

		setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (button�nderung.isSelected() == true) {
				lblLiter�nderung.setVisible(true);
				txtNeueLiterzahl.setVisible(true);
			} else {
				lblLiter�nderung.setVisible(false);
				txtNeueLiterzahl.setVisible(false);
			}
			lblTresterpreis.setVisible(false);
		}
		
	}
	
	private class MyBerechneHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int liter;
			if(button�nderung.isSelected() == true){
				try{
				liter = Integer.parseInt(txtNeueLiterzahl.getText());
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(TresterabrechnungFrame.this,
							"Bitte geben Sie eine Ganzzahl als Literzahl ein.",
							"Fehler", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
				
			else 
				liter = gesamtliterzahl;
			double kosten = tv.berechneTresterGesamtpreis(liter);
			lblTresterpreis.setText(String.valueOf(Math.round(kosten*100)/100.0) + " �");
			lblTresterpreis.setVisible(true);
		}
	}
	
	
	
	private class MyOkHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int liter;
			if(button�nderung.isSelected() == true){
				try{
				liter = Integer.parseInt(txtNeueLiterzahl.getText());
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(TresterabrechnungFrame.this,
							"Bitte geben Sie eine Ganzzahl als Literzahl ein.",
							"Fehler", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}	
			else 
				liter = gesamtliterzahl;
			double kosten = tv.berechneTresterGesamtpreis(liter);
			
			int result = JOptionPane.showConfirmDialog(TresterabrechnungFrame.this, "Der Trestergesamtpreis f�r " + liter + " L betr�gt " + Math.round(kosten*100)/100.0+ " �.\n"
					+ "Abrechnung best�tigen?",
					"Frage", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION)
				return;
			
			Tresterabrechnung ta = new Tresterabrechnung(kunde, liter, Math.round(kosten*100)/100.0);
			tv.tresterAbrechnungHinzuf�gen(ta);
			dispose();	
		}
	}
	
	private class MyAbbHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}
	
	public static void main(String[] args){
		TresterabrechnungFrame t = new TresterabrechnungFrame(6);
	}

}
