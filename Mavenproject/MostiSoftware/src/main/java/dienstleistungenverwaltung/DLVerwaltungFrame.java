package dienstleistungenverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import lagerverwaltung.PreisCellRenderer;


	@SuppressWarnings("serial")
	public class DLVerwaltungFrame extends JFrame {

		private DLTableModel dlTableModel;
		private ListSelectionModel dlSelectionModel;
		private List<Dienstleistung> pliste;
		private JMenuItem bearDL;

		public DLVerwaltungFrame (List<Dienstleistung> auflistung) {

			pliste = auflistung;

			setTitle("Dienstleistungen verwalten");
			setSize(700, 400);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			JMenuBar menubar = new JMenuBar();
			setJMenuBar(menubar);

			JMenu datei = new JMenu("Datei");
			menubar.add(datei);
			datei.addSeparator();
			JMenuItem beenden = new JMenuItem("Beenden");
			datei.add(beenden);

			beenden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					dispose();
				}
			});

			JMenu bearbeiten = new JMenu("Bearbeiten");
			menubar.add(bearbeiten);
			JMenuItem newDL = new JMenuItem("Neue Dienstleistung hinzufügen");
			bearbeiten.add(newDL);
			bearbeiten.addSeparator();
			bearDL = new JMenuItem("Dienstleistung bearbeiten");
			bearbeiten.add(bearDL);
			bearbeiten.addSeparator();
			JMenuItem loescheDL = new JMenuItem("Dienstleistung löschen");
			bearbeiten.add(loescheDL);

			newDL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					addDL();
				}
			});
			
			loescheDL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					deleteDL();
				}
			});

			bearDL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					bearbeiteDL();
				}
			});
			bearDL.setEnabled(false);

			dlTableModel = new DLTableModel(pliste);
			JTable dlTabelle = new JTable(dlTableModel);

			dlSelectionModel = dlTabelle.getSelectionModel();
			dlSelectionModel
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dlSelectionModel
					.addListSelectionListener(new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent listevent) {
							if (listevent.getFirstIndex() == -1)
								bearDL.setEnabled(false);
							else
								bearDL.setEnabled(true);
						}

					});

			dlTabelle.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseevent) {
					if (mouseevent.getClickCount() == 2)
						bearbeiteDL();
				}
			});
			
			TableColumn preisspalte = dlTabelle.getColumnModel().getColumn(1);
			preisspalte.setCellRenderer(new PreisCellRenderer());


			dlTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dlTabelle.getColumnModel().getColumn(0).setPreferredWidth(200);
			dlTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);


			JScrollPane scrollpane = new JScrollPane(dlTabelle);
			JPanel titlepane = new JPanel();
			titlepane.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), "Dienstleistungen"));
			titlepane.setLayout(new BorderLayout());
			titlepane.add(scrollpane);

			add(titlepane);
			setVisible(true);

		}

		private void addDL() {
			new DLHinzufuegenFrame(this, pliste);
			dlTableModel.fireTableDataChanged();
		}
		
		private void deleteDL() {
			int row = dlSelectionModel.getMinSelectionIndex();
			pliste.remove(row);
			dlTableModel.fireTableDataChanged();
		}

		private void bearbeiteDL() {
			int row = dlSelectionModel.getMinSelectionIndex();
			new DLBearbeitenFrame(this, dlTableModel.getDienstleistung(row));
			dlTableModel.fireTableRowsUpdated(row, row);
		}

}
