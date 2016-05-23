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
					int anzahlZeitslots = dauer / k.getZeitslot();
					int zeile = terminSelectionModel.getMaxSelectionIndex()
							+ (anzeigeseite - 1) * k.getZeilenanzahlProSeite();

					if (zeile + anzahlZeitslots > k.getZeilenanzahlProSeite()
							* k.getAufteilung()) {
						JOptionPane.showMessageDialog(TagFrame.this,
								"Termin dauert zu lange");
					} else {

						ArrayList<Termin> termine = termineTableModel
								.getTermine(zeile, anzahlZeitslots);
						String uhrzeit = termineCellRenderer.getText();
						for (Termin t : termine) {
							if (t.getKundenId() != 0) {
								JOptionPane
										.showMessageDialog(TagFrame.this,
												"Nicht genug Zeit für einen Termin dieser Länge verfügbar");
								frei = false;
								break;
							}
						}
						if (frei == true) {
							new TerminErstellenDialog(dauer, datum, termine,
									uhrzeit);
							termineTableModel.fireTableRowsUpdated(zeile, zeile
									+ dauer / k.getZeitslot());
							terminSelectionModel.setSelectionInterval(zeile,
									zeile + dauer / k.getZeitslot()); // Damit
																		// anzeige
																		// aktualisiert
																		// wird
							terminlogik.termineSpeichern(
									termineTableModel.getAlleTermine(), datum);
						}
					}
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
	private int dauer;
	private ListSelectionModel terminSelectionModel;
	private TermineCellRenderer termineCellRenderer;
	private TerminHinzufügenFrame parent;

	private JButton cmdFrueher;
	private JButton cmdSpaeter;

	TagFrame(Date d, int as, TerminHinzufügenFrame p, int länge) {
		parent = p;
		datum = d;
		anzeigeseite = as;
		dauer = länge;

		terminlogik = new TerminLogik();

		setSize(500, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);

		terminliste = terminlogik.termineLaden(datum);

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
		boolean enabledf = terminlogik.isFrueherEnabled(anzeigeseite);
		cmdFrueher.setEnabled(enabledf);
		cmdFrueher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anzeigeseite--;
				boolean enabled = terminlogik.isFrueherEnabled(anzeigeseite);
				cmdFrueher.setEnabled(enabled);
				boolean enableds = terminlogik.isSpaeterEnabled(anzeigeseite);
				cmdSpaeter.setEnabled(enableds);
				termineTableModel.erniedrigeAnzeigeseite();
				termineTableModel.fireTableDataChanged();
			}
		});

		cmdSpaeter = new JButton("Später");
		cmdSpaeter.setBounds(260, 430, 80, 20);
		add(cmdSpaeter);
		boolean enabled = terminlogik.isSpaeterEnabled(anzeigeseite);
		cmdSpaeter.setEnabled(enabled);
		cmdSpaeter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anzeigeseite++;
				boolean enabled = terminlogik.isSpaeterEnabled(anzeigeseite);
				cmdSpaeter.setEnabled(enabled);
				boolean enabledf = terminlogik.isFrueherEnabled(anzeigeseite);
				cmdFrueher.setEnabled(enabledf);
				termineTableModel.erhoeheAnzeigeseite();
				termineTableModel.fireTableDataChanged();

			}
		});

		setVisible(true);

	}

}
