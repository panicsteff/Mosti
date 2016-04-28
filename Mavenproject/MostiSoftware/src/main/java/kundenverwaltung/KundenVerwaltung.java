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
	private KundeFileManager kundeFileManager;
	
	public KundenVerwaltung(){
		
		setTitle("Kundenverwaltung");
		setSize(750, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menu;
		JMenuItem  mi;
		
		menubar.add(menu = new JMenu("Datei"));
		
		menu.add(mi = new JMenuItem("Kundenstamm öffnen..."));
		mi.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				FileDialog fd = new FileDialog(KundenVerwaltung.this, "Kundenstamm öffnen", FileDialog.LOAD);
				fd.setDirectory(".");
				fd.setVisible(true);
				try{
					String filename = fd.getDirectory() + fd.getFile();
					kundeFileManager = new KundeFileManager(filename);
					kundeTableModel.setKunden(kundeFileManager.load());
					kundeTableModel.fireTableDataChanged();
					miSpeichern.setEnabled(true);
				}
				catch(IOException ex){
					JOptionPane.showMessageDialog(KundenVerwaltung.this, "Feheler beim Öffnen.", "Feheler", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(KundenVerwaltung.this, "Ungültiges Format der Kundendatei.", "Fehler", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		miSpeichern = new JMenuItem("Kundenstamm speichern");
		miSpeichern.setEnabled(false);
		menu.add(miSpeichern);
		miSpeichern.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				try{
					kundeFileManager.save(kundeTableModel.getKunden());
				}
				catch(IOException ex){
					JOptionPane.showMessageDialog(KundenVerwaltung.this, "Fehler beim Speichern.", "Fehler", JOptionPane.ERROR_MESSAGE);
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
		
		/*kunde = new Kunde("Kaufmann", "Franz", Formats.DATE_FORMAT.parse("22.04.1941"), "Eichertweg 33", "93437", "Furth im Wald", Geschlecht.maennlich, true, 0.4);
		kunden.add(kunde);
		kunde = new Kunde("Siegl", "Regina", Formats.DATE_FORMAT.parse("14.12.1967"),  "Eichertweg 33", "93437", "Furth im Wald", Geschlecht.weiblich, false, 0.0);
		kunden.add(kunde);
		kunde = new Kunde("Siegl", "Katharina", Formats.DATE_FORMAT.parse("07.07.1996"), "Eichertweg 33", "93437", "Furth im Wald", Geschlecht.weiblich, false, 0.0);
		kunden.add(kunde);*/
		
		new KundenVerwaltung();
	}
}
