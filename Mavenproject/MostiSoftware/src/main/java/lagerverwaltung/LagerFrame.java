package lagerverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import Dienstleistung_Produkt.Produkt;

public class LagerFrame extends JFrame {

	private LagerTableModel lagerTableModel;
	private ListSelectionModel produktSelectionModel;
	private List<Produkt> pliste;

	public LagerFrame(List<Produkt> auflistung) {

		pliste = auflistung;

		setTitle("Angebotsverwaltung");
		setSize(700, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu datei = new JMenu("Datei");
		menubar.add(datei);
//		JMenuItem open = new JMenuItem("Produkt-Liste öffnen");
//		datei.add(open);
//		datei.add(new JSeparator());
//		JMenuItem save = new JMenuItem("Produkt-Liste speichern");
//		datei.add(save);
		datei.addSeparator();
		JMenuItem beenden = new JMenuItem("Beenden");
		datei.add(beenden);

		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		JMenu bearbeiten = new JMenu("Bearbeiten");
		menubar.add(bearbeiten);
		JMenuItem newP = new JMenuItem("Neues Produkt hinzufügen");
		bearbeiten.add(newP);
		bearbeiten.addSeparator();
		JMenuItem bearP = new JMenuItem("Produkt bearbeiten");
		bearbeiten.add(bearP);
		bearbeiten.addSeparator();
		JMenuItem löschP = new JMenuItem("Produkt löschen");
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

		lagerTableModel = new LagerTableModel(pliste);
		JTable ptabelle = new JTable(lagerTableModel);

		produktSelectionModel = ptabelle.getSelectionModel();
		produktSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		produktSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent listevent) {
						if (listevent.getFirstIndex() == -1)
							bearP.setEnabled(false);
						else
							bearP.setEnabled(true);
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
//		TableColumn mengespalte = ptabelle.getColumnModel().getColumn(2);
//		mengespalte.setCellRenderer(new MengeCellRenderer());
//		
//		TableColumn grenzespalte = ptabelle.getColumnModel().getColumn(3);
//		grenzespalte.setCellRenderer(new MengeCellRenderer());

		ptabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// ptabelle.getColumnModel().getColumn(0).setPreferredWidth(100);
		// ptabelle.getColumnModel().getColumn(1).setPreferredWidth(150);
		// ptabelle.getColumnModel().getColumn(2).setPreferredWidth(100);
		// ptabelle.getColumnModel().getColumn(3).setPreferredWidth(70);

		JScrollPane scrollpane = new JScrollPane(ptabelle);
		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Getränke"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(scrollpane);

		add(titlepane);
		// new GetraenkHinzufuegenDialog(this, liste);
		setVisible(true);

	}

	private void addProdukt() {
		new ProduktHinzufuegenDialog(this, pliste);
		lagerTableModel.fireTableDataChanged();
	}
	
	private void deleteProdukt() {
		int row = produktSelectionModel.getMinSelectionIndex();
		pliste.remove(row);
		lagerTableModel.fireTableDataChanged();
	}

	private void bearbeiteProdukt() {
		int row = produktSelectionModel.getMinSelectionIndex();
		new ProduktBearbeitenDialog(this, lagerTableModel.getProdukt(row));
		lagerTableModel.fireTableRowsUpdated(row, row);
	}

	public static void main(String[] args) {

		List<Produkt> p = new ArrayList<Produkt>();

		Produkt p1 = new Produkt("3L-Beutel", 1.00, 10, 200);
		Produkt p2 = new Produkt("5L-Beutel", 1.50, 100, 300);
		Produkt p3 = new Produkt("10L-Beutel", 2.00, 100, 200);
		Produkt p4 = new Produkt("3L Beutel+Box", 2.00, 100, 200);
		Produkt p5 = new Produkt("5L Beutel+Box", 2.50, 100, 200);
		Produkt p6 = new Produkt("10L Beutel+Box", 3.00, 100, 200);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);

		LagerFrame lf = new LagerFrame(p);
	}

}
