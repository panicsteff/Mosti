package gui.schichtverwaltung;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import kundenverwaltung.Formats;
import logik.schichtverwaltung.MitarbeiterNameCellRenderer;
import logik.schichtverwaltung.Schicht;
import logik.schichtverwaltung.SchichtLogik;
import logik.schichtverwaltung.SchichtTableModel;

public class SchichtTagFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private SchichtLogik schichtlogik;
	private Date datum;

	public SchichtTagFrame(Date d){
		
		schichtlogik = new SchichtLogik();
		datum = d;
		
		setSize(600,250);
		String title = Formats.DATE_FORMAT.format(d);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		for(int i=0; i<schichtlogik.getMitarbeiterProSchicht(); i++){
			JLabel mitarbeiter = new JLabel("Mitarbeiter"+(i+1)+": ");
			mitarbeiter.setBounds(0, 30+i*40, 100, 40);
			mitarbeiter.setFont(mitarbeiter.getFont().deriveFont(16f));
			add(mitarbeiter);
		}
		
		
		ArrayList<Schicht> liste = schichtlogik.schichtLaden(datum.getTime());
		SchichtTableModel schichtmodel= new SchichtTableModel(liste);
		JTable schichtplan = new JTable(schichtmodel);
		TableColumnModel tcm = schichtplan.getColumnModel();
		tcm.getColumn(0).setCellRenderer(new MitarbeiterNameCellRenderer());
		tcm.getColumn(1).setCellRenderer(new MitarbeiterNameCellRenderer());
		schichtplan.setFont(schichtplan.getFont().deriveFont(16f));
		schichtplan.setRowHeight(40);
		schichtplan.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollpane = new JScrollPane(schichtplan);
		scrollpane.setBounds(100, 10, 400, 300);
		add(scrollpane);
		

		
		setVisible(true);
	}
	
	
}
