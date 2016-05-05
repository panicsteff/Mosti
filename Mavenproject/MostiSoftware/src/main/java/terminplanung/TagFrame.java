package terminplanung;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import kundenverwaltung.Formats;

public class TagFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> terminliste;
	ArrayList<Integer> adminwerte;
	private TermineTableModel termineTableModel;
	private TerminDB terminDb;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private int zeilenanzahl;
	private ListSelectionModel terminSelectionModel;
	private JMenuBar mbar;

	TagFrame(Date d, int za, int as, ArrayList<Integer> aw) {
		
		datum = d;
		zeilenanzahl = za;
		anzeigeseite = as;
		adminwerte = aw;	
		
		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		mbar = new JMenuBar();
		setJMenuBar(mbar);
		
		JMenu termine = new JMenu("Termine");
		mbar.add(termine);
		
		JMenuItem neuerTermin = new JMenuItem("Neuen Termin anlegen");
		termine.add(neuerTermin);
		neuerTermin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		terminDb = new TerminDB();
		terminliste = terminDb.termineLaden(datum, zeilenanzahl, anzeigeseite);
		

		termineTableModel = new TermineTableModel(terminliste, anzeigeseite, adminwerte);
		tagesTabelle = new JTable(termineTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tagesTabelle.setRowHeight(30);
		tagesTabelle.setFont(tagesTabelle.getFont().deriveFont(16f));


		terminSelectionModel = tagesTabelle.getSelectionModel();
		terminSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tagesTabelle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int zeile = terminSelectionModel.getMaxSelectionIndex();
					//int kundenId = termineTableModel.getTermin(zeile);
					String date = Formats.DATE_FORMAT.format(datum);
					String name = (String) termineTableModel.getValueAt(zeile, 1);
					String zeit = (String) termineTableModel.getValueAt(zeile, 0);
					new TerminBearbeitenFrame(date, name, zeit);
				}
			}
		});

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(2,1));
		

		JScrollPane scrollpane = new JScrollPane(tagesTabelle);
		JPanel tabellenpane = new JPanel();
		tabellenpane.setLayout(new BorderLayout());
		tabellenpane.add(scrollpane);
		tabellenpane.setBounds(0, 0, 300, 500);
		contentPane.add(tabellenpane);

		
		JButton cmdFrueher = new JButton("Früher");
		cmdFrueher.setBounds(0, 40, 80, 20);
		cmdFrueher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TagFrame(datum, zeilenanzahl, anzeigeseite - 1, adminwerte);
				TagFrame.this.dispose();
			}
		});
		if (anzeigeseite == 1) {
			cmdFrueher.setEnabled(false);
		}
		
		JButton cmdSpaeter = new JButton("Später");
		cmdSpaeter.setBounds(0, 40, 80, 20);
		cmdSpaeter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new TagFrame(datum, zeilenanzahl, anzeigeseite + 1, adminwerte);
				TagFrame.this.dispose();

			}
		});
		if (anzeigeseite == adminwerte.get(3)) {									
			cmdSpaeter.setEnabled(false);
		}

		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new FlowLayout());
		buttonpanel.add(cmdFrueher);
		buttonpanel.add(cmdSpaeter);
		buttonpanel.setBounds(300, 100, 200, 50);
		contentPane.add(buttonpanel);
		
		add(contentPane);
		
		setVisible(true);

	}
	
	public static void main (String[] avgs){
		ArrayList<Integer> aw = new ArrayList<Integer>();
		aw.add(5);
		aw.add(540);
		aw.add(1140);
		aw.add(3);
		
		String s = "1.9.2016";
		Date d = new Date();
		try {
			d = Formats.DATE_FORMAT.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new TagFrame(d,10, 1,aw);
	}

}
