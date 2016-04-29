package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import kundenverwaltung.Formats;
import terminplanung.TermineTableModel;
import terminplanung.Termin;
import terminplanung.TerminDB;

public class TagFrame extends JFrame{

	private ArrayList<Termin> terminliste;
	private TermineTableModel termineTableModel;
	private TerminDB terminDb;
	private JTable tagesTabelle;
	private ListSelectionModel terminSelectionModel;
	SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
	
	public TagFrame (Date datum){
		terminDb = new TerminDB();
		terminliste = terminDb.termineLaden(datum);
		setSize(300,400);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		termineTableModel = new TermineTableModel(terminliste);
		tagesTabelle = new JTable(termineTableModel);
		
		terminSelectionModel = tagesTabelle.getSelectionModel();
		terminSelectionModel.setSelectionMode(
		ListSelectionModel.SINGLE_SELECTION);
		tagesTabelle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (event.getClickCount() == 2){
					int position = terminSelectionModel.getMaxSelectionIndex();
					Termin t = termineTableModel.getTermin(position);
					new TerminBearbeitenFrame(t);
				}	
			}
		});
		
		JScrollPane scrollpane = new JScrollPane(tagesTabelle);
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(scrollpane);
		add(content);	
		
		setVisible(true);
		
		
		
	}
	
	
	public static void main (String[] args) {
		
		TagFrame tag  = new TagFrame(new Date());
	
	}
}
