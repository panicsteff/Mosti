package gui.terminplanung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Date;

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

import logik.terminplanung.Termin;
import logik.terminplanung.TerminHinzufügenLogik;

public class TerminHinzufügenFrame extends JFrame{

	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent event) {
			if (event.getClickCount() == 2) {
				int zeile = terminSelectionModel.getMinSelectionIndex();
				String datum = (String) fttm.getValueAt(zeile, 0);
				int terminuhrzeit = (Integer) fttm.getValueAt(zeile, 1);
				long d = terminhinzufügenLogik.formatieren(datum);
				int as = terminhinzufügenLogik.berechneAnzeigeSeite(terminuhrzeit);
				new TagFrame(d, as, TerminHinzufügenFrame.this, länge);
				TerminHinzufügenFrame.this.dispose();
			}
		}
	}
	//MVVM Pattern
	
	class MyBerechnenHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				String s = txtmenge.getText();
				if(s.equals("")){
					JOptionPane.showMessageDialog(TerminHinzufügenFrame.this, "Bitte geben sie eine Obstmenge ein");
				}else{
					länge = terminhinzufügenLogik.berechneTermindauer(s);
					dauer.setEnabled(true);
					txtdauer.setEnabled(true);
					txtdauer.setText(länge + "");
					titlepane.setEnabled(true);
					spaeter.setEnabled(true);
					frueher.setEnabled(true);
					aktuellerTag = heute;									//Default initialisierung
					freieTermine = terminhinzufügenLogik.freieTermineSuchen(heute.getTime());   
					fttm = new FreieTermineTableModel(freieTermine, heute);
					verfügbarTabelle.setModel(fttm);
					tcm = verfügbarTabelle.getColumnModel();
					tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
					tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
				}
				
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(TerminHinzufügenFrame.this, "Keine gültige Eingabe der Obstmenge");
			}
			
		}
	}
	
	class MyFrüherHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			aktuellerTag = new Date(terminhinzufügenLogik.vorherigenTagBerechnen(aktuellerTag.getTime()));
			freieTermine = terminhinzufügenLogik.freieTermineSuchen(aktuellerTag.getTime());   						//nächsten Tag anzeigen
			fttm = new FreieTermineTableModel(freieTermine, aktuellerTag);
			verfügbarTabelle.setModel(fttm);
			tcm = verfügbarTabelle.getColumnModel();
			tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
			tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
		}
	}
	
	class MySpäterHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			aktuellerTag = new Date(terminhinzufügenLogik.nächstenTagBerechnen(aktuellerTag.getTime()));
			freieTermine = terminhinzufügenLogik.freieTermineSuchen(aktuellerTag.getTime());   						//nächsten Tag anzeigen
			fttm = new FreieTermineTableModel(freieTermine, aktuellerTag);
			verfügbarTabelle.setModel(fttm);
			tcm = verfügbarTabelle.getColumnModel();
			tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
			tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
		}
		
	}
	
	
	private static final long serialVersionUID = 1L;
	private TerminHinzufügenLogik terminhinzufügenLogik;
	private ArrayList<Termin> freieTermine;
	private JTextField txtmenge;
	private JLabel dauer;
	private JTextField txtdauer;
	private FreieTermineTableModel fttm;
	private JTable verfügbarTabelle;
	private JPanel titlepane;
	private JButton spaeter;
	private JButton frueher;
	private ListSelectionModel terminSelectionModel;
	private TableColumnModel tcm;
	private Date heute;
	private Date aktuellerTag;
	private int länge = 0;

	
	public TerminHinzufügenFrame(long date){
		setBounds(350, 200, 300, 500);
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		terminhinzufügenLogik = new TerminHinzufügenLogik();
		heute = new Date(date);
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
		
		spaeter = new JButton("Nächster Tag");
		spaeter.addActionListener(new MySpäterHandler());
		spaeter.setBounds(170, 350, 150, 30);
		spaeter.setEnabled(false);
		add(spaeter);
		
		frueher = new JButton("Vorheriger Tag");
		frueher.addActionListener(new MyFrüherHandler());
		frueher.setBounds(10, 350, 150, 30);
		frueher.setEnabled(false);
		add(frueher);
		
		setVisible(true);
	}
	
}
