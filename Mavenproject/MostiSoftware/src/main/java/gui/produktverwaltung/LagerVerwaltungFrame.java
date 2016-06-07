package gui.produktverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
	// private List<Produkt> pliste;
	// private List<Produkt> aliste;
	// private List<Produkt> zliste;
	private JMenuItem bearP;
	static boolean hasChanged;
	//private JMenuItem miSpeichern;
	private ProduktSortiment pSortiment;
	private JMenuItem löschP;

	public LagerVerwaltungFrame(ProduktSortiment p) {
		this.pSortiment = p;
		
		//hasChanged = false;
		// createGesamtListe();

		setTitle("Produkte verwalten");
		setSize(700, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu datei = new JMenu("Datei");
		menubar.add(datei);
		datei.addSeparator();

//		miSpeichern = new JMenuItem("Produktangebot speichern");
//		datei.add(miSpeichern);
//		miSpeichern.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				try {
//					pSortiment.createSpecialLists();
//					pSortiment.printGesamtListe();
//					// a.produkteSpeichern();
//
//				} catch (Exception ex) {
//					System.out.println(e);
//				}
//			}
//		});

		datei.addSeparator();

		JMenuItem beenden = new JMenuItem("Beenden");
		datei.add(beenden);

		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				pSortiment.createSpecialLists();
				dispose();
			}
		});

		JMenu bearbeiten = new JMenu("Produktangebot bearbeiten");
		menubar.add(bearbeiten);
		JMenuItem newP = new JMenuItem("Neues Produkt hinzufügen");
		bearbeiten.add(newP);
		bearbeiten.addSeparator();
		bearP = new JMenuItem("Produkt bearbeiten");
		bearbeiten.add(bearP);
		bearbeiten.addSeparator();
		löschP = new JMenuItem("Produkt löschen");
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
		//
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
		printListe();
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
		// if(aliste.contains(p)== true)
		// aliste.remove(p);
		// else
		// zliste.remove(p);
		printListe();
		lagerTableModel.fireTableDataChanged();
	}

	private void bearbeiteProdukt() {
		int row = produktSelectionModel.getMinSelectionIndex();
		Produkt p = lagerTableModel.getProdukt(row);
		new ProduktBearbeitenFrame(this, p);

		// if(hasChanged == true){
		// if (aliste.contains(p) && p.isAbfüllmaterial() == false) {
		// aliste.remove(p);
		// zliste.add(p);
		// } else if (zliste.contains(p) && p.isAbfüllmaterial() == true) {
		// zliste.remove(p);
		// aliste.add(p);
		// }
		// hasChanged = false;
		// }
		pSortiment.updateProdukte();
		printListe();
		// lagerTableModel.fireTableRowsUpdated(0, (aliste != null?
		// aliste.size() : 0)+(zliste != null? zliste.size() : 0)-1);
		lagerTableModel.fireTableRowsUpdated(0, (pSortiment
				.getGesamtSortiment() != null ? pSortiment.getGesamtSortiment()
				.size() : 0));
	}

	private void printListe() {
		System.out.println();
		for (Produkt p : pSortiment.getGesamtSortiment()) {
			System.out.print(p.getName());
		}
		System.out.println();
	}


}
