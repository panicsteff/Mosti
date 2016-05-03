package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import kassenfunktion.Abrechnungsrahmen;
import kundenverwaltung.KundenVerwaltung;
import lagerverwaltung.LagerFrame;
import main.Angebote;
import main.Kundeneinkäufe;
import dienstleistungProdukt.Produkt;
import dienstleistungenverwaltung.DLFrame;

public class M_Startseite extends JFrame {

	JMenuBar mbar;
	JMenu mDatei;
	JMenuItem abrechnen;
	Angebote angebote;
	Kundeneinkäufe kundeneinkäufe;

	public M_Startseite(Angebote a, Kundeneinkäufe k) {
		angebote = a;
		kundeneinkäufe = k;
		setSize(500, 500);
		setTitle("Startseite");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3));
		JButton kassenButton = new JButton();
		kassenButton.setIcon(new ImageIcon("./src/register.png"));
		JButton lagerButton = new JButton();
		lagerButton.setIcon(new ImageIcon("./src/karre.jpg"));
		JButton dlButton = new JButton();
		dlButton.setIcon(new ImageIcon("./src/apple.png"));

		panel.add(kassenButton);
		panel.add(lagerButton);
		panel.add(dlButton);
		panel.add(new JLabel("andere funktion"));
		panel.add(new JLabel("andere funktion"));
		panel.add(new JLabel("andere funktion"));

		kassenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Abrechnungsrahmen a = new Abrechnungsrahmen(angebote.getDLSortiment(),angebote.getAbfuellSortiment(), angebote.getZProduktSortiment(),kundeneinkäufe);
			}
		});

		lagerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ArrayList<Produkt> p = new ArrayList<Produkt>();
//				Produkt p1 = new Produkt("5L Box", 1.00, 10, 200, true);
//				Produkt p2 = new Produkt("10L Box", 2.00, 100, 200, true);
//				Produkt p3 = new Produkt("10L Box", 2.00, 100, 200, true);
//				p.add(p1);
//				p.add(p2);
//				p.add(p3);
				LagerFrame l = new LagerFrame(angebote.getAbfuellSortiment(), angebote.getZProduktSortiment());
			}
		});
		
		dlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLFrame dl = new DLFrame(angebote.getDLSortiment());
			}
		});

		mbar = new JMenuBar();
		setJMenuBar(mbar);
		mDatei = new JMenu("Datei");
		mbar.add(mDatei);
		abrechnen = new JMenuItem("Abrechnen");
		mDatei.add(abrechnen);
		abrechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Abrechnungsrahmen a = new Abrechnungsrahmen(angebote.getDLSortiment(),angebote.getAbfuellSortiment(), angebote.getZProduktSortiment(), kundeneinkäufe);
			}
		});

		mDatei.add(new JSeparator());
		JMenuItem kunden = new JMenuItem("Kunden pflegen");
		mDatei.add(kunden);
		kunden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new KundenVerwaltung();
			}
		});

		add(panel);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		M_Startseite startseite = new M_Startseite();
//	}

}
