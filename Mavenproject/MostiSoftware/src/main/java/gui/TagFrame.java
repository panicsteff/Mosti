package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import kundenverwaltung.Formats;
import terminplanung.TerminDB;
import terminplanung.TermineTableModel;

public class TagFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> terminliste;
	private TermineTableModel termineTableModel;
	private TerminDB terminDb;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private ListSelectionModel terminSelectionModel;
	SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");

	public TagFrame(Date d, int as) {
		datum = d;
		anzeigeseite = as;
		terminDb = new TerminDB();
		terminliste = terminDb.termineLaden(datum, anzeigeseite);
		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		termineTableModel = new TermineTableModel(terminliste, anzeigeseite);
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
					int position = terminSelectionModel.getMaxSelectionIndex();
					int kundenId = termineTableModel.getTermin(position);
					// new TerminBearbeitenFrame(t);
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
				new TagFrame(datum, anzeigeseite - 1);
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
				new TagFrame(datum, anzeigeseite + 1);
				TagFrame.this.dispose();

			}
		});
		if (anzeigeseite == termineTableModel.getAufteilung()) {										//eigetlich die Anzeigeanzahl
			cmdSpaeter.setEnabled(false);
		}

		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new FlowLayout());
		buttonpanel.add(cmdFrueher);
		buttonpanel.add(cmdSpaeter);
		buttonpanel.setBounds(300, 100, 200, 50);
		contentPane.add(buttonpanel);
		
		add(contentPane);
		
		
		System.out.println(termineTableModel.berechneZeilen());
		setVisible(true);

	}

	public static void main(String[] avgs) {
		
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String s = "1.9.2016";
		Date d = new Date();
		try {
			d = df.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new TagFrame(d, 1);
	}

}
