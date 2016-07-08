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
				new TagFrame(d, as, TerminHinzufügenFrame.this, länge, menge, flasche.isSelected());
				TerminHinzufügenFrame.this.dispose();
			}
		}
	}
	
	
	class MyBerechnenHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				String s = txtmenge.getText();
				if(s.equals("")){
					JOptionPane.showMessageDialog(TerminHinzufügenFrame.this, "Bitte geben sie eine Obstmenge ein");
				}else{
					try{
						menge = Integer.parseInt(s);
					}catch (Exception ex){
						JOptionPane.showMessageDialog(TerminHinzufügenFrame.this, "Ungültige Obstmenge");
					}
					länge = terminhinzufügenLogik.berechneTermindauer(s, flasche.isSelected());
					dauer.setEnabled(true);
					txtdauer.setEnabled(true);
					txtdauer.setText(länge + "");
					titlepane.setEnabled(true);
					spaeter.setEnabled(true);
					frueher.setEnabled(true);
					aktuellerTag = heute;									//Default initialisierung
					freieTermine = terminhinzufügenLogik.freieTermineSuchen(heute.getTime());   
					fttm = new FreieTermineTableModel(freieTermine, heute, länge);
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
			fttm = new FreieTermineTableModel(freieTermine, aktuellerTag, länge);
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
			fttm = new FreieTermineTableModel(freieTermine, aktuellerTag, länge);
			verfügbarTabelle.setModel(fttm);
			tcm = verfügbarTabelle.getColumnModel();
			tcm.getColumn(1).setCellRenderer(new TermineCellRenderer());
			tcm.getColumn(2).setCellRenderer(new TermineCellRenderer());
		}
		
	}
	
	
	private static final long serialVersionUID = 1L;
	private TerminHinzufügenLogik terminhinzufügenLogik;
	private ArrayList<Termin> freieTermine;
	private JCheckBox flasche;
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
	private int menge;

	
	public TerminHinzufügenFrame(long date){
		setBounds(450, 350, 300, 500);
		setLocationRelativeTo(getParent());
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(370, 500);
		
		terminhinzufügenLogik = new TerminHinzufügenLogik();
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
		
		flasche = new JCheckBox("Abfüllung in Flaschen");
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
		
		verfügbarTabelle = new JTable();
		verfügbarTabelle.getTableHeader().setFont(verfügbarTabelle.getFont().deriveFont(16f));
		verfügbarTabelle.setFont(verfügbarTabelle.getFont().deriveFont(16f));
		verfügbarTabelle.setRowHeight(30);
		
		
		terminSelectionModel = verfügbarTabelle.getSelectionModel();
		terminSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		verfügbarTabelle.addMouseListener(new MyMouseListener());
		
		JScrollPane scrollpane = new JScrollPane(verfügbarTabelle);
		titlepane = new JPanel();
		titlepane.setBounds(10, 210, 300, 200);
		titlepane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Freie Termine"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);
		titlepane.setEnabled(false);
		add(titlepane);
		
		spaeter = new JButton("Nächster Tag");
		spaeter.setFont(spaeter.getFont().deriveFont(16f));
		spaeter.addActionListener(new MySpäterHandler());
		spaeter.setBounds(140, 450, 120, 30);
		spaeter.setEnabled(false);
		add(spaeter);
		
		frueher = new JButton("Vorheriger Tag");
		frueher.setFont(frueher.getFont().deriveFont(16f));
		frueher.addActionListener(new MyFrüherHandler());
		frueher.setBounds(10, 450, 120, 30);
		frueher.setEnabled(false);
		add(frueher);
		
		setVisible(true);
	}
	
	public static void main(String[] avgs){
		new TerminHinzufügenFrame(1l);
	}
	
}
