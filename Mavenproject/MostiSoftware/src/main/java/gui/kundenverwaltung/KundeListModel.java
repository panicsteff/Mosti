package gui.kundenverwaltung;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import persistenz.KundeDB;
import logik.kundenverwaltung.Kunde;

@SuppressWarnings("serial")
public class KundeListModel extends AbstractListModel<String> {

	private KundeDB kundeDb;
	ArrayList<Kunde> kundenliste;

	public KundeListModel() {
		kundeDb = new KundeDB();
		try {
			kundenliste = kundeDb.kundenLaden();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sortiereKundenliste();

	}

	void sortiereKundenliste() { // alphabetische Sortierung nach Nachnamen der
									// Kunden
		Collections.sort(kundenliste, new Comparator<Kunde>() {

			public int compare(Kunde o1, Kunde o2) {
				return o1.getNachname().compareTo(o2.getNachname());
			}

		});
	}

	public String getElementAt(int index) {
		if (index < getSize())
			return getKundenname(kundenliste.get(index));
		else
			return "-";

	}

	public Kunde getKunde(int index) {
		return kundenliste.get(index);
	}

	public int getSize() {
		return kundenliste.size();
	}

	public String getKundenname(Kunde kunde) {
		return kunde.getNachname() + ", " + kunde.getVorname();
	}

	public void printListe() {
		for (Kunde k : kundenliste) {
			System.out.println(k.getNachname());
		}
	}

	// public static void main(String[] args){
	// JFrame frame = new JFrame();
	// frame.setTitle("Miau");
	// frame.setSize(500, 400);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.getContentPane().setLayout(null);
	//
	// ListModel model = new KundeListModel();
	// JList listKunde = new JList(model);
	// ListSelectionModel lsm = listKunde.getSelectionModel();
	// lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	// ListCellRenderer lcr = new KundeListCellRenderer();
	// listKunde.setCellRenderer(lcr);
	// JScrollPane scrollpane = new JScrollPane(listKunde);
	// scrollpane.setBounds(110, 300, 100, 70);
	// frame.add(scrollpane);
	// frame.setVisible(true);
	// }
	// public static void main(String[] args) {
	// KundeListModel k = new KundeListModel();
	// k.printListe();
	// }

}
