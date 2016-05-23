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

public class TerminHinzuf�genFrame extends JFrame{

	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent event) {
			if (event.getClickCount() == 2) {
				int zeile = terminSelectionModel.getMinSelectionIndex();
				String datum = (String) fttm.getValueAt(zeile, 0);
				int terminzeile = (Integer) fttm.getValueAt(zeile, 1);
				Date d = terminhinzuf�genLogik.formatieren(datum);
				int as = terminhinzuf�genLogik.berechneAnzeigeSeite(terminzeile);
				new TagFrame(d, as, TerminHinzuf�genFrame.this, l�nge);
				TerminHinzuf�genFrame.this.dispose();
			}
		}
	}
	//MVVM Pattern
	
	class MyBerechnenHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				String s = txtmenge.getText();
				if(s.equals("")){
					JOptionPane.showMessageDialog(TerminHinzuf�genFrame.this, "Bitte geben sie eine Obstmenge ein");
				}else{
					l�nge = terminhinzuf�genLogik.berechneTermindauer(s);
					dauer.setEnabled(true);
					txtdauer.setEnabled(true);
					txtdauer.setText(l�nge + "");
					titlepane.setEnabled(true);
					heute = new Date();
					aktuellerTag = heute;									//Default initialisierung
					freieTermine = terminhinzuf�genLogik.freieTermineSuchen(heute);   
					fttm = new FreieTermineTableModel(freieTermine, heute);
					verf�gbarTabelle.setModel(fttm);
					tcm = verf�gbarTabelle.getColumnModel();
					tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
					tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
				}
				
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(TerminHinzuf�genFrame.this, "Keine g�ltige Eingabe der Obstmenge");
			}
			
		}
	}
	
	
	private static final long serialVersionUID = 1L;
	private TerminHinzuf�genLogik terminhinzuf�genLogik;
	private ArrayList<Termin> freieTermine;
	private JTextField txtmenge;
	private JLabel dauer;
	private JTextField txtdauer;
	private FreieTermineTableModel fttm;
	private JTable verf�gbarTabelle;
	private JPanel titlepane;
	private ListSelectionModel terminSelectionModel;
	private TableColumnModel tcm;
	private Date heute;
	private Date aktuellerTag;
	private int l�nge = 0;

	
	public TerminHinzuf�genFrame(){
		setBounds(350, 200, 300, 500);
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		terminhinzuf�genLogik = new TerminHinzuf�genLogik();
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
		
		verf�gbarTabelle = new JTable();
		verf�gbarTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		terminSelectionModel = verf�gbarTabelle.getSelectionModel();
		terminSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		verf�gbarTabelle.addMouseListener(new MyMouseListener());
		
		JScrollPane scrollpane = new JScrollPane(verf�gbarTabelle);
		titlepane = new JPanel();
		titlepane.setBounds(10, 130, 200, 200);
		titlepane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Freie Termine"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);
		titlepane.setEnabled(false);
		add(titlepane);
		
		JButton spaeter = new JButton("N�chster Tag");
		spaeter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aktuellerTag = terminhinzuf�genLogik.n�chstenTagBerechnen(aktuellerTag);
				freieTermine = terminhinzuf�genLogik.freieTermineSuchen(aktuellerTag);   						//n�chsten Tag anzeigen
				fttm = new FreieTermineTableModel(freieTermine, aktuellerTag);
				verf�gbarTabelle.setModel(fttm);
				tcm = verf�gbarTabelle.getColumnModel();
				tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
				tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
			}
		});
		spaeter.setBounds(170, 350, 150, 30);
		add(spaeter);
		
		JButton fr�her = new JButton("Vorheriger Tag");
		fr�her.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aktuellerTag = terminhinzuf�genLogik.vorherigenTagBerechnen(aktuellerTag);
				freieTermine = terminhinzuf�genLogik.freieTermineSuchen(aktuellerTag);   						//n�chsten Tag anzeigen
				fttm = new FreieTermineTableModel(freieTermine, aktuellerTag);
				verf�gbarTabelle.setModel(fttm);
				tcm = verf�gbarTabelle.getColumnModel();
				tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
				tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
			}
		});
		fr�her.setBounds(10, 350, 150, 30);
		add(fr�her);
		
		setVisible(true);
	}
	
}
