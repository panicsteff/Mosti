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

	class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {

			boolean frei = true;

			if (event.getClickCount() == 2) {

				if (parent == null) {
					JOptionPane.showMessageDialog(null, "Termin bearbeiten");
				} else {
					int anzahlZeitslots = tagframelogik.berechneAnzahlZeitslots(dauer);
					int zeile = tagframelogik.getZeile(terminSelectionModel.getMaxSelectionIndex(), anzeigeseite);
					if (zeile + anzahlZeitslots > tagframelogik.anzahlAlleTermine()) {
						JOptionPane.showMessageDialog(TagFrame.this,
								"Termin dauert zu lange. Tag ist schon vorbei");
					} else {

						ArrayList<Termin> termine = termineTableModel.getTermine(zeile, anzahlZeitslots);
						String uhrzeit = termineCellRenderer.getText();
						frei = tagframelogik.istTerminFrei(termine);
						if (frei == true) {
							new TerminErstellenDialog(dauer, datum, termine,
									uhrzeit);
							termineTableModel.fireTableRowsUpdated(zeile, zeile + tagframelogik.berechneAnzahlZeitslots(dauer));
							terminSelectionModel.setSelectionInterval(zeile, zeile + tagframelogik.berechneAnzahlZeitslots(dauer)); // Damit anzeige aktualisiert wird
							tagframelogik.termineSpeichern(termineTableModel.getAlleTermine(), datum);
						}
					}
				}

			}
		}
	}
	
	class MySp�terHandler implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			anzeigeseite++;
			boolean enabled = tagframelogik.isSpaeterEnabled(anzeigeseite);
			cmdSpaeter.setEnabled(enabled);
			boolean enabledf = tagframelogik.isFrueherEnabled(anzeigeseite);
			cmdFrueher.setEnabled(enabledf);
			termineTableModel.erhoeheAnzeigeseite();
			termineTableModel.fireTableDataChanged();
		}
		
	}
	
	class MyFr�herHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			anzeigeseite--;
			boolean enabled = tagframelogik.isFrueherEnabled(anzeigeseite);
			cmdFrueher.setEnabled(enabled);
			boolean enableds = tagframelogik.isSpaeterEnabled(anzeigeseite);
			cmdSpaeter.setEnabled(enableds);
			termineTableModel.erniedrigeAnzeigeseite();
			termineTableModel.fireTableDataChanged();
		}
	}

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> terminliste;
	private TermineTableModel termineTableModel;
	private TerminLogik terminlogik;
	private TagFrameLogik tagframelogik;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private int dauer;
	private ListSelectionModel terminSelectionModel;
	private TermineCellRenderer termineCellRenderer;
	private TerminHinzuf�genFrame parent;
	private JButton cmdFrueher;
	private JButton cmdSpaeter;

	TagFrame(Date d, int as, TerminHinzuf�genFrame p, int l�nge) {
		parent = p;
		datum = d;
		anzeigeseite = as;
		dauer = l�nge;

		terminlogik = new TerminLogik();
		tagframelogik = new TagFrameLogik();

		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);

		terminliste = tagframelogik.termineLaden(datum);

		termineTableModel = new TermineTableModel(terminliste, anzeigeseite);
		tagesTabelle = new JTable(termineTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tagesTabelle.setRowHeight(30);
		tagesTabelle.setFont(tagesTabelle.getFont().deriveFont(16f));
		tagesTabelle.getTableHeader().setFont(
				tagesTabelle.getTableHeader().getFont().deriveFont(16f));
		TableColumnModel tcm = tagesTabelle.getColumnModel();
		termineCellRenderer = new TermineCellRenderer();
		tcm.getColumn(0).setCellRenderer(termineCellRenderer);
		tcm.getColumn(1).setCellRenderer(new KundenNameCellRenderer());

		terminSelectionModel = tagesTabelle.getSelectionModel();
		terminSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tagesTabelle.addMouseListener(new MyMouseListener());

		JScrollPane scrollpane = new JScrollPane(tagesTabelle);
		scrollpane.setBounds(0, 0, 500, 400);
		add(scrollpane);

		cmdFrueher = new JButton("Fr�her");
		cmdFrueher.setBounds(150, 430, 80, 20);
		add(cmdFrueher);
		boolean enabledf = tagframelogik.isFrueherEnabled(anzeigeseite);
		cmdFrueher.setEnabled(enabledf);
		cmdFrueher.addActionListener(new MyFr�herHandler());

		cmdSpaeter = new JButton("Sp�ter");
		cmdSpaeter.setBounds(260, 430, 80, 20);
		add(cmdSpaeter);
		boolean enabled = tagframelogik.isSpaeterEnabled(anzeigeseite);
		cmdSpaeter.setEnabled(enabled);
		cmdSpaeter.addActionListener(new MySp�terHandler());

		setVisible(true);

	}

}
