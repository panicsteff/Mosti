package gui_schicht;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;

import terminplanung.Tag;
import terminplanung.TagTableModel;
import terminplanung.Termin;

public class TagFrame extends JDialog{

	private ArrayList<Termin> terminliste;
	
	public TagFrame (Tag neuerTag){
		terminliste = neuerTag.getTerminliste();
		setSize(300,400);
		setTitle("<Datum>");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		TagTableModel tagTableModel = new TagTableModel(terminliste);
		JTable tagTabelle = new JTable(tagTableModel);
		JPanel content = new JPanel();
		content.add(tagTabelle);
		
		add(content);		
		
		setVisible(true);
		
		
	}
	
	
	public static void main (String[] args){
		Tag neuerTag = new Tag();
		
		Termin t1 = new Termin();
		Termin t2 = new Termin();
		Termin t3 = new Termin();
		
		neuerTag.fuegeTerminHinzu(t1);
		neuerTag.fuegeTerminHinzu(t2);
		neuerTag.fuegeTerminHinzu(t3);
		
		TagFrame tag  = new TagFrame(neuerTag);
			
	}
}
