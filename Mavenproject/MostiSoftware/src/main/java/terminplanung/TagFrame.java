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
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import kundenverwaltung.Formats;

public class TagFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Konfigurationswerte k = new Konfigurationswerte();
	private ArrayList<Integer> terminliste;
	private TermineTableModel termineTableModel;
	private TerminDB terminDb;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private ListSelectionModel terminSelectionModel;
	private JMenuBar mbar;
	private TerminHinzufügenFrame parent;

	TagFrame(Date d, int as, TerminHinzufügenFrame p) {
		parent = p;
		datum = d;
		anzeigeseite = as;	
		
		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		terminDb = new TerminDB();
		terminliste = terminDb.termineLaden(datum, anzeigeseite);
		

		termineTableModel = new TermineTableModel(terminliste, anzeigeseite);
		tagesTabelle = new JTable(termineTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tagesTabelle.setRowHeight(30);
		tagesTabelle.setFont(tagesTabelle.getFont().deriveFont(16f));
		tagesTabelle.getTableHeader().setFont(tagesTabelle.getTableHeader().getFont().deriveFont(16f));
		TableColumnModel tcm = tagesTabelle.getColumnModel();
		tcm.getColumn(0).setCellRenderer(new TermineCellRenderer());


		terminSelectionModel = tagesTabelle.getSelectionModel();
		terminSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tagesTabelle.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent event){
				if(event.getClickCount() == 2){
					int dauer = parent.getTerminlänge();
					int zeile = terminSelectionModel.getMaxSelectionIndex();
					int terminId = (Integer) termineTableModel.getValueAt(zeile, 0);
					new TerminErstellenFrame(dauer, datum, terminId, termineTableModel);
					termineTableModel.fireTableDataChanged();
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
				new TagFrame(datum, anzeigeseite - 1, parent);
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
				new TagFrame(datum, anzeigeseite + 1, parent);
				TagFrame.this.dispose();

			}
		});
		if (anzeigeseite == k.getAufteilung()) {									
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
	

}
