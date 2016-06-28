package gui.trester;

import gui.verkauf.ÜbersichtButtonGroup;
import gui.verkauf.ÜbersichtButtonModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
	private JRadioButton buttonÄnderung;
	private JLabel lblLiterzahl;
	private JLabel lblText;
	private JLabel lblLiteränderung;
	private int gesamtliterzahl;
	private JLabel lblTresterpreis;
	private Tresterverwaltung tv;
	private Kunde kunde;
	private KundeDB kundeDB; 
	
	public TresterabrechnungFrame(int kundenId){
	
		setTitle("Trester abrechnen");
		setSize(600, 450);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		tv = new Tresterverwaltung();
		
		kundeDB = new KundeDB(); 
		kunde = kundeDB.einzelnenKundeLaden(kundenId);

		lblText = new JLabel("Die Literzahl für den gesamten, heutigen Tag beträgt:");
		lblText.setFont(lblText.getFont().deriveFont(16f));
		lblText.setBounds(6, 20, 450, 19);
		getContentPane().add(lblText);
		
		lblLiterzahl = new JLabel();
		gesamtliterzahl = tv.getTagesLiterZahl();
		lblLiterzahl.setText(String.valueOf(gesamtliterzahl) + " Liter");
		lblLiterzahl.setFont(lblLiterzahl.getFont().deriveFont(16f));
		lblLiterzahl.setBounds(510, 20, 150, 19);
		getContentPane().add(lblLiterzahl);
		
		lblText = new JLabel("Literzahl zur Abrechnung des Tresters:");
		lblText.setFont(lblText.getFont().deriveFont(16f));
		lblText.setBounds(6, 60, 300, 19);
		getContentPane().add(lblText);
		
		buttonGanzerTag = new JRadioButton("Literzahl des gesamten, heutigen Tages");
		buttonGanzerTag.setSelected(true);
		buttonGanzerTag.setModel(new ÜbersichtButtonModel(true));
		buttonGanzerTag.setFont(buttonGanzerTag.getFont().deriveFont(16f));
		buttonGanzerTag.setBounds(6, 90, 350, 23);
		buttonGanzerTag.addActionListener(new ButtonHandler());
		getContentPane().add(buttonGanzerTag);

		buttonÄnderung = new JRadioButton("Literzahl ändern");
		buttonÄnderung.setModel(new ÜbersichtButtonModel(false));
		buttonÄnderung.setFont(buttonÄnderung.getFont().deriveFont(16f));
		buttonÄnderung.setBounds(6, 120, 250, 23);
		buttonÄnderung.addActionListener(new ButtonHandler());
		getContentPane().add(buttonÄnderung);
		
		lblLiteränderung = new JLabel("Geben Sie die gewünschte Literzahl ein:");
		lblLiteränderung.setFont(lblLiteränderung.getFont().deriveFont(16f));
		lblLiteränderung.setBounds(6, 150, 350, 19);
		getContentPane().add(lblLiteränderung);
		lblLiteränderung.setVisible(false);
		
		txtNeueLiterzahl = new JFormattedTextField();
		txtNeueLiterzahl.setFont(txtNeueLiterzahl.getFont().deriveFont(16f));
		txtNeueLiterzahl.setBounds(350, 150, 100, 22);
		getContentPane().add(txtNeueLiterzahl);
		txtNeueLiterzahl.setVisible(false);
		
		//lblKundenname.setVisible(false);

		ÜbersichtButtonGroup group = new ÜbersichtButtonGroup();
		group.add(buttonGanzerTag);
		group.add(buttonÄnderung);
		group.setValue(true);
		
		JButton berechneTresterpreis = new JButton("Tresterpreis berechnen");
		berechneTresterpreis.setFont(berechneTresterpreis.getFont().deriveFont(16f));
		berechneTresterpreis.setBounds(6, 270, 300, 30);
		getContentPane().add(berechneTresterpreis);
		berechneTresterpreis.addActionListener(new MyBerechneHandler());
		
		lblText = new JLabel("Kostensumme für den Trester:");
		lblText.setFont(lblText.getFont().deriveFont(16f));
		lblText.setBounds(6, 310, 300, 19);
		getContentPane().add(lblText);
		
		lblTresterpreis = new JLabel();
		lblTresterpreis.setFont(lblTresterpreis.getFont().deriveFont(16f));
		lblTresterpreis.setBounds(280, 310, 250, 19);
		getContentPane().add(lblTresterpreis);
		lblTresterpreis.setVisible(false);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.setBounds(6, 370, 150, 30);
		getContentPane().add(okButton);
		okButton.addActionListener(new MyOkHandler());

		JButton abbButton = new JButton("Abbrechen");
		abbButton.setFont(abbButton.getFont().deriveFont(16f));
		abbButton.setBounds(220, 370, 150, 30);
		getContentPane().add(abbButton);
		abbButton.addActionListener(new MyAbbHandler());

		setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (buttonÄnderung.isSelected() == true) {
				lblLiteränderung.setVisible(true);
				txtNeueLiterzahl.setVisible(true);
			} else {
				lblLiteränderung.setVisible(false);
				txtNeueLiterzahl.setVisible(false);
			}
			lblTresterpreis.setVisible(false);
		}
		
	}
	
	private class MyBerechneHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int liter;
			if(buttonÄnderung.isSelected() == true){
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
			lblTresterpreis.setText(String.valueOf(Math.round(kosten*100)/100.0) + " €");
			lblTresterpreis.setVisible(true);
		}
	}
	
	
	
	private class MyOkHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int liter;
			if(buttonÄnderung.isSelected() == true){
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
			
			int result = JOptionPane.showConfirmDialog(TresterabrechnungFrame.this, "Der Trestergesamtpreis für " + liter + " L beträgt " + Math.round(kosten*100)/100.0+ " €.\n"
					+ "Abrechnung bestätigen?",
					"Frage", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION)
				return;
			
			java.util.Date datum = new Date();
			java.sql.Date date = new java.sql.Date(datum.getTime());
			Tresterabrechnung ta = new Tresterabrechnung(kunde.getKundenID()
					, liter, tv.getPreisPro1000L(), date);
			tv.tresterAbrechnungHinzufügen(ta);
			dispose();	
		}
	}
	
	private class MyAbbHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}

}
