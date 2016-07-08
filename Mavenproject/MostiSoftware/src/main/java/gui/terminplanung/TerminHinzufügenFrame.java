package gui.terminplanung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import logik.terminplanung.TerminHinzuf�genLogik;

public class TerminHinzuf�genFrame extends JFrame{

	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent event) {
			if (event.getClickCount() == 2) {
				int zeile = terminSelectionModel.getMinSelectionIndex();
				String datum = (String) fttm.getValueAt(zeile, 0);
				int terminuhrzeit = (Integer) fttm.getValueAt(zeile, 1);
				long d = terminhinzuf�genLogik.formatieren(datum);
				int as = terminhinzuf�genLogik.berechneAnzeigeSeite(terminuhrzeit);
				new TagFrame(d, as, TerminHinzuf�genFrame.this, l�nge, menge, flasche.isSelected());
				TerminHinzuf�genFrame.this.dispose();
			}
		}
	}
	
	
	class MyBerechnenHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				String s = txtmenge.getText();
				if(s.equals("")){
					JOptionPane.showMessageDialog(TerminHinzuf�genFrame.this, "Bitte geben sie eine Obstmenge ein");
				}else{
					try{
						menge = Integer.parseInt(s);
					}catch (Exception ex){
						JOptionPane.showMessageDialog(TerminHinzuf�genFrame.this, "Ung�ltige Obstmenge");
					}
					l�nge = terminhinzuf�genLogik.berechneTermindauer(s, flasche.isSelected());
					dauer.setEnabled(true);
					txtdauer.setEnabled(true);
					txtdauer.setText(l�nge + "");
					titlepane.setEnabled(true);
					spaeter.setEnabled(true);
					frueher.setEnabled(true);
					aktuellerTag = heute;									//Default initialisierung
					freieTermine = terminhinzuf�genLogik.freieTermineSuchen(heute.getTime());   
					fttm = new FreieTermineTableModel(freieTermine, heute, l�nge);
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
	
	class MyFr�herHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			aktuellerTag = new Date(terminhinzuf�genLogik.vorherigenTagBerechnen(aktuellerTag.getTime()));
			freieTermine = terminhinzuf�genLogik.freieTermineSuchen(aktuellerTag.getTime());   						//n�chsten Tag anzeigen
			fttm = new FreieTermineTableModel(freieTermine, aktuellerTag, l�nge);
			verf�gbarTabelle.setModel(fttm);
			tcm = verf�gbarTabelle.getColumnModel();
			tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
			tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
		}
	}
	
	class MySp�terHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			aktuellerTag = new Date(terminhinzuf�genLogik.n�chstenTagBerechnen(aktuellerTag.getTime()));
			freieTermine = terminhinzuf�genLogik.freieTermineSuchen(aktuellerTag.getTime());   						//n�chsten Tag anzeigen
			fttm = new FreieTermineTableModel(freieTermine, aktuellerTag, l�nge);
			verf�gbarTabelle.setModel(fttm);
			tcm = verf�gbarTabelle.getColumnModel();
			tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
			tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
		}
		
	}
	
	
	private static final long serialVersionUID = 1L;
	private TerminHinzuf�genLogik terminhinzuf�genLogik;
	private ArrayList<Termin> freieTermine;
	private JCheckBox flasche;
	private JTextField txtmenge;
	private JLabel dauer;
	private JTextField txtdauer;
	private FreieTermineTableModel fttm;
	private JTable verf�gbarTabelle;
	private JPanel titlepane;
	private JButton spaeter;
	private JButton frueher;
	private ListSelectionModel terminSelectionModel;
	private TableColumnModel tcm;
	private Date heute;
	private Date aktuellerTag;
	private int l�nge = 0;
	private int menge;

	
	public TerminHinzuf�genFrame(long date){
		setBounds(450, 350, 300, 500);
		setLocationRelativeTo(getParent());
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(370, 500);
		
		terminhinzuf�genLogik = new TerminHinzuf�genLogik();
		heute = new Date(date);
		setLayout(null);
		
		JLabel menge = new JLabel("Obstmenge in Zentner: ");
		menge.setFont(menge.getFont().deriveFont(16f));
		menge.setBounds(10, 10, 200, 30);
		add(menge);
		
		txtmenge = new JTextField();
		txtmenge.setFont(txtmenge.getFont().deriveFont(16f));
		txtmenge.setBounds(210, 10, 100, 30);
		add(txtmenge);
		
		flasche = new JCheckBox("Abf�llung in Flaschen");
		flasche.setFont(flasche.getFont().deriveFont(16f));
		flasche.setBounds(10, 60, 200, 30);
		add(flasche);
		
		JButton dauerberechnen = new JButton("Termindauer berechnen");
		dauerberechnen.setFont(dauerberechnen.getFont().deriveFont(16f));
		dauerberechnen.setBounds(25, 110, 300, 30);
		dauerberechnen.addActionListener(new MyBerechnenHandler());
		add(dauerberechnen);
		
		dauer = new JLabel("Presszeit in Minuten:");
		dauer.setFont(dauer.getFont().deriveFont(16f));
		dauer.setBounds(10, 160, 200, 30);
		dauer.setEnabled(false);
		add(dauer);
		
		txtdauer = new JTextField();
		txtdauer.setFont(txtdauer.getFont().deriveFont(16f));
		txtdauer.setBounds(210, 160, 100, 30);
		txtdauer.setEnabled(false);
		add(txtdauer);
		
		verf�gbarTabelle = new JTable();
		verf�gbarTabelle.getTableHeader().setFont(verf�gbarTabelle.getFont().deriveFont(16f));
		verf�gbarTabelle.setFont(verf�gbarTabelle.getFont().deriveFont(16f));
		verf�gbarTabelle.setRowHeight(30);
		
		
		terminSelectionModel = verf�gbarTabelle.getSelectionModel();
		terminSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		verf�gbarTabelle.addMouseListener(new MyMouseListener());
		
		JScrollPane scrollpane = new JScrollPane(verf�gbarTabelle);
		titlepane = new JPanel();
		titlepane.setBounds(10, 210, 300, 200);
		titlepane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Freie Termine"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);
		titlepane.setEnabled(false);
		add(titlepane);
		
		spaeter = new JButton("N�chster Tag");
		spaeter.setFont(spaeter.getFont().deriveFont(16f));
		spaeter.addActionListener(new MySp�terHandler());
		spaeter.setBounds(140, 450, 120, 30);
		spaeter.setEnabled(false);
		add(spaeter);
		
		frueher = new JButton("Vorheriger Tag");
		frueher.setFont(frueher.getFont().deriveFont(16f));
		frueher.addActionListener(new MyFr�herHandler());
		frueher.setBounds(10, 450, 120, 30);
		frueher.setEnabled(false);
		add(frueher);
		
		setVisible(true);
	}
	
	public static void main(String[] avgs){
		new TerminHinzuf�genFrame(1l);
	}
	
}
