
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import logik.administratorverwaltung.AdministratorLogik;
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
		mDatei.setFont(mDatei.getFont().deriveFont(16f));
		
		JMenuItem passwort = new JMenuItem("Passwort ändern");
		passwort.setFont(passwort.getFont().deriveFont(16f));
		mDatei.add(passwort);
		passwort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new PasswortBearbeitenFrame(M_Startseite.this.mitarbeiter);
			}
		});
		JMenuItem adminwerte = new JMenuItem("Adminwerte bearbeiten");
		adminwerte.setFont(adminwerte.getFont().deriveFont(16f));
		mDatei.add(new JSeparator());
		mDatei.add(adminwerte);
		adminwerte.setVisible(isAdmin);
		adminwerte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new ÜbersichtFrame();
			}
		});
		

			
		
		
		JMenu trester = new JMenu("Tresterverwaltung");
		trester.setFont(trester.getFont().deriveFont(16f));
		mbar.add(trester);
		JMenuItem tresteritem = new JMenuItem("Tresterpreis bearbeiten");
		tresteritem.setFont(tresteritem.getFont().deriveFont(16f));
		trester.add(tresteritem);
		tresteritem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TresterpreisBearbeitenFrame(new Tresterverwaltung());
			}
		});
		trester.addSeparator();
		JMenuItem tresteritem2 = new JMenuItem("Trester-Verkaufsübersicht erstellen");
		tresteritem2.setFont(tresteritem2.getFont().deriveFont(16f));
		trester.add(tresteritem2);
		tresteritem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TresterübersichtFrame();
			}
		});
		
		JMenu account = new JMenu("Account");
		account.setFont(account.getFont().deriveFont(16f));
		mbar.add(account);
		
		JMenuItem wechseln = new JMenuItem("Benutzer wechseln");
		wechseln.setFont(wechseln.getFont().deriveFont(16f));
		account.add(wechseln);
		wechseln.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				M_Startseite.this.dispose();
				new Anmeldung();
			}
			
		});
		account.addSeparator();
		
		JMenuItem logout = new JMenuItem("Log out");
		logout.setFont(logout.getFont().deriveFont(16f));
		account.add(logout);
		logout.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				backupBehandlung();
				M_Startseite.this.dispose();
			}

			
			
		});
		add(panel);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				backupBehandlung();
			}
		});
		setVisible(true);
	}
	
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
	private void backupBehandlung() {
		new AdministratorLogik();
		long heute = new Date().getTime();
		long letztesBackup = AdministratorLogik.getLetztesBackup();
		
		long differenz = (heute - letztesBackup)/(24*60*60*1000);				//Stunden, Minuten, Sekunden und Millisekunden
		if(differenz> AdministratorLogik.getBackupdauer()){
			int result = JOptionPane.showConfirmDialog(null, "Das letzte Backup ist mehr als "
					+ AdministratorLogik.getBackupdauer() + " Tage her. Wollen Sie das Backup für die Datenbank jetzt aktualisieren?");
			if(result == JOptionPane.YES_OPTION){
				JButton speichern = new JButton("Speichern");
				File f = new File("./Mosti-Datenbank.mdb");
				JFileChooser fc = new JFileChooser(".");
				fc.showSaveDialog(M_Startseite.this);
				if(fc.showSaveDialog(speichern) == JFileChooser.APPROVE_OPTION){
					try {
						AdministratorLogik.letztesBackupSpeichern(heute);
						copyFileUsingStream(f, fc.getSelectedFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
}