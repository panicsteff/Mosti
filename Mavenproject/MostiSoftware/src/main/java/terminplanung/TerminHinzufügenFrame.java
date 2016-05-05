package terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TerminHinzufügenFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> adminwerte = new ArrayList<Integer>();
	private JTextField txtmenge;
	private JLabel beginn;
	private JTextField txtbeginn;
	private JLabel dauer;
	private JTextField txtdauer;

	
	public TerminHinzufügenFrame(ArrayList<Integer> aw){
		setBounds(350, 200, 300, 200);
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		adminwerte = aw;
		setLayout(null);
		
		JLabel menge = new JLabel("Obstmenge: ");
		menge.setBounds(10, 10, 100, 20);
		add(menge);
		
		txtmenge = new JTextField();
		txtmenge.setBounds(150, 10, 100, 20);
		add(txtmenge);
		
		JButton dauerberechnen = new JButton("Termindauer berechnen");
		dauerberechnen.setBounds(25, 50, 200, 20);
		dauerberechnen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int länge = 0;
				try {
					String s = txtmenge.getText();
					länge = berechneTermindauer(s);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(TerminHinzufügenFrame.this, "Keine gültige Eingabe der Obstmenge");
				}
				beginn.setEnabled(true);
				txtbeginn.setEnabled(true);
				dauer.setEnabled(true);
				txtdauer.setEnabled(true);
				txtdauer.setText(länge + "");
			}
		});
		add(dauerberechnen);
		
		dauer = new JLabel("Termindauer");
		dauer.setBounds(10, 90, 100, 20);
		dauer.setEnabled(false);
		add(dauer);
		
		txtdauer = new JTextField();
		txtdauer.setBounds(150, 90, 100, 20);
		txtdauer.setEnabled(false);
		add(txtdauer);
		
		beginn = new JLabel("Terminbeginn");
		beginn.setEnabled(false);
		beginn.setBounds(10, 130, 100, 20);
		add(beginn);
		
		txtbeginn = new JTextField();
		txtbeginn.setEnabled(false);
		txtbeginn.setBounds(150, 130, 100, 20);
		add(txtbeginn);
		
		
		
		
		
		
		
		
		setVisible(true);
	}
	
	
	public static void main(String[] avgs){
		ArrayList<Integer> aw = new ArrayList<Integer>();
		aw.add(5);
		aw.add(540);
		aw.add(1140);
		aw.add(3);
		new TerminHinzufügenFrame(aw);
	}
	
	int berechneTermindauer(String s) throws ParseException{
		
		int obstmenge = Integer.parseInt(s);
		double dauer = obstmenge/3;
		int zeitslot = adminwerte.get(0);
		if(dauer%zeitslot == 0){
			return (int) dauer;
		} else{
			int h = (int) dauer/zeitslot;
			dauer = (h+1)*zeitslot;
			return (int) dauer;
		}
	}
}
