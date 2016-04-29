package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	private ArrayList<Integer> terminliste;
	private TermineTableModel termineTableModel;
	private TerminDB terminDb;
	private JTable tagesTabelle;
	private Date datum;
	private int tageszeit;
	private ListSelectionModel terminSelectionModel;
	SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");

	public TagFrame(Date d, int tz) {
		datum = d;
		tageszeit = tz;
		terminDb = new TerminDB();
		terminliste = terminDb.termineLaden(datum, tageszeit);
		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		termineTableModel = new TermineTableModel(terminliste, tageszeit);
		tagesTabelle = new JTable(termineTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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

		JButton cmdFrueher = new JButton("Früher");
		cmdFrueher.setBounds(0, 40, 80, 20);
		cmdFrueher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TagFrame(datum, tageszeit - 1);
				TagFrame.this.dispose();
			}
		});
		if (tageszeit == 1) {
			cmdFrueher.setEnabled(false);
		}

		JPanel panelfrueher = new JPanel();
		panelfrueher.setLayout(null);
		panelfrueher.add(cmdFrueher);
		add(panelfrueher);

		JScrollPane scrollpane = new JScrollPane(tagesTabelle);
		JPanel tabellenpane = new JPanel();
		tabellenpane.setLayout(new BorderLayout());
		tabellenpane.add(scrollpane);
		add(tabellenpane);

		JButton cmdSpaeter = new JButton("Später");
		cmdSpaeter.setBounds(0, 40, 80, 20);
		cmdSpaeter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new TagFrame(datum, tageszeit + 1);
				TagFrame.this.dispose();

			}
		});
		if (tageszeit == 3) {
			cmdSpaeter.setEnabled(false);
		}

		JPanel panelspaeter = new JPanel();
		panelspaeter.setLayout(null);
		panelspaeter.add(cmdSpaeter);
		add(panelspaeter);

		Container content = getContentPane();
		content.setLayout(new GridLayout(1, 3));
		
		setVisible(true);

	}

	public static void main(String[] avgs) {
		new TagFrame(new Date(), 1);
	}

}
