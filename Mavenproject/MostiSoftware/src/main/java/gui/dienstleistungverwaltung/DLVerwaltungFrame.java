package gui.dienstleistungverwaltung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import logik.dienstleistungverwaltung.DLSortiment;
import logik.dienstleistungverwaltung.Dienstleistung;
import logik.produktverwaltung.PreisCellRenderer;


	@SuppressWarnings("serial")
	public class DLVerwaltungFrame extends JFrame {

		private DLTableModel dlTableModel;
		private ListSelectionModel dlSelectionModel;
		private JMenuItem bearDL;
		private JMenuItem loescheDL;
		private DLSortiment dlSortiment;

		public DLVerwaltungFrame (DLSortiment d) {
			this.dlSortiment = d;

			setTitle("Dienstleistungen verwalten");
			setSize(700, 400);
			setLocationRelativeTo(getParent());
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			JMenuBar menubar = new JMenuBar();
			setJMenuBar(menubar);

			JMenu datei = new JMenu("Datei");
			datei.setFont(datei.getFont().deriveFont(16f));
			menubar.add(datei);
			datei.addSeparator();
			JMenuItem beenden = new JMenuItem("Beenden");
			beenden.setFont(beenden.getFont().deriveFont(16f));
			datei.add(beenden);

			beenden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					dispose();
				}
			});

			JMenu bearbeiten = new JMenu("Bearbeiten");
			bearbeiten.setFont(bearbeiten.getFont().deriveFont(16f));
			menubar.add(bearbeiten);
			JMenuItem newDL = new JMenuItem("Neue Dienstleistung hinzufügen");
			bearbeiten.add(newDL);
			newDL.setFont(newDL.getFont().deriveFont(16f));
			bearbeiten.addSeparator();
			bearDL = new JMenuItem("Dienstleistung bearbeiten");
			bearbeiten.add(bearDL);
			bearDL.setFont(bearDL.getFont().deriveFont(16f));
			bearbeiten.addSeparator();
			loescheDL = new JMenuItem("Dienstleistung löschen");
			loescheDL.setFont(loescheDL.getFont().deriveFont(16f));
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
			loescheDL.setEnabled(false);

			dlTableModel = new DLTableModel(dlSortiment.getDLSortiment());
			JTable dlTabelle = new JTable(dlTableModel);

			dlSelectionModel = dlTabelle.getSelectionModel();
			dlSelectionModel
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dlSelectionModel
					.addListSelectionListener(new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent listevent) {
							if (listevent.getFirstIndex() == -1){
								bearDL.setEnabled(false);
								loescheDL.setEnabled(false);
								}
							else{
								bearDL.setEnabled(true);
								loescheDL.setEnabled(true);
								
							}
								
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

			dlTabelle.setRowHeight(30);
			dlTabelle.getTableHeader().setFont(dlTabelle.getFont().deriveFont(16f));
			dlTabelle.setFont(dlTabelle.getFont().deriveFont(16f));
			dlTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dlTabelle.getColumnModel().getColumn(0).setPreferredWidth(200);
			dlTabelle.getColumnModel().getColumn(1).setPreferredWidth(200);


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
			new DLHinzufuegenFrame(this, dlSortiment);
			dlTableModel.fireTableDataChanged();
		}
		
		private void deleteDL() {
			int row = dlSelectionModel.getMinSelectionIndex();
			Dienstleistung d = dlTableModel.getDienstleistung(row);
			int result = JOptionPane.showConfirmDialog(this, "Möchten Sie die Dienstleistung \"" + d.getName() + "\" wirklich löschen?",
					"Frage", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION)
				return;
			dlSortiment.deleteDienstleistung(d);
			dlTableModel.fireTableDataChanged();
		}

		private void bearbeiteDL() {
			int row = dlSelectionModel.getMinSelectionIndex();
			Dienstleistung d = dlTableModel.getDienstleistung(row);
			new DLBearbeitenFrame(this, d);
			//dlSortiment.dienstleistungenAktualisieren();
			dlSortiment.dienstleistungAktualisieren(d);
			dlTableModel.fireTableRowsUpdated(row, row);
		}

}
