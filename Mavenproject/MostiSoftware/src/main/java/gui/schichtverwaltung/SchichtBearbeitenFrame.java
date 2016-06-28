package gui.schichtverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import logik.schichtverwaltung.Schicht;
import logik.schichtverwaltung.SchichtLogik;

public class SchichtBearbeitenFrame extends JFrame{

	class MyKeyListener extends KeyAdapter {
		
		JComboBox<String> box;
		String aktuelleEingabe;
		ArrayList<Integer> mitarbeiterIds;
	
		MyKeyListener(int pos){
			box = boxliste.get(pos);
			aktuelleEingabe = eingabe.get(pos);
			mitarbeiterIds = idListe.get(pos);
		}
		
		public void keyTyped(KeyEvent k) {
			
			boolean delete = false;
			char c = k.getKeyChar();
			if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
				aktuelleEingabe = aktuelleEingabe + c;
				delete = false;
			}
			if (c == '\b') {
				if (aktuelleEingabe.length() > 0) {
					aktuelleEingabe = aktuelleEingabe.substring(0, aktuelleEingabe.length() - 1);
					delete = true;
				}
			}
			
			if(aktuelleEingabe != ""){
				box.showPopup();
				ArrayList<Integer> geladen = schichtlogik.mitarbeiterIdLaden(aktuelleEingabe);
				mitarbeiterIds.removeAll(mitarbeiterIds);
				for(Integer i: geladen){
					mitarbeiterIds.add(i);
				}
				box.removeAllItems();
				
				for(Integer i : mitarbeiterIds){
					box.addItem(schichtlogik.mitarbeiternameLaden(i));
				}
				box.setSelectedItem(null);
				if(delete == true){
					box.getEditor().setItem(aktuelleEingabe);
				}else{
					if(aktuelleEingabe.length() - 1 >= 0){
						box.getEditor().setItem(aktuelleEingabe.substring(0, aktuelleEingabe.length() - 1));
					}
					
				}
			}else{
				box.hidePopup();
			}
			
		}
	}
	
	class MyOkListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			for(int i=0; i<boxliste.size(); i++){
				JComboBox<String> combo = boxliste.get(i);
				int gewähltePosition = combo.getSelectedIndex();
				Schicht s = schichtliste.get(i%schichtlogik.getSchichtenProTag());
				if(combo.getEditor().getItem().equals("") && s.getMitarbeiterId(i/schichtlogik.getSchichtenProTag()) != 0){		//Termin gabs schon
					schichtlogik.schichtLöschen(s.getSchichtId(i/schichtlogik.getSchichtenProTag())); 		//Umgerechnet in absoulte position in der Tabelle
				}
				else{
					if(gewähltePosition > -1){
						int mitarbeiterId = idListe.get(i).get(gewähltePosition);
						if(s.getMitarbeiterId(i/schichtlogik.getSchichtenProTag()) != 0){				//Wenns den Termin schon gab
							schichtlogik.schichtUpdaten(s.getSchichtId(i/schichtlogik.getSchichtenProTag()), mitarbeiterId);
						}
						else{
							int uhrzeit = schichtlogik.berechneUhrzeit(i);
							schichtlogik.schichtSpeichern(datum, mitarbeiterId, uhrzeit);	
						}
					}
				}
			}
			SchichtBearbeitenFrame.this.dispose();
			
			
		}
	}
	
	
	private static final long serialVersionUID = 1L;
	private Date datum;
	private SchichtLogik schichtlogik;
	private ArrayList<JComboBox<String>> boxliste;
	private ArrayList<String> eingabe;
	private ArrayList<ArrayList<Integer>> idListe;
	private ArrayList<Schicht> schichtliste;
	
	
	SchichtBearbeitenFrame(long datum){
		setSize(600,300);
		setLocationRelativeTo(getParent());
		this.datum = new Date(datum);
		setTitle(this.datum.toString());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		schichtlogik = new SchichtLogik();
		boxliste = new ArrayList<JComboBox<String>>();
		idListe = new ArrayList<ArrayList<Integer>>();
		eingabe = new ArrayList<String>();
		schichtliste = schichtlogik.schichtLaden(datum);
		
		for(int i=0; i<schichtlogik.getMitarbeiterProSchicht(); i++){
			for(int j=0; j<schichtlogik.getSchichtenProTag(); j++){
				String s = new String("");
				eingabe.add(s);
				ArrayList<Integer> liste = new ArrayList<Integer>();
				idListe.add(liste);
			}
		}
		
		JButton cmdOk = new JButton("Speichern");
		cmdOk.setFont(cmdOk.getFont().deriveFont(16f));
		cmdOk.setBounds(10, 10, 130, 30);
		add(cmdOk);
		cmdOk.addActionListener(new MyOkListener());
		
		
		for(int i=0; i<schichtlogik.getSchichtenProTag(); i++){
			JLabel schicht = new JLabel("Schicht" +(i+1));
			schicht.setBounds(140+i*180, 50, 180,40);
			schicht.setFont(schicht.getFont().deriveFont(16f));
			add(schicht);
		}
		
		
		for(int i=0; i<schichtlogik.getMitarbeiterProSchicht(); i++){
			JLabel mitarbeiter = new JLabel("Mitarbeiter"+(i+1)+": ");
			mitarbeiter.setBounds(0, 100+i*40, 100, 40);
			mitarbeiter.setFont(mitarbeiter.getFont().deriveFont(16f));
			add(mitarbeiter);
		}
		
		for(int i=0; i<schichtlogik.getMitarbeiterProSchicht(); i++){
			for(int j=0; j<schichtlogik.getSchichtenProTag(); j++){
				JComboBox<String> combobox = new JComboBox<String>();
				combobox.setFont(combobox.getFont().deriveFont(16f));
				combobox.setEditable(true);
				combobox.setBounds(100+j*180, 100+i*40, 180, 40);
				boxliste.add(combobox);
				combobox.getEditor().getEditorComponent().addKeyListener(new MyKeyListener(j+i*schichtlogik.getSchichtenProTag()));
				add(combobox);
				int id = schichtliste.get(j).getMitarbeiterId(i);
				if(id != 0){
					String name = schichtlogik.mitarbeiternameLaden(id);
					combobox.getEditor().setItem(name);
				}
				
			}
		}
		
		
		
				
		setVisible(true);
	}

}
