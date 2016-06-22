
package gui.account;

import gui.administratorverwaltung.ÜbersichtFrame;
import gui.dienstleistungverwaltung.DLVerwaltungFrame;
import gui.kundenverwaltung.KundenVerwaltung;
import gui.mitarbeiterverwaltung.MitarbeiterVerwaltung;
import gui.produktverwaltung.LagerVerwaltungFrame;
import gui.schichtverwaltung.SchichtplanungsFrame;
import gui.terminplanung.TagFrame;
import gui.terminplanung.TerminplanungsFrame;
import gui.trester.TresterpreisBearbeitenFrame;
import gui.trester.TresterübersichtFrame;
import gui.verkauf.Verkaufsübersicht;

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
		
		setSize(750, 500);
		setLocationRelativeTo(getParent());
		

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
		
		JButton terminplanung = new JButton("Terminverwaltung", new ImageIcon("./src/termin.png"));
		terminplanung.setVerticalTextPosition(SwingConstants.BOTTOM);
        terminplanung.setHorizontalTextPosition(SwingConstants.CENTER);
		terminplanung.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TerminplanungsFrame();
			}
		});
		
		JButton kundeButton = new JButton("Kundenverwaltung", new ImageIcon("./src/kunde.jpg"));
		kundeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		kundeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		kundeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new KundenVerwaltung();
			}
		});
		
		
		JButton schichtButton = new JButton("Schichtplanverwaltung", new ImageIcon("./src/schichtplan.png"));
		schichtButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		schichtButton.setHorizontalTextPosition(SwingConstants.CENTER);
		schichtButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SchichtplanungsFrame(M_Startseite.this.isAdmin);
			}
		});
		
		
		JButton übersichtButton = new JButton("Verkaufsübersicht", new ImageIcon("./src/übersicht.png"));
		übersichtButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        übersichtButton.setHorizontalTextPosition(SwingConstants.CENTER);
		übersichtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verkaufsübersicht v = new Verkaufsübersicht();
			}
		});
		
		
		JButton mitarbeiter = new JButton("Mitarbeiterverwaltung", new ImageIcon("./src/mitarbeiter.png"));
		mitarbeiter.setVerticalTextPosition(SwingConstants.BOTTOM);
        mitarbeiter.setHorizontalTextPosition(SwingConstants.CENTER);
        mitarbeiter.setVisible(isAdmin);
		mitarbeiter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new MitarbeiterVerwaltung();
			}
		});
		
	
		
		
		JButton lagerButton = new JButton("Lagerverwaltung", new ImageIcon("./src/karre.jpg"));
		lagerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        lagerButton.setHorizontalTextPosition(SwingConstants.CENTER);
		lagerButton.setVisible(isAdmin);
		lagerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LagerVerwaltungFrame l = new LagerVerwaltungFrame(pSorti);
			}
		});
		
		
		JButton dlButton = new JButton("Dienstleistungen", new ImageIcon("./src/apple.png"));
		dlButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        dlButton.setHorizontalTextPosition(SwingConstants.CENTER);
		dlButton.setVisible(isAdmin);
		dlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLVerwaltungFrame dl = new DLVerwaltungFrame(dlSorti);
			}
		});
		

		
	
		panel.add(kassenButton);
		panel.add(terminplanung);
		panel.add(kundeButton);
		panel.add(schichtButton);
		panel.add(übersichtButton);
		panel.add(mitarbeiter);
		panel.add(lagerButton);
		panel.add(dlButton);
		
		
		

		mbar = new JMenuBar();
		setJMenuBar(mbar);
		mDatei = new JMenu("Benutzereinstellungen");
		mbar.add(mDatei);
		
		JMenuItem passwort = new JMenuItem("Passwort ändern");
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
				new ÜbersichtFrame();
			}
		});
		

			
		
		
		JMenu trester = new JMenu("Tresterverwaltung");
		mbar.add(trester);
		JMenuItem tresteritem = new JMenuItem("Tresterpreis bearbeiten");
		trester.add(tresteritem);
		tresteritem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TresterpreisBearbeitenFrame(new Tresterverwaltung());
			}
		});
		JMenuItem tresteritem2 = new JMenuItem("Trester-Verkaufsübersicht erstellen");
		trester.add(tresteritem2);
		tresteritem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TresterübersichtFrame();
			}
		});
		
		JMenu account = new JMenu("Account");
		mbar.add(account);
		
		JMenuItem wechseln = new JMenuItem("Benutzer wechseln");
		account.add(wechseln);
		wechseln.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				M_Startseite.this.dispose();
				new Anmeldung();
			}
			
		});
		account.addSeparator();
		
		JMenuItem logout = new JMenuItem("Log out");
		account.add(logout);
		logout.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				M_Startseite.this.dispose();
			}
			
		});

		
		
		
			

		add(panel);
		setVisible(true);
	}


}