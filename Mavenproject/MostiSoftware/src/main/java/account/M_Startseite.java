
package account;

import gui.administratorverwaltung.�bersichtFrame;
import gui.dienstleistungverwaltung.DLVerwaltungFrame;
import gui.kundenverwaltung.KundenVerwaltung;
import gui.mitarbeiterverwaltung.MitarbeiterVerwaltung;
import gui.produktverwaltung.LagerVerwaltungFrame;
import gui.schichtverwaltung.SchichtplanungsFrame;
import gui.terminplanung.TagFrame;
import gui.terminplanung.TerminplanungsFrame;
import gui.trester.TresterabrechnungFrame;
import gui.trester.TresterpreisBearbeitenFrame;
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 200, 500, 500);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3));
		JButton kassenButton = new JButton();
		kassenButton.setIcon(new ImageIcon("./src/register.png"));
		
		kassenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TagFrame(new Date().getTime(), 1, M_Startseite.this, 0);
			}
		});
		
		
		JButton mitarbeiter = new JButton("Mitarbeiterverwaltung");
		panel.add(mitarbeiter);
		mitarbeiter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new MitarbeiterVerwaltung();
			}
		});
		mitarbeiter.setIcon(new ImageIcon("./src/mitarbeiter.jpg"));
		
		JButton terminplanung = new JButton("Terminplanung");
		panel.add(terminplanung);
		terminplanung.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TerminplanungsFrame();
			}
		});
		
		terminplanung.setIcon(new ImageIcon("./src/termin.png"));
		JButton lagerButton = new JButton();
		lagerButton.setIcon(new ImageIcon("./src/karre.jpg"));
		lagerButton.setVisible(isAdmin);
		JButton dlButton = new JButton();
		dlButton.setIcon(new ImageIcon("./src/apple.png"));
		dlButton.setVisible(isAdmin);
		JButton �bersichtButton = new JButton();
		�bersichtButton.setIcon(new ImageIcon("./src/�bersicht.png"));
		�bersichtButton.setVisible(isAdmin);

		panel.add(kassenButton);
		panel.add(lagerButton);
		panel.add(dlButton);
		panel.add(�bersichtButton);
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
		
		
		
		JMenu kunde = new JMenu("Kunde");
		mbar.add(kunde);
		JMenuItem cmdKunde = new JMenuItem("Kundenverwaltung");
		kunde.add(cmdKunde);
		cmdKunde.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new KundenVerwaltung();
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
		JMenuItem tresteritem2 = new JMenuItem("Tresterabrechnung erstellen");
		trester.add(tresteritem2);
		tresteritem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TresterabrechnungFrame();
//				int result = JOptionPane.showConfirmDialog(M_Startseite.this, "M�chten Sie den Tresterpreis f�r den gesamten Tag berechnen?",
//						"Frage", JOptionPane.YES_NO_OPTION);
			}
		});
		
		
		JMenu schicht = new JMenu("Schichtplan");
		mbar.add(schicht);
		JMenuItem schichtitem = new JMenuItem("Zur Schichtplanbearbeitung");
		schicht.add(schichtitem);
		schichtitem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SchichtplanungsFrame(M_Startseite.this.isAdmin);
			}
		});
			

		add(panel);
		setVisible(true);
	}


}