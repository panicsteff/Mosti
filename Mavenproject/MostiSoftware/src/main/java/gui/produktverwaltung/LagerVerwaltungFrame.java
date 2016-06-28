package gui.produktverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import logik.produktverwaltung.MengeCellRenderer;
import logik.produktverwaltung.PreisCellRenderer;
import logik.produktverwaltung.Produkt;
import logik.produktverwaltung.ProduktSortiment;

public class LagerVerwaltungFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private LagerTableModel lagerTableModel;
	private ListSelectionModel produktSelectionModel;

	private JMenuItem bearP;
	//static boolean hasChanged;

	private ProduktSortiment pSortiment;
	private JMenuItem löschP;

	public LagerVerwaltungFrame(ProduktSortiment p) {
		this.pSortiment = p;

		setTitle("Produkte verwalten");
		setSize(700, 400);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu datei = new JMenu("Datei");
		datei.setFont(datei.getFont().deriveFont(16f));
		menubar.add(datei);
		datei.addSeparator();

		JMenuItem beenden = new JMenuItem("Beenden");
		beenden.setFont(beenden.getFont().deriveFont(16f));
		datei.add(beenden);

		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				pSortiment.createSpecialLists();
				dispose();
			}
		});

		JMenu bearbeiten = new JMenu("Produktangebot bearbeiten");
		bearbeiten.setFont(bearbeiten.getFont().deriveFont(16f));
		menubar.add(bearbeiten);
		JMenuItem newP = new JMenuItem("Neues Produkt hinzufügen");
		newP.setFont(newP.getFont().deriveFont(16f));
		bearbeiten.add(newP);
		bearbeiten.addSeparator();
		bearP = new JMenuItem("Produkt bearbeiten");
		bearP.setFont(bearP.getFont().deriveFont(16f));
		bearbeiten.add(bearP);
		bearbeiten.addSeparator();
		löschP = new JMenuItem("Produkt löschen");
		löschP.setFont(löschP.getFont().deriveFont(16f));
		bearbeiten.add(löschP);

		newP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addProdukt();
			}
		});

		löschP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				deleteProdukt();
			}
		});

		bearP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bearbeiteProdukt();
			}
		});
		bearP.setEnabled(false);
		löschP.setEnabled(false);

		lagerTableModel = new LagerTableModel(pSortiment.getGesamtSortiment());

		JTable ptabelle = new JTable(lagerTableModel);
		ptabelle.setFont(ptabelle.getFont().deriveFont(16f));
		ptabelle.getTableHeader().setFont(ptabelle.getFont().deriveFont(16f));
		ptabelle.setRowHeight(30);

		produktSelectionModel = ptabelle.getSelectionModel();
		produktSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		produktSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent listevent) {
						if (listevent.getFirstIndex() == -1){
							bearP.setEnabled(false);
							löschP.setEnabled(false);
						}
						else{
							bearP.setEnabled(true);
							löschP.setEnabled(true);
						}
					}

				});

		ptabelle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseevent) {
				if (mouseevent.getClickCount() == 2)
					bearbeiteProdukt();
			}
		});

		TableColumn preisspalte = ptabelle.getColumnModel().getColumn(1);
		preisspalte.setCellRenderer(new PreisCellRenderer());
		
		TableColumn mengespalte = ptabelle.getColumnModel().getColumn(2);
		mengespalte.setCellRenderer(new MengeCellRenderer());
		TableColumn grenzespalte = ptabelle.getColumnModel().getColumn(3);
		grenzespalte.setCellRenderer(new MengeCellRenderer());

		ptabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ptabelle.getColumnModel().getColumn(0).setPreferredWidth(200);
		ptabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
		ptabelle.getColumnModel().getColumn(2).setPreferredWidth(120);
		ptabelle.getColumnModel().getColumn(3).setPreferredWidth(120);
		ptabelle.getColumnModel().getColumn(4).setPreferredWidth(100);

		JScrollPane scrollpane = new JScrollPane(ptabelle);
		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Produkte"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);

		add(titlepane);
		setVisible(true);

	}

	private void addProdukt() {
		new ProduktHinzufuegenFrame(this, pSortiment);
		//printListe();
		lagerTableModel.fireTableDataChanged();
	}

	private void deleteProdukt() {
		int row = produktSelectionModel.getMinSelectionIndex();
		Produkt p = lagerTableModel.getProdukt(row);
		int result = JOptionPane.showConfirmDialog(this, "Möchten Sie das Produkt " + p.getName() + " wirklich löschen?",
				"Frage", JOptionPane.YES_NO_OPTION);
		if (result != JOptionPane.YES_OPTION)
			return;
		
		pSortiment.deleteProdukt(p);
		//printListe();
		lagerTableModel.fireTableDataChanged();
	}

	private void bearbeiteProdukt() {
		int row = produktSelectionModel.getMinSelectionIndex();
		Produkt p = lagerTableModel.getProdukt(row);
		new ProduktBearbeitenFrame(this, p);
		pSortiment.produktAktualisieren(p);
		//printListe();
		lagerTableModel.fireTableRowsUpdated(0, (pSortiment
				.getGesamtSortiment() != null ? pSortiment.getGesamtSortiment()
				.size() : 0));
	}

//	private void printListe() {
//		System.out.println();
//		for (Produkt p : pSortiment.getGesamtSortiment()) {
//			System.out.print(p.getName());
//		}
//		System.out.println();
//	}


}
