package gui.administratorverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logik.administratorverwaltung.AdministratorLogik;

public class �bersichtFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField mitProSchichttxt;
	private JTextField schichtenProTagtxt;
	private JTextField beginntxt;
	private JTextField endetxt;
	private JTextField zeitslottxt;
	private JTextField anzeigetxt;
	private JTextField pressdauertxt;
	private JTextField abf�lldauertxt;
	private AdministratorLogik al;
	
	
	public �bersichtFrame(){
		setSize(500,600);
		setTitle("Konfigurationswerte");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		al = new AdministratorLogik();
		
		JLabel schicht = new JLabel("Schichten");
		schicht.setBounds(50, 10, 160, 30);
		add(schicht);
		
		JLabel mitProSchicht = new JLabel("Mitarbeiter pro Schicht:");
		mitProSchicht.setBounds(10,50,160,30);
		add(mitProSchicht);
		
		mitProSchichttxt = new JTextField(AdministratorLogik.getMitarbeiterProSchicht() + "");
		mitProSchichttxt.setBounds(200, 50, 160, 30);
		add(mitProSchichttxt);
		
		JLabel schichtenProTag = new JLabel("Schichten pro Tag:");
		schichtenProTag.setBounds(10,90,160,30);
		add(schichtenProTag);
		
		schichtenProTagtxt = new JTextField(AdministratorLogik.getSchichtenProTag() + "");
		schichtenProTagtxt.setBounds(200, 90, 160, 30);
		add(schichtenProTagtxt);
		
		JLabel termine = new JLabel("Termine");
		termine.setBounds(50, 150, 160, 30);
		add(termine);
		
		JLabel arbeitsbeginn = new JLabel("Arbeitsbeginn:");
		arbeitsbeginn.setBounds(10,190,160,30);
		add(arbeitsbeginn);
		
		beginntxt = new JTextField(AdministratorLogik.getArbeitsbeginn() + "");
		beginntxt.setBounds(200, 190, 160, 30);
		add(beginntxt);
		
		JLabel arbeitsende = new JLabel("Arbeitsende");
		arbeitsende.setBounds(10,230,160,30);
		add(arbeitsende);
		
		endetxt = new JTextField(AdministratorLogik.getArbeitsende() + "");
		endetxt.setBounds(200, 230, 160, 30);
		add(endetxt);
		
		JLabel zeitslot = new JLabel("L�nge eines Terminslots: ");
		zeitslot.setBounds(10,270,160,30);
		add(zeitslot);
		
		zeitslottxt = new JTextField(AdministratorLogik.getZeitslot() + "");
		zeitslottxt.setBounds(200, 270, 160, 30);
		add(zeitslottxt);
		
		JLabel anzeige = new JLabel("Seitenaufteilung der Termine: ");
		anzeige.setBounds(10,310,180,30);
		add(anzeige);
		
		anzeigetxt = new JTextField(AdministratorLogik.getAufteilung() + "");
		anzeigetxt.setBounds(200, 310, 160, 30);
		add(anzeigetxt);
		
		JLabel pressdauer = new JLabel("Presszeit f�r einen Zentner Obst in min: ");
		pressdauer.setBounds(10,350,160,30);
		add(pressdauer);
		
		pressdauertxt = new JTextField(AdministratorLogik.getPressdauer() + "");
		pressdauertxt.setBounds(200, 350, 160, 30);
		add(pressdauertxt);
		
		JLabel abf�lldauer = new JLabel("Abf�lldauer f�r Flaschen: ");
		abf�lldauer.setBounds(10,390,180,30);
		add(abf�lldauer);
		
		abf�lldauertxt = new JTextField(AdministratorLogik.getAufteilung() + "");
		abf�lldauertxt.setBounds(200, 390, 160, 30);
		add(abf�lldauertxt);
		
		JButton speichern = new JButton("Speichern");
		speichern.setBounds(10, 440, 200, 40);
		add(speichern);
		speichern.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int result = JOptionPane.showConfirmDialog(�bersichtFrame.this, "Wollen Sie die �nderungen speichern?");
				if(result == JOptionPane.NO_OPTION){
					dispose();
				}
				if(result == JOptionPane.YES_OPTION){
					int anzeige, zeitslot, beginn, ende, mitProSchicht, schichtenProTag;
					double pressdauer, abf�lldauer;
					try{
						anzeige = Integer.parseInt(abf�lldauertxt.getText());
						zeitslot = Integer.parseInt(pressdauertxt.getText());
						beginn = Integer.parseInt(beginntxt.getText());
						ende = Integer.parseInt(endetxt.getText());
						mitProSchicht = Integer.parseInt(mitProSchichttxt.getText());
						schichtenProTag = Integer.parseInt(schichtenProTagtxt.getText());
						pressdauer = Double.parseDouble(pressdauertxt.getText());
						abf�lldauer = Double.parseDouble(abf�lldauertxt.getText());
					}catch(Exception ex){
						ex.printStackTrace();
						return;
					}
					if((ende-beginn)%zeitslot != 0){
						JOptionPane.showMessageDialog(�bersichtFrame.this, "Bei dieser Terminslotl�nge kommt es zu unfertigen Terminen. "
								+ "Bitte w�hlen Sie eine andere Slotl�nge");
						return;
					}
					if((ende-beginn)%(zeitslot*anzeige) != 0){
						JOptionPane.showMessageDialog(�bersichtFrame.this, "Mit dieser Anzeigeauftelung k�nne die Termine nicht gleichm��ig verteilt werden."
								+ " Bitte w�hlen Sei eine andere Aufteilung");
						return;
					}
					al.datenSpeichern(anzeige, zeitslot, beginn, ende, mitProSchicht, schichtenProTag, pressdauer, abf�lldauer);
					�bersichtFrame.this.dispose();
				}
			}
		});
		
		JButton abbrechen = new JButton("Abbrechen");
		abbrechen.setBounds(240, 440, 200, 40);
		add(abbrechen);
		abbrechen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				�bersichtFrame.this.dispose();
			}
		});
		setVisible(true);
		
	}

}
