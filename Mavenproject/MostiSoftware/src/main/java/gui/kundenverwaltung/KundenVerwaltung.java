package gui.kundenverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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
import javax.swing.table.TableColumnModel;

import logik.kundenverwaltung.Kunde;
import logik.kundenverwaltung.KundeTableModel;
import persistenz.KundeDB;

public class KundenVerwaltung extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private KundeTableModel kundeTableModel;
	private ListSelectionModel kundeSelectionModel;
	private JMenuItem miKundeBearbeiten;
	private JMenuItem miKundeHinzufuegen;
	private JMenuItem miKundeLoeschen;
	private KundeDB kundeDb;
	private ArrayList<Kunde> kundenliste;

	public KundenVerwaltung() {

		setTitle("Kundenverwaltung");
		setSize(1300, 600);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		kundeDb = new KundeDB();

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu menu;
		JMenuItem mi;

		menubar.add(menu = new JMenu("Datei"));
		menu.setFont(menu.getFont().deriveFont(16f));
		menu.add(new JSeparator());

		menu.add(mi = new JMenuItem("Beenden"));
		mi.setFont(mi.getFont().deriveFont(16f));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		menubar.add(menu = new JMenu("Bearbeiten"));
		menu.setFont(menu.getFont().deriveFont(16f));
		
		miKundeHinzufuegen = new JMenuItem("Neuen Kunden hinzufügen");
		miKundeHinzufuegen.setFont(miKundeHinzufuegen.getFont().deriveFont(16f));
		miKundeHinzufuegen.setEnabled(true);
		menu.add(miKundeHinzufuegen);
		miKundeHinzufuegen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new KundeHinzufügenFrame(KundenVerwaltung.this, kundeTableModel.getKunden());
				kundeTableModel.fireTableDataChanged();
				sortiereKundenliste(kundenliste);
			}
		});
		
		miKundeBearbeiten = new JMenuItem("Kunde bearbeiten");
		miKundeBearbeiten.setFont(miKundeBearbeiten.getFont().deriveFont(16f));
		miKundeBearbeiten.setEnabled(false);
		menu.add(miKundeBearbeiten);
		miKundeBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editKunde();
			}
		});
		
		menu.add(new JSeparator());
		
		
		miKundeLoeschen = new JMenuItem("Kunde löschen");
		miKundeLoeschen.setFont(miKundeLoeschen.getFont().deriveFont(16f));
		miKundeLoeschen.setEnabled(false);
		menu.add(miKundeLoeschen);
		miKundeLoeschen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int pos = kundeSelectionModel.getMinSelectionIndex();
				Kunde k = kundeTableModel.getKunde(pos);
				int result = JOptionPane.showConfirmDialog(KundenVerwaltung.this, "Möchten Sie den Kunden " + k.getVorname() + " " + k.getNachname() + " wirklich löschen?",
						"Frage", JOptionPane.YES_NO_OPTION);
				if (result != JOptionPane.YES_OPTION)
					return;
				kundeDb.kundeLöschen(k.getKundenID());
				Date d = new Date();
				kundeDb.termineUpdaten(k.getKundenID(), d.getTime());
				kundeTableModel.deletKunde(k);
				kundeTableModel.fireTableRowsDeleted(pos, pos);
			}
		});


		kundeTableModel = new KundeTableModel();
		kundenliste = new ArrayList<Kunde>();
		try {
			kundenliste = kundeDb.kundenLaden();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		sortiereKundenliste(kundenliste);
		kundeTableModel.setKunden(kundenliste);
		JTable table = new JTable(kundeTableModel);
		table.setSize(800, 300);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setMinWidth(200);	
		tcm.getColumn(1).setMinWidth(200);	
		tcm.getColumn(2).setMinWidth(200);	
		tcm.getColumn(3).setMinWidth(100);	
		tcm.getColumn(4).setMinWidth(50);	
		tcm.getColumn(5).setMinWidth(200);	
		tcm.getColumn(6).setMinWidth(200);
		table.setRowHeight(30);
		table.setFont(table.getFont().deriveFont(16f));
		table.getTableHeader().setFont(
				table.getTableHeader().getFont().deriveFont(16f));
		kundeSelectionModel = table.getSelectionModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		kundeSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent lsevent) {
						if (lsevent.getFirstIndex() == -1) {
							miKundeBearbeiten.setEnabled(false);
							miKundeLoeschen.setEnabled(false);
						} else {
							miKundeBearbeiten.setEnabled(true);
							miKundeLoeschen.setEnabled(true);
						}
					}
				});

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (event.getClickCount() == 2) {
					editKunde();
				}
			}
		});

		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollpane = new JScrollPane(table);
		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Tabellenansicht"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);

		add(titlepane);
		setVisible(true);
	}
	
	public void sortiereKundenliste(ArrayList<Kunde> liste){            // alphabetische Sortierung nach Nachnamen der Kunden
		Collections.sort(liste, new Comparator<Kunde>() {
			public int compare(Kunde o1, Kunde o2) {
				return o1.getNachname().compareTo(o2.getNachname());
			}
			
		});
	}

	private void editKunde() {
		int row = kundeSelectionModel.getMinSelectionIndex();
		new KundeBearbeitenDialog(this, kundeTableModel.getKunde(row));
		kundeTableModel.fireTableRowsUpdated(row, row);
		sortiereKundenliste(kundenliste);
		kundeDb.kundenSpeichern(kundeTableModel.getKunden());
	}
	
	public static void main(String[] avgs){
		new KundenVerwaltung();
	}


}
