package kundenverwaltung;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;


public class KundenVerwaltung extends JFrame{
	private KundeTableModel kundeTableModel;
	private ListSelectionModel kundeSelectionModel;
	private JMenuItem miKundeBearbeiten;
	private JMenuItem miSpeichern;
	private KundeDB kundeDb;
	
	public KundenVerwaltung(){
		
		setTitle("Kundenverwaltung");
		setSize(750, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		kundeDb = new KundeDB();
	
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menu;
		JMenuItem  mi;
		
		menubar.add(menu = new JMenu("Datei"));
		
		menu.add(mi = new JMenuItem("Kundenstamm öffnen..."));
		mi.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
	
				try{
					ArrayList<Kunde> kundenliste = kundeDb.kundenLaden();
					kundeTableModel.setKunden(kundenliste);
					kundeTableModel.fireTableDataChanged();
					miSpeichern.setEnabled(true);
				}
				catch(Exception ex){
					System.out.println(ex);
				}
				
			}
		});
		
		miSpeichern = new JMenuItem("Kundenstamm speichern");
		miSpeichern.setEnabled(false);
		menu.add(miSpeichern);
		miSpeichern.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				try{
					kundeDb.kundenSpeichern(kundeTableModel.getKunden());
				}
				catch(Exception ex){
					System.out.println(e);
				}
			}
		});
		
		miKundeBearbeiten = new JMenuItem("Kunde bearbeiten");
		miKundeBearbeiten.setEnabled(false);
		menu.add(miKundeBearbeiten);
		miKundeBearbeiten.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				editKunde();
			}
		});
		
		menu.add(new JSeparator());
		
		menu.add(mi = new JMenuItem("Beenden"));
		mi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		kundeTableModel = new KundeTableModel();
		JTable table = new JTable(kundeTableModel);
		kundeSelectionModel = table.getSelectionModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		kundeSelectionModel.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent lsevent){
				if(lsevent.getFirstIndex() == -1){
					miKundeBearbeiten.setEnabled(false);
				}else{
					miKundeBearbeiten.setEnabled(true);
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent event){
				if(event.getClickCount() == 2){
					editKunde();
				}
			}
		});
		
		TableColumn colrabatt = table.getColumnModel().getColumn(5);
		TableColumn colanrede = table.getColumnModel().getColumn(0);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollpane = new JScrollPane(table);
		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tabellenansicht"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);
		
		add(titlepane);
		setVisible(true);
	}
	
	private void editKunde(){
		int row = kundeSelectionModel.getMinSelectionIndex();
		new KundeBearbeitenDialog(this, kundeTableModel.getKunde(row));
		kundeTableModel.fireTableRowsUpdated(row, row);
	}
	
	public static void main(String[] args) throws ParseException{
		List<Kunde> kunden = new ArrayList<Kunde>();
		Kunde kunde;
		
//		kunde = new Kunde("Kaufmann", "Franz", "Eichertweg 33", "93437", "Furth im Wald","086581234");
//		kunden.add(kunde);
//		kunde = new Kunde("Siegl", "Regina",  "Eichertweg 33", "93437", "Furth im Wald", "086459876");
//		kunden.add(kunde);
//		kunde = new Kunde("Siegl", "Katharina", "Eichertweg 33", "93437", "Furth im Wald", "094145678");
//		kunden.add(kunde);
		
		new KundenVerwaltung();
	}
}
