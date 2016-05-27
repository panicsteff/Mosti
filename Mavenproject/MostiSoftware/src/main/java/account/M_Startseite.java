
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
import lagerverwaltung.ProduktSortiment;
import mitarbeiterverwaltung.MitarbeiterVerwaltung;
import terminplanung.TerminplanungsFrame;
import trester.TresterFrame;
import trester.Tresterverwaltung;
import verkaufsverwaltung.Verkaufsverwaltung;
import verkaufsverwaltung.Verkaufsübersicht;
import dienstleistungenverwaltung.DLSortiment;
import dienstleistungenverwaltung.DLVerwaltungFrame;

public class M_Startseite extends JFrame {

	private static final long serialVersionUID = 1L;
	JMenuBar mbar;
	JMenu mDatei;
	JMenuItem abrechnen;
	DLSortiment dlSorti;
	ProduktSortiment pSorti;

	public M_Startseite() {
		dlSorti = new DLSortiment(); 
		pSorti = new ProduktSortiment();
		
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
		JButton übersichtButton = new JButton();
		übersichtButton.setIcon(new ImageIcon("./src/übersicht.png"));

		panel.add(kassenButton);
		panel.add(lagerButton);
		panel.add(dlButton);
		panel.add(übersichtButton);
		panel.add(new JLabel("andere funktion"));
		panel.add(new JLabel("andere funktion"));

		kassenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KassenFrame k = new KassenFrame(dlSorti,pSorti, new Verkaufsverwaltung());
			}
		});

		lagerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LagerVerwaltungFrame l = new LagerVerwaltungFrame(pSorti);
			}
		});
		
		dlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLVerwaltungFrame dl = new DLVerwaltungFrame(dlSorti);
			}
		});
		
		übersichtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verkaufsübersicht v = new Verkaufsübersicht();
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
				KassenFrame k = new KassenFrame(dlSorti,pSorti, new Verkaufsverwaltung());
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
		
		JMenu trester = new JMenu("Tresterverwaltung");
		mbar.add(trester);
		JMenuItem tresteritem = new JMenuItem("Tresterpreis bearbeiten");
		trester.add(tresteritem);
		tresteritem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TresterFrame(new Tresterverwaltung());
			}
		});
		

		add(panel);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		M_Startseite startseite = new M_Startseite();
//	}

}