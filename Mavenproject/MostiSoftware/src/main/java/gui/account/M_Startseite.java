
package gui.account;

import gui.administratorverwaltung.�bersichtFrame;
import gui.dienstleistungverwaltung.DLVerwaltungFrame;
import gui.kundenverwaltung.KundenVerwaltung;
import gui.mitarbeiterverwaltung.MitarbeiterVerwaltung;
import gui.produktverwaltung.LagerVerwaltungFrame;
import gui.schichtverwaltung.SchichtplanungsFrame;
import gui.terminplanung.TagFrame;
import gui.terminplanung.TerminplanungsFrame;
import gui.trester.TresterpreisBearbeitenFrame;
import gui.trester.Trester�bersichtFrame;
import gui.verkauf.Verkaufs�bersicht;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import logik.dienstleistungverwaltung.DLSortiment;
import logik.mitarbeiterverwaltung.Mitarbeiter;
import logik.produktverwaltung.ProduktSortiment;
import logik.trester.Tresterverwaltung;

public class M_Startseite extends JFrame {

	private static final long serialVersionUID = 1L;
	JMenuBar mbar;
	JMenu mDatei;
	JMenuItem abrechnen;
	DLSortiment dlSorti;
	ProduktSortiment pSorti;
	private boolean isAdmin;
	private Mitarbeiter mitarbeiter;

	public M_Startseite(boolean isAdmin, Mitarbeiter m) {
		dlSorti = new DLSortiment(); 
		pSorti = new ProduktSortiment();
		
		this.isAdmin = isAdmin;
		mitarbeiter = m;
		
		setTitle("Startseite");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 500);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3));
		JButton kassenButton = new JButton("Kasse", new ImageIcon("./src/register.png"));
		kassenButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        kassenButton.setHorizontalTextPosition(SwingConstants.CENTER);
		kassenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TagFrame(new Date().getTime(), 1, M_Startseite.this, 0, 0, false);
			}
		});
		
		
		JButton mitarbeiter = new JButton("Mitarbeiterverwaltung", new ImageIcon("./src/mitarbeiter1.png"));
		mitarbeiter.setVerticalTextPosition(SwingConstants.BOTTOM);
        mitarbeiter.setHorizontalTextPosition(SwingConstants.CENTER);
		mitarbeiter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new MitarbeiterVerwaltung();
			}
		});
		
		
		JButton terminplanung = new JButton("Terminplanung", new ImageIcon("./src/termin.png"));
		terminplanung.setVerticalTextPosition(SwingConstants.BOTTOM);
        terminplanung.setHorizontalTextPosition(SwingConstants.CENTER);
		terminplanung.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TerminplanungsFrame();
			}
		});
		
		
		JButton lagerButton = new JButton("Lagerverwaltung", new ImageIcon("./src/karre.jpg"));
		lagerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        lagerButton.setHorizontalTextPosition(SwingConstants.CENTER);
		lagerButton.setVisible(isAdmin);
		
		
		JButton dlButton = new JButton("Dienstleistungen", new ImageIcon("./src/apple.png"));
		dlButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        dlButton.setHorizontalTextPosition(SwingConstants.CENTER);
		dlButton.setVisible(isAdmin);
		
		
		JButton �bersichtButton = new JButton("Verkaufs�bersicht", new ImageIcon("./src/�bersicht.png"));
		�bersichtButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        �bersichtButton.setHorizontalTextPosition(SwingConstants.CENTER);
		�bersichtButton.setVisible(isAdmin);
		
		JButton kundeButton = new JButton("Kundenverwaltung", new ImageIcon("./src/kunde.jpg"));
		kundeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		kundeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton schichtButton = new JButton("Schichtplanverwaltung", new ImageIcon("./src/schichtplan.png"));
		schichtButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		schichtButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		panel.add(kassenButton);
		panel.add(mitarbeiter);
		panel.add(kundeButton);
		panel.add(terminplanung);
		panel.add(lagerButton);
		panel.add(dlButton);
		panel.add(�bersichtButton);
		panel.add(schichtButton);
//		panel.add(new JLabel("andere funktion"));
//		panel.add(new JLabel("andere funktion"));

		

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
		
		�bersichtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verkaufs�bersicht v = new Verkaufs�bersicht();
			}
		});
		
		kundeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new KundenVerwaltung();
			}
		});
		
		
		schichtButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SchichtplanungsFrame(M_Startseite.this.isAdmin);
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
				//KassenFrame k = new KassenFrame(dlSorti,pSorti, new Verkaufsverwaltung());
			}
		});
		
		JMenuItem passwort = new JMenuItem("Passwort �ndern");
		mDatei.add(new JSeparator());
		mDatei.add(passwort);
		passwort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new PasswortBearbeitenFrame(M_Startseite.this.mitarbeiter);
			}
		});
		JMenuItem adminwerte = new JMenuItem("Adminwerte bearbeiten");
		mDatei.add(new JSeparator());
		mDatei.add(adminwerte);
		adminwerte.setVisible(isAdmin);
		adminwerte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new �bersichtFrame();
			}
		});
		
		

		//mDatei.add(new JSeparator());
		//JMenuItem kunden = new JMenuItem("Kunden pflegen");
		//mDatei.add(kunden);
		//kunden.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//		new KundenVerwaltung();
		//	}
		//});
		
		
		
		
		
		
		
		JMenu trester = new JMenu("Tresterverwaltung");
		mbar.add(trester);
		JMenuItem tresteritem = new JMenuItem("Tresterpreis bearbeiten");
		trester.add(tresteritem);
		tresteritem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TresterpreisBearbeitenFrame(new Tresterverwaltung());
			}
		});
		JMenuItem tresteritem2 = new JMenuItem("Trester-Verkaufs�bersicht erstellen");
		trester.add(tresteritem2);
		tresteritem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Trester�bersichtFrame();
			}
		});
		
		
		
		
			

		add(panel);
		setVisible(true);
	}


}