package terminplanung;

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

import kundenverwaltung.Formats;

public class TagFrame extends JFrame {

	class MyMouseListener extends MouseAdapter{
		public void mousePressed(MouseEvent event){
			if(event.getClickCount() == 2){
				
				if(parent == null){
					JOptionPane.showMessageDialog(null, "Du geile Sau");
				} else{
					int dauer = parent.getTerminlänge();
					int anzahlZeitslots = dauer/k.getZeitslot();
					int zeile = terminSelectionModel.getMaxSelectionIndex();
					ArrayList<Termin> t = termineTableModel.getTermine(zeile, anzahlZeitslots);
					String uhrzeit = termineCellRenderer.getText();
					new TerminErstellenDialog(dauer, datum, t, uhrzeit);
					termineTableModel.fireTableRowsUpdated(zeile, zeile + dauer/k.getZeitslot());
					terminlogik.termineSpeichern(termineTableModel.getAlleTermine(), datum);
				}
								
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	private Konfigurationswerte k = new Konfigurationswerte();
	private ArrayList<Termin> terminliste;
	private TermineTableModel termineTableModel;
	private TerminLogik terminlogik;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private ListSelectionModel terminSelectionModel;
	private TermineCellRenderer termineCellRenderer;
	private TerminHinzufügenFrame parent;

	TagFrame(Date d, int as, TerminHinzufügenFrame p) {
		parent = p;
		datum = d;
		anzeigeseite = as;	
		
		terminlogik = new TerminLogik();
		
		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		terminliste = terminlogik.termineLaden(datum, anzeigeseite);
		

		termineTableModel = new TermineTableModel(terminliste);
		tagesTabelle = new JTable(termineTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tagesTabelle.setRowHeight(30);
		tagesTabelle.setFont(tagesTabelle.getFont().deriveFont(16f));
		tagesTabelle.getTableHeader().setFont(tagesTabelle.getTableHeader().getFont().deriveFont(16f));
		TableColumnModel tcm = tagesTabelle.getColumnModel();
		termineCellRenderer = new TermineCellRenderer();
		tcm.getColumn(0).setCellRenderer(termineCellRenderer);
		tcm.getColumn(1).setCellRenderer(new KundenNameCellRenderer());


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
				new TagFrame(datum, anzeigeseite - 1, parent);
				TagFrame.this.dispose();
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
				new TagFrame(datum, anzeigeseite + 1, parent);
				TagFrame.this.dispose();

			}
		});
		if (anzeigeseite == k.getAufteilung()) {									
			cmdSpaeter.setEnabled(false);
		}
		
		setVisible(true);

	}
	

}
