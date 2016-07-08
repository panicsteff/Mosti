package gui.mitarbeiterverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

import logik.mitarbeiterverwaltung.Mitarbeiter;
import logik.mitarbeiterverwaltung.MitarbeiterTableModel;
import persistenz.MitarbeiterDB;

public class MitarbeiterVerwaltung extends JFrame {

	private static final long serialVersionUID = 1L;
	private MitarbeiterTableModel mitarbeiterTableModel;
	private ListSelectionModel mitarbeiterSelectionModel;
	private JMenuItem miMitarbeiterHinzufügen;
	private JMenuItem miMitarbeiterBearbeiten;
	private JMenuItem miMitarbeiterLoeschen;
	private MitarbeiterDB mitarbeiterDb;
	private ArrayList<Mitarbeiter> mitarbeiterliste;

	public MitarbeiterVerwaltung() {

		setTitle("Mitarbeiterverwaltung");
		setSize(750, 400);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mitarbeiterDb = new MitarbeiterDB();

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

		miMitarbeiterHinzufügen = new JMenuItem("Neuen Mitarbeiter hinzufügen");
		menu.add(miMitarbeiterHinzufügen);
		miMitarbeiterHinzufügen.setFont(miMitarbeiterHinzufügen.getFont()
				.deriveFont(16f));
		miMitarbeiterHinzufügen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MitarbeiterHinzufügenFrame(MitarbeiterVerwaltung.this,
						mitarbeiterTableModel.getMitarbeiter());
				mitarbeiterTableModel.fireTableDataChanged();
				sortiereMitarbeiterliste(mitarbeiterliste);
			}
		});

		miMitarbeiterBearbeiten = new JMenuItem("Mitarbeiter bearbeiten");
		miMitarbeiterBearbeiten.setFont(miMitarbeiterBearbeiten.getFont()
				.deriveFont(16f));
		miMitarbeiterBearbeiten.setEnabled(false);
		menu.add(miMitarbeiterBearbeiten);
		miMitarbeiterBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editMitarbeiter();
			}
		});

		menu.add(new JSeparator());

		miMitarbeiterLoeschen = new JMenuItem("Mitarbeiter löschen");
		miMitarbeiterLoeschen.setFont(miMitarbeiterLoeschen.getFont()
				.deriveFont(16f));
		miMitarbeiterLoeschen.setEnabled(false);
		menu.add(miMitarbeiterLoeschen);
		miMitarbeiterLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = mitarbeiterSelectionModel.getMinSelectionIndex();
				Mitarbeiter m = mitarbeiterTableModel.getMitarbeiter(pos);

				int result = JOptionPane.showConfirmDialog(
						MitarbeiterVerwaltung.this,
						"Möchten Sie den Mitarbeiter " + m.getVorname() + " "
								+ m.getNachname() + " wirklich löschen?",
						"Frage", JOptionPane.YES_NO_OPTION);
				if (result != JOptionPane.YES_OPTION)
					return;

				mitarbeiterDb.mitarbeiterLöschen(m.getMitarbeiterID());
				mitarbeiterTableModel.deleteMitarbeiter(m);
				mitarbeiterTableModel.fireTableRowsDeleted(pos, pos);
			}
		});

		mitarbeiterliste = new ArrayList<Mitarbeiter>();
		try {
			mitarbeiterliste = mitarbeiterDb.mitarbeiterLaden();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		sortiereMitarbeiterliste(mitarbeiterliste);
		mitarbeiterTableModel = new MitarbeiterTableModel();
		mitarbeiterTableModel.setMitarbeiter(mitarbeiterliste);
		JTable table = new JTable(mitarbeiterTableModel);
		table.setFont(table.getFont().deriveFont(16f));
		table.getTableHeader().setFont(table.getFont().deriveFont(16f));
		table.setRowHeight(30);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setWidth(200);
		tcm.getColumn(1).setWidth(200);
		tcm.getColumn(2).setWidth(200);
		tcm.getColumn(3).setWidth(100);
		tcm.getColumn(4).setWidth(30);
		tcm.getColumn(5).setWidth(200);
		tcm.getColumn(6).setWidth(250);
		mitarbeiterSelectionModel = table.getSelectionModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		mitarbeiterSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent lsevent) {
						if (lsevent.getFirstIndex() == -1) {
							miMitarbeiterBearbeiten.setEnabled(false);
							miMitarbeiterLoeschen.setEnabled(false);
						} else {
							miMitarbeiterBearbeiten.setEnabled(true);
							miMitarbeiterLoeschen.setEnabled(true);
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

		JScrollPane scrollpane = new JScrollPane(table);
		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Tabellenansicht"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);

		add(titlepane);
		setVisible(true);
	}

	void sortiereMitarbeiterliste(ArrayList<Mitarbeiter> liste) { // alphabetische
																	// Sortierung
																	// nach
																	// Nachnamen
																	// der
																	// Kunden
		Collections.sort(liste, new Comparator<Mitarbeiter>() {

			public int compare(Mitarbeiter o1, Mitarbeiter o2) {
				if (o1.getNachname() == null || o2.getNachname() == null) {
					return Integer.MIN_VALUE;
				}
				return o1.getNachname().compareTo(o2.getNachname());
			}

		});
	}

	private void editMitarbeiter() {
		int row = mitarbeiterSelectionModel.getMinSelectionIndex();
		new MitarbeiterBearbeitenDialog(this,
				mitarbeiterTableModel.getMitarbeiter(row));
		sortiereMitarbeiterliste(mitarbeiterliste);
		mitarbeiterDb.mitarbeiterSpeichern(mitarbeiterTableModel
				.getMitarbeiter());
		mitarbeiterTableModel.fireTableDataChanged();
	}

	/*
	 * public void deleteMitarbeiter(){
	 * 
	 * }
	 */

	public static void main(String[] avgs) {
		new MitarbeiterVerwaltung();
	}
}
