package administratorverwaltung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import kundenverwaltung.Formats;

public class SchichtTagFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public SchichtTagFrame(Date d){
		
		setSize(400,250);
		String title = Formats.DATE_FORMAT.format(d);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		
		JLabel kassierer = new JLabel("Kassierer: ");
		kassierer.setBounds(0, 30, 100, 40);
		kassierer.setFont(kassierer.getFont().deriveFont(16f));
		add(kassierer);
		
		JLabel abfüller = new JLabel("Abfüller: ");
		abfüller.setBounds(0, 70, 100, 40);
		abfüller.setFont(abfüller.getFont().deriveFont(16f));
		add(abfüller);
		
		JLabel presser = new JLabel("Presser: ");
		presser.setBounds(0, 110, 100, 40);
		presser.setFont(presser.getFont().deriveFont(16f));
		add(presser);
		
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		
		for(int i = 0; i<4; i++){
			Schicht s = new Schicht();
			s.setSchichtId(i+1);
			ArrayList<Integer> ids = new ArrayList<Integer>();
			
			for(int j=0; j<3; j++){
				ids.add(5+i);
			}
			s.setMitarbeiterIds(ids);
			liste.add(s);
		}
		
		SchichtTableModel schichtmodel= new SchichtTableModel(liste);
		JTable schichtplan = new JTable(schichtmodel);
		schichtplan.setFont(schichtplan.getFont().deriveFont(16f));
		schichtplan.setRowHeight(40);
		schichtplan.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollpane = new JScrollPane(schichtplan);
		scrollpane.setBounds(100, 10, 200, 144);
		add(scrollpane);
		

		
		setVisible(true);
	}
	
	
}
