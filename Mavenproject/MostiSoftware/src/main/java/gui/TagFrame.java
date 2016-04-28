package gui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import kundenverwaltung.Formats;
import terminplanung.TagTableModel;
import terminplanung.Termin;
import terminplanung.TerminDB;

public class TagFrame extends JDialog{

	private ArrayList<Termin> terminliste;
	TagTableModel tagTableModel;
	private TerminDB terminDb;
	SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
	
	public TagFrame (Date datum){
		terminDb = new TerminDB();
		terminliste = terminDb.termineLaden(datum);
		setSize(300,400);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		tagTableModel = new TagTableModel(terminliste);
		JTable tagesTabelle = new JTable(tagTableModel);
		
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
