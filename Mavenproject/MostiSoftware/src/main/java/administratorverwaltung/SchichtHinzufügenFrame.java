package administratorverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import mitarbeiterverwaltung.Formats;

public class SchichtHinzufügenFrame extends JFrame{
	
	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent event) {
			if (event.getClickCount() == 2) {
				int zeile = schichtSelectionModel.getMinSelectionIndex();
				String datum = (String) fstm.getValueAt(zeile, 0);
				Date d = new Date();
				try {
					d = Formats.DATE_FORMAT.parse(datum);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int schichtid = (Integer) fstm.getValueAt(zeile, 1);
				int anzeigeseite = berechneAnzeigeSeite(schichtid);
				new SchichtTagFrame(d,anzeigeseite, SchichtHinzufügenFrame.this);
			}
		}
	}
	
	class MyBerechnenHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int länge = 0;
			try {
				String s = txtmenge.getText();
				länge = berechneSchichtdauer(s);
				dauer.setEnabled(true);
				txtdauer.setEnabled(true);
				txtdauer.setText(länge + "");
				titlepane.setEnabled(true);
				//freieSchicht = schichtplanDb.freieSchichtSuchen(new Date()); 
				//vom heutigen Tag aus mit arbeitsende und zeitslots
				fstm = new FreieSchichtTableModel(freieSchicht);
				verfügbarTabelle.setModel(fstm);
				tcm = verfügbarTabelle.getColumnModel();
				tcm.getColumn(1).setCellRenderer(new SchichtCellRenderer());
				tcm.getColumn(2).setCellRenderer(new SchichtCellRenderer());
				
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(SchichtHinzufügenFrame.this, "Keine gültige Eingabe der Schicht");
			}
			
		}
	}
	
	
	private static final long serialVersionUID = 1L;
	private Konfigurationswerte k = new Konfigurationswerte();
	private ArrayList<Schicht> freieSchicht;
	private JTextField txtmenge;
	private JLabel dauer;
	private JTextField txtdauer;
	private SchichtplanDB schichtplanDb;
	private FreieSchichtTableModel fstm;
	private JTable verfügbarTabelle;
	private JPanel titlepane;
	private ListSelectionModel schichtSelectionModel;
	private TableColumnModel tcm;

	
	public SchichtHinzufügenFrame(){
		setBounds(350, 200, 300, 500);
		setTitle("Neue Schicht");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		schichtplanDb = new SchichtplanDB();
		setLayout(null);
		
		JLabel menge = new JLabel("Schicht: ");
		menge.setBounds(10, 10, 100, 20);
		add(menge);
		
		txtmenge = new JTextField();
		txtmenge.setBounds(150, 10, 100, 20);
		add(txtmenge);
		
		JButton dauerberechnen = new JButton("Schichtdauer berechnen");
		dauerberechnen.setBounds(25, 50, 200, 20);
		dauerberechnen.addActionListener(new MyBerechnenHandler());
		add(dauerberechnen);
		
		dauer = new JLabel("Arbeitszeit in Minuten:");
		dauer.setBounds(10, 90, 150, 20);
		dauer.setEnabled(false);
		add(dauer);
		
		txtdauer = new JTextField();
		txtdauer.setBounds(150, 90, 100, 20);
		txtdauer.setEnabled(false);
		add(txtdauer);
		
		verfügbarTabelle = new JTable();
		verfügbarTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		schichtSelectionModel = verfügbarTabelle.getSelectionModel();
		schichtSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		verfügbarTabelle.addMouseListener(new MyMouseListener());
		
		JScrollPane scrollpane = new JScrollPane(verfügbarTabelle);
		titlepane = new JPanel();
		titlepane.setBounds(10, 130, 200, 200);
		titlepane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Freie Termine"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);
		titlepane.setEnabled(false);
		add(titlepane);
		
		setVisible(true);
	}
	
	int berechneSchichtdauer(String s) throws ParseException{
		
		int obstmenge = Integer.parseInt(s);
		double dauer = obstmenge/10;
		int zeitslot = k.getZeitslot();
		if(dauer%zeitslot == 0){
			return (int) dauer;
		} else{
			int h = (int) dauer/zeitslot;
			dauer = (h+1)*zeitslot;
			return (int) dauer;
		}
	}
	
	private  int berechneAnzeigeSeite(int terminId){
		for(int i = 1;i<= k.getAufteilung(); i++){
			if(terminId<=k.getZeilenanzahlProSeite()*i){
				int anzeigeseite = i;
				return anzeigeseite;
			}
		}
		return k.getAufteilung();
		
	}
	
	int getSchichtlänge(){
		String s = txtdauer.getText();
		int dauer = 0;
		try{
			 dauer= Integer.parseInt(s);
		} catch (Exception e){
			e.printStackTrace();
		}
		return dauer;
	}
	

}
