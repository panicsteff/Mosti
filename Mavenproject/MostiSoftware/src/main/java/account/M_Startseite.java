package account;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import kassenfunktion.KassenFrame;
import kundenverwaltung.KundenVerwaltung;
import lagerverwaltung.LagerVerwaltungFrame;
import main.Angebote;
import mitarbeiterverwaltung.MitarbeiterVerwaltung;
import terminplanung.TerminplanungsFrame;
import verkaufsverwaltung.Kundeneinkäufe;
import dienstleistungenverwaltung.DLVerwaltungFrame;

public class M_Startseite extends JFrame {

	private static final long serialVersionUID = 1L;
	JMenuBar mbar;
	JMenu mDatei;
	JMenuItem abrechnen;
	Angebote angebote;
	Kundeneinkäufe kundeneinkäufe;

	public M_Startseite(Angebote a, Kundeneinkäufe k) {
		angebote = a;
		kundeneinkäufe = k;
		setTitle("Startseite");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 200, 500, 500);

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
				KassenFrame a = new KassenFrame(angebote.getDLSortiment(),angebote.getAbfuellSortiment(), angebote.getZProduktSortiment(),kundeneinkäufe);
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
				//LagerVerwaltungFrame l = new LagerVerwaltungFrame(angebote.getAbfuellSortiment(), angebote.getZProduktSortiment());
				LagerVerwaltungFrame l = new LagerVerwaltungFrame(angebote);
			}
		});
		
		dlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLVerwaltungFrame dl = new DLVerwaltungFrame(angebote.getDLSortiment());
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
				KassenFrame a = new KassenFrame(angebote.getDLSortiment(),angebote.getAbfuellSortiment(), angebote.getZProduktSortiment(), kundeneinkäufe);
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
		
		JMenu terminplanung = new JMenu("Terminplanung");
		mbar.add(terminplanung);
		JMenuItem termin = new JMenuItem("Zur Terminplanung");
		terminplanung.add(termin);
		termin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TerminplanungsFrame();
			}
		});
		
		JMenu mitarbeiter = new JMenu("Mitarbeiter");
		mbar.add(mitarbeiter);
		JMenuItem cmdMitarbeiter = new JMenuItem("Mitarbeiterverwaltung");
		mitarbeiter.add(cmdMitarbeiter);
		cmdMitarbeiter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new MitarbeiterVerwaltung();
			}
		});
		

		add(panel);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		M_Startseite startseite = new M_Startseite();
//	}

}
