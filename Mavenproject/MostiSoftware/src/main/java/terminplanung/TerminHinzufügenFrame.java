package terminplanung;

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

import kundenverwaltung.Formats;

public class TerminHinzufügenFrame extends JFrame{

	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent event) {
			if (event.getClickCount() == 2) {
				int zeile = terminSelectionModel.getMinSelectionIndex();
				String datum = (String) fttm.getValueAt(zeile, 0);
				Date d = new Date();
				try {
					d = Formats.DATE_FORMAT.parse(datum);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int terminid = (Integer) fttm.getValueAt(zeile, 1);
				int anzeigeseite = berechneAnzeigeSeite(terminid);
				new TagFrame(d,anzeigeseite, TerminHinzufügenFrame.this);
			}
		}
	}
	
	class MyBerechnenHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int länge = 0;
			try {
				String s = txtmenge.getText();
				länge = berechneTermindauer(s);
				dauer.setEnabled(true);
				txtdauer.setEnabled(true);
				txtdauer.setText(länge + "");
				titlepane.setEnabled(true);
				freieTermine = terminDb.freieTermineSuchen(new Date()); 
				//vom heutigen Tag aus mit arbeitsende und zeitslots
				fttm = new FreieTermineTableModel(freieTermine);
				verfügbarTabelle.setModel(fttm);
				tcm = verfügbarTabelle.getColumnModel();
				tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
				tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
				
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(TerminHinzufügenFrame.this, "Keine gültige Eingabe der Obstmenge");
			}
			
		}
	}
	
	
	private static final long serialVersionUID = 1L;
	private Konfigurationswerte k = new Konfigurationswerte();
	private ArrayList<Termin> freieTermine;
	private JTextField txtmenge;
	private JLabel dauer;
	private JTextField txtdauer;
	private TerminDB terminDb;
	private FreieTermineTableModel fttm;
	private JTable verfügbarTabelle;
	private JPanel titlepane;
	private ListSelectionModel terminSelectionModel;
	private TableColumnModel tcm;

	
	public TerminHinzufügenFrame(){
		setBounds(350, 200, 300, 500);
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		terminDb = new TerminDB();
		setLayout(null);
		
		JLabel menge = new JLabel("Obstmenge: ");
		menge.setBounds(10, 10, 100, 20);
		add(menge);
		
		txtmenge = new JTextField();
		txtmenge.setBounds(150, 10, 100, 20);
		add(txtmenge);
		
		JButton dauerberechnen = new JButton("Termindauer berechnen");
		dauerberechnen.setBounds(25, 50, 200, 20);
		dauerberechnen.addActionListener(new MyBerechnenHandler());
		add(dauerberechnen);
		
		dauer = new JLabel("Presszeit in Minuten:");
		dauer.setBounds(10, 90, 150, 20);
		dauer.setEnabled(false);
		add(dauer);
		
		txtdauer = new JTextField();
		txtdauer.setBounds(150, 90, 100, 20);
		txtdauer.setEnabled(false);
		add(txtdauer);
		
		verfügbarTabelle = new JTable();
		verfügbarTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		terminSelectionModel = verfügbarTabelle.getSelectionModel();
		terminSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
	
	int berechneTermindauer(String s) throws ParseException{
		
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
	
	public int getTerminlänge(){
		String s = txtdauer.getText();
		int dauer = 0;
		try{
			 dauer= Integer.parseInt(s);
		} catch (Exception e){
			e.printStackTrace();
		}
		return dauer;
	}
	
	public static void main(String[] avgs){
		new TerminHinzufügenFrame();
	}
	
}
