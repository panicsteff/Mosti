package terminplanung;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TerminBearbeitenFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	
	
	TerminBearbeitenFrame(String date, String name, String zeit){
		
		setTitle("Termin bearbeiten");
		setSize(300,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(3,2));
		
		JLabel kunde = new JLabel("Kunde:");
		kunde.setFont(kunde.getFont().deriveFont(16f));
		JTextField kundetxt = new JTextField(name);	
		kundetxt.setFont(kundetxt.getFont().deriveFont(16f));
		
		JLabel datum = new JLabel("Datum");
		datum.setFont(datum.getFont().deriveFont(16f));
		JTextField datumtxt = new JTextField(date);
		datumtxt.setFont(datumtxt.getFont().deriveFont(16f));
		
		JLabel uhrzeit = new JLabel("Uhrzeit");
		uhrzeit.setFont(uhrzeit.getFont().deriveFont(16f));
		JTextField uhrzeittxt = new JTextField(zeit);
		uhrzeittxt.setFont(uhrzeittxt.getFont().deriveFont(16f));
		
		content.add(kunde);
		content.add(kundetxt);
		content.add(datum);
		content.add(datumtxt);
		content.add(uhrzeit);
		content.add(uhrzeittxt);
		
		add(content);
		
		setVisible(true);
	}
	
	public static void main (String[] avg){
		new TerminBearbeitenFrame("1.9.2016" ,"Franz Schofreiter", "11.45");
	}
}
