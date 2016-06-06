package gui.trester;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;

import kundenverwaltung.NullableFormatter;
import logik.produktverwaltung.FoFormat;
import logik.trester.Tresterabrechnung;
import logik.trester.Tresterverwaltung;

@SuppressWarnings("serial")
public class TresterFrame extends JFrame {
	
	private JFormattedTextField txtPreis;
	private Tresterabrechnung ta;
	private JLabel label;
	static Tresterverwaltung tv;
	
	public TresterFrame(Tresterverwaltung tv) {
		
		this.tv = tv;	
		setTitle("Tresterpreis verwalten");
		setSize(500, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(new GridLayout(3,2));
		label = new JLabel("Aktueller Tresterpreis pro 1000 L Saft: ");
		add(label);
		label = new JLabel(String.valueOf(tv.getPreisPro1000L())+ " €");
		add(label);
//		label = new JLabel("Neuer Preis aus 1000 L Saft: ");
//		//label.setFont(label.getFont().deriveFont(14f));
//		add(label);

		label = new JLabel ("Neuer Tresterpreis pro 1000 L [€]:");
		//label.setFont(label.getFont().deriveFont(14f));
		add(label);
		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
		NullableFormatter nf = new NullableFormatter(nuf);
		add(txtPreis = new JFormattedTextField(nf));

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new MyOKHandler());
		add(okButton);

		JButton abbButton = new JButton("Abbrechen");
		abbButton.addActionListener(new MyAbbHandler());
		add(abbButton);

		setVisible(true);
	}
	
	private class MyOKHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			try {
				tv.setPreisPro1000L(Double.parseDouble(txtPreis.getText()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Preis pro 1000 L: "+ tv.getPreisPro1000L());
			//Tresterabrechnung ta = new Tresterabrechnung(tv, 2000);
			//System.out.println("Tresterpreis: "+ta.getPreis());
			dispose();
		}
	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();

		}

	}
	
//	public static void main(String[] args){
//		TresterFrame t = new TresterFrame(new Tresterverwaltung());
//		
//	}

}
