package kundenverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

public class KundenVerwaltung extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private KundeTableModel kundeTableModel;
	private ListSelectionModel kundeSelectionModel;
	private JMenuItem miKundeBearbeiten;
	private JMenuItem miKundeHinzufuegen;
	private JMenuItem miKundeLoeschen;
	private KundeDB kundeDb;

	public KundenVerwaltung() {

		setTitle("Kundenverwaltung");
		setSize(750, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		kundeDb = new KundeDB();

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu menu;
		JMenuItem mi;

		menubar.add(menu = new JMenu("Datei"));
		
		miKundeLoeschen = new JMenuItem("Kunde löschen");
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

		menu.add(new JSeparator());
		
		miKundeHinzufuegen = new JMenuItem("Neuen Kunden hinzufügen");
		miKundeHinzufuegen.setEnabled(true);
		menu.add(miKundeHinzufuegen);
		miKundeHinzufuegen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new KundeHinzufügenFrame(KundenVerwaltung.this, kundeTableModel.getKunden());
				kundeTableModel.fireTableDataChanged();
			}
		});
		
		miKundeBearbeiten = new JMenuItem("Kunde bearbeiten");
		miKundeBearbeiten.setEnabled(false);
		menu.add(miKundeBearbeiten);
		miKundeBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editKunde();
			}
		});

		menu.add(new JSeparator());

		menu.add(mi = new JMenuItem("Beenden"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		kundeTableModel = new KundeTableModel();
		ArrayList<Kunde> kundenliste = new ArrayList<Kunde>();
		try {
			kundenliste = kundeDb.kundenLaden();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		kundeTableModel.setKunden(kundenliste);
		JTable table = new JTable(kundeTableModel);
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

	private void editKunde() {
		int row = kundeSelectionModel.getMinSelectionIndex();
		new KundeBearbeitenDialog(this, kundeTableModel.getKunde(row));
		kundeTableModel.fireTableRowsUpdated(row, row);
		kundeDb.kundenSpeichern(kundeTableModel.getKunden());
	}
	
	public static void main(String[] avgs){
		new KundenVerwaltung();
	}


}
