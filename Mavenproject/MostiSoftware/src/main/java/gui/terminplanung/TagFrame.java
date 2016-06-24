package gui.terminplanung;

import gui.account.M_Startseite;
import gui.kassenfunktion.KassenFrame;
import gui.trester.TresterabrechnungFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import logik.dienstleistungverwaltung.DLSortiment;
import logik.kundenverwaltung.Formats;
import logik.produktverwaltung.ProduktSortiment;
import logik.terminplanung.TagFrameController;
import logik.terminplanung.Termin;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

public class TagFrame extends JDialog {

	class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {

			boolean frei = true;

			if (event.getClickCount() == 2) {

				if (parent instanceof TerminplanungsFrame) {
					int pos = terminSelectionModel.getMaxSelectionIndex();
					int zeile = tagframecontroller.getZeile(pos, anzeigeseite);
					Termin t = termineTableModel.getTermine(zeile,1).get(0);
					t = tagframecontroller.startTerminfinden(termineTableModel.getAlleTermine(), t);
					if(t.getKundenId()==0){
						JOptionPane.showMessageDialog(TagFrame.this, "Bitte wählen sie einen bestehenden Termin zur Bearbeitung");
						return;
					}
					int länge = tagframecontroller.getTermindauer(t.getAnzahlZeitslots());
					
					String uhrzeit = tagframecontroller.terminNachUhrzeit(t.getUhrzeit());
					String name = kundenNameCellRenderer.getText();
					new TerminBearbeitenDialog(t, länge, name, uhrzeit, termineTableModel.getAlleTermine());
					termineTableModel.fireTableDataChanged();
					return;
				}
				if(parent instanceof M_Startseite){
					int pos = terminSelectionModel.getMaxSelectionIndex();
					int kundenid = (Integer) termineTableModel.getValueAt(pos, 1);
					if(kundenid == 0){
						JOptionPane.showMessageDialog(null, "Abrechnung nicht möglich, da kein Kundentermin vorhanden");
						return;
					}
					DLSortiment dlSortiment = new DLSortiment(); 
					ProduktSortiment pSortiment = new ProduktSortiment();
					new KassenFrame(dlSortiment, pSortiment, new Verkaufsverwaltung(), kundenid);
					TagFrame.this.dispose();
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
									uhrzeit, menge, flaschen);
							termineTableModel.fireTableRowsUpdated(zeile, zeile + anzahlZeitslots);
							terminSelectionModel.setSelectionInterval(zeile, zeile + anzahlZeitslots); // Damit anzeige aktualisiert wird
							tagframecontroller.termineSpeichern(termine.get(0).getKundenId(), anzahlZeitslots, datum, termine.get(0).getUhrzeit(), menge, flaschen );
						}
					}
				}

			}
		}
	}
	
	class MyKeyListener extends KeyAdapter {
		public void keyTyped(KeyEvent k) {
			
			boolean delete = false;
			
			char c = k.getKeyChar();
			if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
				eingabe = eingabe + c;
				delete = false;
			}
			if (c == '\b') {
				if (eingabe.length() > 0) {
					eingabe = eingabe.substring(0, eingabe.length() - 1);
					delete = true;
				}
			}
			if(eingabe != ""){
				tresterKunde.showPopup();
				kundenIds = tagframecontroller.kundenIdLaden(eingabe);
				tresterKunde.removeAllItems();
				
				for(Integer i : kundenIds){
					tresterKunde.addItem(tagframecontroller.kundenNameLaden(i));
				}
				tresterKunde.setSelectedItem(null);
				if(delete == true){
					tresterKunde.getEditor().setItem(eingabe);
				}else{
					if(eingabe.length() - 1 >= 0){
						tresterKunde.getEditor().setItem(eingabe.substring(0, eingabe.length() - 1));
					}
					
				}
			}else{
				tresterKunde.hidePopup();
			}
			
		}
	}
	
	class MySpäterHandler implements ActionListener{

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
	
	class MyFrüherHandler implements ActionListener{

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
	private String eingabe;
	private ListSelectionModel terminSelectionModel;
	private TermineCellRenderer termineCellRenderer;
	private KundenNameCellRenderer kundenNameCellRenderer;
	private JFrame parent;
	private JButton cmdFrueher;
	private JButton cmdSpaeter;
	private JComboBox<String> tresterKunde;
	private int kundenId;
	private ArrayList<Integer> kundenIds;
	private int menge;
	private boolean flaschen;
	private boolean neu;

	public TagFrame(long d, int as, JFrame p, int länge, int menge, boolean flaschen) {
		parent = p;
		datum = new Date(d);
		anzeigeseite = as;
		dauer = länge;
		eingabe = "";
		kundenIds = new ArrayList<Integer>();
		this.menge = menge;
		this.flaschen = flaschen;

		tagframecontroller = new TagFrameController();

		setSize(520, 600);
		setLocationRelativeTo(getParent());
		String title = Formats.DATE_FORMAT.format(datum);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);

		terminliste = tagframecontroller.termineLaden(datum);

		termineTableModel = new TermineTableModel(terminliste, anzeigeseite);
		tagesTabelle = new JTable(termineTableModel);
		tagesTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tagesTabelle.setRowHeight(30);
		tagesTabelle.setFont(tagesTabelle.getFont().deriveFont(16f));
		tagesTabelle.getTableHeader().setFont(
				tagesTabelle.getTableHeader().getFont().deriveFont(16f));
		TableColumnModel tcm = tagesTabelle.getColumnModel();
		termineCellRenderer = new TermineCellRenderer();
		kundenNameCellRenderer = new KundenNameCellRenderer();
		tcm.getColumn(0).setCellRenderer(termineCellRenderer);
		tcm.getColumn(1).setCellRenderer(kundenNameCellRenderer);
		tcm.getColumn(1).setMinWidth(250);

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
		boolean enabledf = tagframecontroller.isFrueherEnabled(anzeigeseite);
		cmdFrueher.setEnabled(enabledf);
		cmdFrueher.addActionListener(new MyFrüherHandler());

		cmdSpaeter = new JButton("Später");
		cmdSpaeter.setBounds(260, 430, 80, 20);
		add(cmdSpaeter);
		boolean enabled = tagframecontroller.isSpaeterEnabled(anzeigeseite);
		cmdSpaeter.setEnabled(enabled);
		cmdSpaeter.addActionListener(new MySpäterHandler());

		JLabel kunde = new JLabel("Tresterkunde: ");
		kunde.setBounds(20, 500, 200, 40);
		add(kunde);
		
		tresterKunde = new JComboBox<String>();
		tresterKunde.setBounds(230, 500, 200, 40);
		tresterKunde.setEditable(true);
		add(tresterKunde);
		tresterKunde.getEditor().getEditorComponent().addKeyListener(new MyKeyListener());
		kundenId = tagframecontroller.tresterKundeLaden(datum);
		neu = false;
		if(kundenId == 0){
			neu = true;
		}else{
			tresterKunde.getEditor().setItem(tagframecontroller.kundenNameLaden(kundenId));
		}
		tresterKunde.getEditor().getEditorComponent().addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent m){
				if(m.getClickCount() == 2){
					if(tresterKunde.getEditor().getItem().equals("") == true){
						JOptionPane.showMessageDialog(TagFrame.this, "Bitte wählen Sie zuerst eine Tresterkunden aus");
						return;
					}
					int index = tresterKunde.getSelectedIndex();
					if(index < 0){									//Combobox wurde nicht angeworfen und es hat sich nix geändert
						new TresterabrechnungFrame(kundenId);								
					}
					else{
						new TresterabrechnungFrame(kundenIds.get(index));
					}
				}
			}
		});
		
		addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent arg0) {
				if(tresterKunde.getEditor().getItem().equals("")){
					tagframecontroller.tresterKundeLöschen(datum);
					return;
				}
				int index = tresterKunde.getSelectedIndex();
				if(index < 0){								//Combobox wurde nicht angeworfen und es hat sich nix geändert
					return;
				}
				int kundenId = kundenIds.get(index);
				tagframecontroller.tresterKundeSpeichern(datum, kundenId, neu);
			}
			
		});
		
		setVisible(true);

	}

}
