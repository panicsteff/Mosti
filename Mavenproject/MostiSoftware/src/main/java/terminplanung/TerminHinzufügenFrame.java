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
				int terminzeile = (Integer) fttm.getValueAt(zeile, 1);
				Date d = new Date();
				try {
					d = Formats.DATE_FORMAT.parse(datum);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int as = terminlogik.berechneAnzeigeSeite(terminzeile);
				new TagFrame(d, as, TerminHinzufügenFrame.this);
				TerminHinzufügenFrame.this.dispose();
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
				freieTermine = terminlogik.freieTermineSuchen(new Date());   
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
	private TerminLogik terminlogik;
	private ArrayList<Termin> freieTermine;
	private JTextField txtmenge;
	private JLabel dauer;
	private JTextField txtdauer;
	private FreieTermineTableModel fttm;
	private JTable verfügbarTabelle;
	private JPanel titlepane;
	private ListSelectionModel terminSelectionModel;
	private TableColumnModel tcm;

	
	public TerminHinzufügenFrame(){
		setBounds(350, 200, 300, 500);
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		terminlogik = new TerminLogik();
		setLayout(null);
		
		JLabel menge = new JLabel("Obstmenge in Zentner: ");
		menge.setBounds(10, 10, 150, 20);
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
		double dauer = obstmenge*5;
		int zeitslot = k.getZeitslot();
		if(dauer%zeitslot == 0){						
			return (int) dauer;
		} else{													//evtl. Dauer in vielfaches der Zeitslots umrechnen
			int h = (int) dauer/zeitslot;
			dauer = (h+1)*zeitslot;
			return (int) dauer;
		}
	}
	
	
	int getTerminlänge(){
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
