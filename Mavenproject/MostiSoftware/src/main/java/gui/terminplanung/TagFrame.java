package gui.terminplanung;

import gui.kassenfunktion.KassenFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import kundenverwaltung.Formats;
import logik.dienstleistungverwaltung.DLSortiment;
import logik.produktverwaltung.ProduktSortiment;
import logik.terminplanung.TagFrameController;
import logik.terminplanung.Termin;
import logik.verkaufsverwaltung.Verkaufsverwaltung;
import account.M_Startseite;

public class TagFrame extends JFrame {

	class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {

			boolean frei = true;

			if (event.getClickCount() == 2) {

				if (parent == null) {
					int pos = terminSelectionModel.getMaxSelectionIndex();
					int zeile = tagframecontroller.getZeile(pos, anzeigeseite);
					Termin t = termineTableModel.getTermine(zeile,1).get(0);
					if(t.getKundenId()==0){
						JOptionPane.showMessageDialog(TagFrame.this, "Bitte w�hlen sie einen bestehenden Termin zur Bearbeitung");
						return;
					}
					int l�nge = tagframecontroller.getTermindauer(t.getAnzahlZeitslots());	
					String uhrzeit = termineCellRenderer.getText();
					String name = kundenNameCellRenderer.getText();
					new TerminBearbeitenDialog(t, l�nge, name, uhrzeit, termineTableModel.getAlleTermine());
					termineTableModel.fireTableDataChanged();
					return;
				}
				if(parent instanceof M_Startseite){
					int pos = terminSelectionModel.getMaxSelectionIndex();
					int kundenid = (Integer) termineTableModel.getValueAt(pos, 1);
					if(kundenid == 0){
						JOptionPane.showMessageDialog(null, "Abrechnung nicht m�glich, da kein Kundentermin vorhanden");
						return;
					}
					DLSortiment dlSortiment = new DLSortiment(); 
					ProduktSortiment pSortiment = new ProduktSortiment();
					new KassenFrame(dlSortiment, pSortiment, new Verkaufsverwaltung(), kundenid);
				}else {
					int anzahlZeitslots = tagframecontroller.berechneAnzahlZeitslots(dauer);
					int zeile = tagframecontroller.getZeile(terminSelectionModel.getMaxSelectionIndex(), anzeigeseite);
					if (zeile + anzahlZeitslots > tagframecontroller.anzahlAlleTermine()) {
						JOptionPane.showMessageDialog(TagFrame.this,
								"Termin dauert zu lange. Tag ist schon vorbei");
					} else {

						ArrayList<Termin> termine = termineTableModel.getTermine(zeile, anzahlZeitslots);
						String uhrzeit = termineCellRenderer.getText();
						frei = tagframecontroller.istTerminFrei(termine);
						if (frei == true) {
							new TerminErstellenDialog(dauer, datum, termine,
									uhrzeit);
							termineTableModel.fireTableRowsUpdated(zeile, zeile + anzahlZeitslots);
							terminSelectionModel.setSelectionInterval(zeile, zeile + anzahlZeitslots); // Damit anzeige aktualisiert wird
							tagframecontroller.termineSpeichern(termine.get(0).getKundenId(), anzahlZeitslots, datum, termine.get(0).getUhrzeit() );
						}
					}
				}

			}
		}
	}
	
	class MySp�terHandler implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			anzeigeseite++;
			boolean enabled = tagframecontroller.isSpaeterEnabled(anzeigeseite);
			cmdSpaeter.setEnabled(enabled);
			boolean enabledf = tagframecontroller.isFrueherEnabled(anzeigeseite);
			cmdFrueher.setEnabled(enabledf);
			termineTableModel.erhoeheAnzeigeseite();
			termineTableModel.fireTableDataChanged();
		}
		
	}
	
	class MyFr�herHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			anzeigeseite--;
			boolean enabled = tagframecontroller.isFrueherEnabled(anzeigeseite);
			cmdFrueher.setEnabled(enabled);
			boolean enableds = tagframecontroller.isSpaeterEnabled(anzeigeseite);
			cmdSpaeter.setEnabled(enableds);
			termineTableModel.erniedrigeAnzeigeseite();
			termineTableModel.fireTableDataChanged();
		}
	}

	private static final long serialVersionUID = 1L;
	private ArrayList<Termin> terminliste;
	private TermineTableModel termineTableModel;
	private TagFrameController tagframecontroller;
	private JTable tagesTabelle;
	private Date datum;
	private int anzeigeseite;
	private int dauer;
	private ListSelectionModel terminSelectionModel;
	private TermineCellRenderer termineCellRenderer;
	private KundenNameCellRenderer kundenNameCellRenderer;
	private JFrame parent;
	private JButton cmdFrueher;
	private JButton cmdSpaeter;

	public TagFrame(long d, int as, JFrame p, int l�nge) {
		parent = p;
		datum = new Date(d);
		anzeigeseite = as;
		dauer = l�nge;

		tagframecontroller = new TagFrameController();

		setSize(520, 800);
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);

		terminliste = tagframecontroller.termineLaden(datum);

		termineTableModel = new TermineTableModel(terminliste, anzeigeseite);
		tagesTabelle = new JTable(termineTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tagesTabelle.setRowHeight(30);
		tagesTabelle.setFont(tagesTabelle.getFont().deriveFont(16f));
		tagesTabelle.getTableHeader().setFont(
				tagesTabelle.getTableHeader().getFont().deriveFont(16f));
		TableColumnModel tcm = tagesTabelle.getColumnModel();
		termineCellRenderer = new TermineCellRenderer();
		kundenNameCellRenderer = new KundenNameCellRenderer();
		tcm.getColumn(0).setCellRenderer(termineCellRenderer);
		tcm.getColumn(1).setCellRenderer(kundenNameCellRenderer);

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
		boolean enabledf = tagframecontroller.isFrueherEnabled(anzeigeseite);
		cmdFrueher.setEnabled(enabledf);
		cmdFrueher.addActionListener(new MyFr�herHandler());

		cmdSpaeter = new JButton("Sp�ter");
		cmdSpaeter.setBounds(260, 430, 80, 20);
		add(cmdSpaeter);
		boolean enabled = tagframecontroller.isSpaeterEnabled(anzeigeseite);
		cmdSpaeter.setEnabled(enabled);
		cmdSpaeter.addActionListener(new MySp�terHandler());

		setVisible(true);

	}

}
