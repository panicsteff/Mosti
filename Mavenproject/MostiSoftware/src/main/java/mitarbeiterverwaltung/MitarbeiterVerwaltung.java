package mitarbeiterverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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


public class MitarbeiterVerwaltung extends JFrame {
	
	private static final long serialVersionUID = 1L;  // ??? 
	private MitarbeiterTableModel mitarbeiterTableModel;
	private ListSelectionModel mitarbeiterSelectionModel;
	private JMenuItem miMitarbeiterHinzufügen;
	private JMenuItem miMitarbeiterBearbeiten;
	private JMenuItem miMitarbeiterLoeschen;
	private JMenuItem miSpeichern;
	private MitarbeiterDB mitarbeiterDb; // mach ma des a mit DB ?? 


	public MitarbeiterVerwaltung() {

		setTitle("Mitarbeiterverwaltung");
		setSize(750, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mitarbeiterDb = new MitarbeiterDB();

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu menu;
		JMenuItem mi;

		menubar.add(menu = new JMenu("Datei"));

		menu.add(mi = new JMenuItem("Mitarbeiterliste öffnen"));
		mi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					ArrayList<Mitarbeiter> mitarbeiterliste = mitarbeiterDb.mitarbeiterLaden();
					mitarbeiterTableModel.setMitarbeiter(mitarbeiterliste);
					mitarbeiterTableModel.fireTableDataChanged();
					miSpeichern.setEnabled(true);
					miMitarbeiterHinzufügen.setEnabled(true);		
					miMitarbeiterLoeschen.setEnabled(true);
					
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(MitarbeiterVerwaltung.this,
							"Pfad zur Datenbank fehlerhaft");
				} catch (Exception ex){
					System.out.println(ex);
				}

			}
		});

		miSpeichern = new JMenuItem("Mitarbeiterliste speichern");
		miSpeichern.setEnabled(false);
		menu.add(miSpeichern);
		miSpeichern.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					mitarbeiterDb.mitarbeiterSpeichern(mitarbeiterTableModel.getMitarbeiter());
				} catch (Exception ex) {
					System.out.println(e);
				}
			}
		});
		
		
		menu.add(new JSeparator());

		menu.add(mi = new JMenuItem("Beenden"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		menubar.add(menu = new JMenu("Bearbeiten"));
		
		miMitarbeiterHinzufügen = new JMenuItem("Neuen Mitarbeiter hinzufügen");
		miMitarbeiterHinzufügen.setEnabled(false);
		menu.add(miMitarbeiterHinzufügen);
		miMitarbeiterHinzufügen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new MitarbeiterHinzufügenFrame(MitarbeiterVerwaltung.this, MitarbeiterTableModel.getMitarbeiter());
				mitarbeiterTableModel.fireTableDataChanged();
			}
		});
		
		miMitarbeiterBearbeiten = new JMenuItem("Mitarbeiter bearbeiten");
		miMitarbeiterBearbeiten.setEnabled(false);
		menu.add(miMitarbeiterBearbeiten);
		miMitarbeiterBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editMitarbeiter();
			}
		});
		
		miMitarbeiterLoeschen = new JMenuItem("Mitarbeiter löschen");
		miMitarbeiterLoeschen.setEnabled(false);
		menu.add(miMitarbeiterLoeschen);
		miMitarbeiterLoeschen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//deleteMitarbeiter();
			}
		});

		

		mitarbeiterTableModel = new MitarbeiterTableModel();
		JTable table = new JTable(mitarbeiterTableModel);
		mitarbeiterSelectionModel = table.getSelectionModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		mitarbeiterSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent lsevent) {
						if (lsevent.getFirstIndex() == -1) {
							miMitarbeiterBearbeiten.setEnabled(false);
						} else {
							miMitarbeiterBearbeiten.setEnabled(true);
						}
					}
				});

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (event.getClickCount() == 2) {
					editMitarbeiter();
				}
			}
		});

		//TableColumn colrabatt = table.getColumnModel().getColumn(5);
		//TableColumn colanrede = table.getColumnModel().getColumn(0);

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

	private void editMitarbeiter() {
		int row = mitarbeiterSelectionModel.getMinSelectionIndex();
		new MitarbeiterBearbeitenDialog(this, mitarbeiterTableModel.getMitarbeiter(row));
		mitarbeiterTableModel.fireTableRowsUpdated(row, row);
	}

	
	/*public void deleteMitarbeiter(){
		
	}*/
	
	
	public static void main (String[] avgs){
		new MitarbeiterVerwaltung();
	}
}
