package terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.sql.Date;

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
							termineTableModel.fireTableRowsUpdated(zeile, zeile + anzahlZeitslots);
							terminSelectionModel.setSelectionInterval(zeile, zeile + anzahlZeitslots); // Damit anzeige aktualisiert wird
							tagframelogik.termineSpeichern(termine.get(0).getKundenId(), anzahlZeitslots, datum, termine.get(0).getUhrzeit() );
						}
					}
				}

			}
		}
	}
	
	class MySpäterHandler implements ActionListener{

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
	
	class MyFrüherHandler implements ActionListener{

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
	private TagFrameController tagframelogik;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private int dauer;
	private ListSelectionModel terminSelectionModel;
	private TermineCellRenderer termineCellRenderer;
	private TerminHinzufügenFrame parent;
	private JButton cmdFrueher;
	private JButton cmdSpaeter;

	TagFrame(long d, int as, TerminHinzufügenFrame p, int länge) {
		parent = p;
		datum = new Date(d);
		anzeigeseite = as;
		dauer = länge;

		tagframelogik = new TagFrameController();

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

		cmdFrueher = new JButton("Früher");
		cmdFrueher.setBounds(150, 430, 80, 20);
		add(cmdFrueher);
		boolean enabledf = tagframelogik.isFrueherEnabled(anzeigeseite);
		cmdFrueher.setEnabled(enabledf);
		cmdFrueher.addActionListener(new MyFrüherHandler());

		cmdSpaeter = new JButton("Später");
		cmdSpaeter.setBounds(260, 430, 80, 20);
		add(cmdSpaeter);
		boolean enabled = tagframelogik.isSpaeterEnabled(anzeigeseite);
		cmdSpaeter.setEnabled(enabled);
		cmdSpaeter.addActionListener(new MySpäterHandler());

		setVisible(true);

	}

}
