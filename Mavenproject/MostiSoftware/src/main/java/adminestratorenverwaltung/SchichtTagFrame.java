package adminestratorenverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import mitarbeiterverwaltung.Formats;

public class SchichtTagFrame extends JFrame{
	
	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent event){
			if(event.getClickCount() == 2){
				
				if(parent == null){
					JOptionPane.showMessageDialog(null, "Schicht bearbeiten");
				} else{
					int dauer = parent.getSchichtlänge();
					int anzahlZeitslots = dauer/k.getZeitslot();
					int zeile = schichtSelectionModel.getMaxSelectionIndex();
					ArrayList<Schicht> s = schichtTableModel.getSchicht(zeile, anzahlZeitslots);
					String uhrzeit = schichtCellRenderer.getText();
					new SchichtErstellenDialog(dauer, datum, s, uhrzeit);
					schichtTableModel.fireTableRowsUpdated(zeile, zeile + dauer/k.getZeitslot());
					schichtlogik.schichtSpeichern(schichtTableModel.getAlleSchichten(), datum);
				}
								
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	private Konfigurationswerte k = new Konfigurationswerte();
	private ArrayList<Schicht> schichtliste;
	private SchichtTableModel schichtTableModel;
	private SchichtLogik schichtlogik;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private ListSelectionModel terminSelectionModel;
	private SchichtCellRenderer schichtCellRenderer;
	private SchichtHinzufügenFrame parent;

	SchichtTagFrame(Date d, int as, SchichtHinzufügenFrame p) {
		parent = p;
		datum = d;
		anzeigeseite = as;	
		
		schichtlogik = new SchichtLogik();
		
		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		schichtliste = schichtlogik.schichtLaden(datum, anzeigeseite);
		

		schichtTableModel = new SchichtTableModel(schichtliste);
		tagesTabelle = new JTable(schichtTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tagesTabelle.setRowHeight(30);
		tagesTabelle.setFont(tagesTabelle.getFont().deriveFont(16f));
		tagesTabelle.getTableHeader().setFont(tagesTabelle.getTableHeader().getFont().deriveFont(16f));
		TableColumnModel tcm = tagesTabelle.getColumnModel();
		schichtCellRenderer = new SchichtCellRenderer();
		tcm.getColumn(0).setCellRenderer(schichtCellRenderer);
		tcm.getColumn(1).setCellRenderer(new MitarbeiterNameCellRenderer());


		terminSelectionModel = tagesTabelle.getSelectionModel();
		terminSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tagesTabelle.addMouseListener(new MyMouseListener());

		JScrollPane scrollpane = new JScrollPane(tagesTabelle);
		scrollpane.setBounds(0, 0, 500, 400);
		add(scrollpane);
		

		
		JButton cmdFrueher = new JButton("Früher");
		cmdFrueher.setBounds(150, 430, 80, 20);
		add(cmdFrueher);
		cmdFrueher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SchichtTagFrame(datum, anzeigeseite - 1, parent);
				SchichtTagFrame.this.dispose();
			}
		});
		if (anzeigeseite == 1) {
			cmdFrueher.setEnabled(false);
		}
		
		JButton cmdSpaeter = new JButton("Später");
		cmdSpaeter.setBounds(260, 430, 80, 20);
		add(cmdSpaeter);
		cmdSpaeter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new SchichtTagFrame(datum, anzeigeseite + 1, parent);
				SchichtTagFrame.this.dispose();

			}
		});
		if (anzeigeseite == k.getAufteilung()) {									
			cmdSpaeter.setEnabled(false);
		}
		
		setVisible(true);

	}
	


}
