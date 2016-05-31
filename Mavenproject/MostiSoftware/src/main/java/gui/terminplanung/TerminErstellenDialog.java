package gui.terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kundenverwaltung.Formats;
import logik.terminplanung.Termin;
import logik.terminplanung.TerminLogik;

public class TerminErstellenDialog extends JDialog {
	
	class MyKeyListener extends KeyAdapter {
		public void keyTyped(KeyEvent k) {
			
			boolean delete = false;
			
			char c = k.getKeyChar();
			if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
				eingabe = eingabe + c;
				delete = false;
			}
			if (c == '\b') {
				if (eingabe.length() > 0) {
					eingabe = eingabe.substring(0, eingabe.length() - 1);
					delete = true;
				}
			}
			
			if(eingabe != ""){
				kundetxt.showPopup();
				kundenIds = terminlogik.kundenIDLaden(eingabe);
				kundetxt.removeAllItems();
				
				for(Integer i : kundenIds){
					kundetxt.addItem(terminlogik.kundenNamenLaden(i));
				}
				kundetxt.setSelectedItem(null);
				if(delete == true){
					kundetxt.getEditor().setItem(eingabe);
				}else{
					if(eingabe.length() - 1 >= 0){
						kundetxt.getEditor().setItem(eingabe.substring(0, eingabe.length() - 1));
					}
					
				}
			}else{
				kundetxt.hidePopup();
			}
			
		}
	}

	class MyOkListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int gewähltePosition = kundetxt.getSelectedIndex();
			
			if(gewähltePosition == -1){
				JOptionPane.showMessageDialog(TerminErstellenDialog.this, "Bitte wählen Sie einen Kunden aus");
			} else {
				kundenId = kundenIds.get(gewähltePosition);
				for (Termin t : terminliste) {
					t.setKundenId(kundenId);
				}
				TerminErstellenDialog.this.dispose();
			}
			
		}
	}

	private static final long serialVersionUID = 1L;
	private JTextField dauertxt;
	private JTextField uhrzeittxt;
	private JComboBox<String> kundetxt;
	private ArrayList<Termin> terminliste;
	private String eingabe = "";
	private int kundenId;
	private TerminLogik terminlogik;
	private ArrayList<Integer> kundenIds;

	public TerminErstellenDialog(int dauer, Date date, ArrayList<Termin> t,
			String uhrzeitAnzeige) {

		setModal(true);

		setTitle("Termin anlegen");
		setSize(420, 248);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		terminliste = t;
		terminlogik = new TerminLogik();

		JLabel kunde = new JLabel("Kunde:");
		kunde.setFont(kunde.getFont().deriveFont(16f));
		kunde.setBounds(0, 0, 200, 40);
		kundetxt = new JComboBox<String>();
		kundetxt.setFont(kundetxt.getFont().deriveFont(16f));
		kundetxt.setEditable(true);
		kundetxt.getEditor().getEditorComponent().addKeyListener(new MyKeyListener());
		kundetxt.setBounds(200, 0, 200, 40);
		
		JLabel datum = new JLabel("Datum:");
		datum.setFont(datum.getFont().deriveFont(16f));
		datum.setBounds(0, 40, 200, 40);
		JTextField datumtxt = new JTextField(Formats.DATE_FORMAT.format(date));
		datumtxt.setFont(datumtxt.getFont().deriveFont(16f));
		datumtxt.setBounds(200, 40, 200, 40);

		JLabel uhrzeit = new JLabel("Uhrzeit:");
		uhrzeit.setFont(uhrzeit.getFont().deriveFont(16f));
		uhrzeit.setBounds(0, 80, 200, 40);
		uhrzeittxt = new JTextField(uhrzeitAnzeige);
		uhrzeittxt.setFont(uhrzeittxt.getFont().deriveFont(16f));
		uhrzeittxt.setBounds(200, 80, 200, 40);

		JLabel dauerlabel = new JLabel("Dauer:");
		dauerlabel.setFont(dauerlabel.getFont().deriveFont(16f));
		dauerlabel.setBounds(0, 120, 200, 40);
		dauertxt = new JTextField(dauer + " min");
		dauertxt.setFont(dauertxt.getFont().deriveFont(16f));
		dauertxt.setBounds(200, 120, 200, 40);

		JButton speichern = new JButton("Speichern");
		speichern.addActionListener(new MyOkListener());
		speichern.setBounds(0, 160, 140, 40);
		
		JButton kundeHinzufügen = new JButton("Kunde hinzufügen");
		kundeHinzufügen.setBounds(140, 160, 140, 40);
		kundeHinzufügen.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(TerminErstellenDialog.this, "Neuen Kunde erstellen");
			}
			
		});	
		
		JButton abbrechen = new JButton("Abbrechen");
		abbrechen.setBounds(280, 160, 140, 40);
		abbrechen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				TerminErstellenDialog.this.dispose();
			}
		});

		add(kunde);
		add(kundetxt);
		add(datum);
		add(datumtxt);
		add(uhrzeit);
		add(uhrzeittxt);
		add(dauerlabel);
		add(dauertxt);
		
		add(speichern);
		add(abbrechen);
		add(new JPanel());
		add(kundeHinzufügen);

		setVisible(true);

	}

	public static void main(String[] avgs) {

		new TerminErstellenDialog(5, new Date(), null, "Hallo");
	}
}
