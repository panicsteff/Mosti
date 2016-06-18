package gui.trester;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import logik.produktverwaltung.PreisCellRenderer;
import logik.trester.Tresterabrechnung;

@SuppressWarnings("serial")
public class TresterFrame extends JFrame {

	private ArrayList<Tresterabrechnung> tresterverkäufe;
	private TresterTableModel tTableModel;
	private JLabel label;

	public TresterFrame(ArrayList<Tresterabrechnung> liste) {
		tresterverkäufe = liste;

		setTitle("Tresterverkäufe");
		setSize(700, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu datei = new JMenu("Datei");
		menubar.add(datei);
		JMenuItem neueÜbersichtItem = new JMenuItem("Neue Trester-Verkaufsübersicht erstellen");
		datei.add(neueÜbersichtItem);
		datei.addSeparator();
		JMenuItem beenden = new JMenuItem("Schließen");
		datei.add(beenden);
		
		neueÜbersichtItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new TresterübersichtFrame();
				dispose();
			}
		});

		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		tTableModel = new TresterTableModel(tresterverkäufe);
		JTable tTabelle = new JTable(tTableModel);

		TableColumn preisspalte = tTabelle.getColumnModel().getColumn(2);
		preisspalte.setCellRenderer(new PreisCellRenderer());
		
		TableColumn summenspalte = tTabelle.getColumnModel().getColumn(3);
		summenspalte.setCellRenderer(new PreisCellRenderer());
		
		TableColumn datumsspalte = tTabelle.getColumnModel().getColumn(4);
		datumsspalte.setCellRenderer(new DatumRechtsbuendigCellRenderer());

		tTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tTabelle.getColumnModel().getColumn(0).setPreferredWidth(150);
		tTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
		tTabelle.getColumnModel().getColumn(2).setPreferredWidth(150);
		tTabelle.getColumnModel().getColumn(3).setPreferredWidth(100);
		tTabelle.getColumnModel().getColumn(4).setPreferredWidth(150);

		JScrollPane scrollpane = new JScrollPane(tTabelle);
		JPanel titlepanel = new JPanel();
		titlepanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Tresterverkäufe"));
		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(scrollpane, BorderLayout.CENTER);

		JPanel summenPanel = new JPanel();
		summenPanel.setLayout(new GridLayout(2, 2));
		label = new JLabel("Kostensumme gesamt");
		summenPanel.add(label);
		label = new JLabel();
		label.setText(String.valueOf(tTableModel.berechneKostenGesamt()) + " €");
		summenPanel.add(label);
		label = new JLabel("Literzahl gesamt");
		summenPanel.add(label);
		label = new JLabel();
		label.setText(String.valueOf(tTableModel.berechneLiterGesamt()) + " L");
		summenPanel.add(label);
		
		titlepanel.add(summenPanel, BorderLayout.SOUTH);

//		panel.add(titlepane, BorderLayout.NORTH);
//		panel.add(summenPanel, BorderLayout.CENTER);
		add(titlepanel);
		setVisible(true);

	}

//	public static void main(String[] args) {
//		ArrayList<Verkaufsposition> vliste = new ArrayList<Verkaufsposition>();
//		Verkaufsposition v1 = new Verkaufsposition("Quittensaft", 1.00, 0, 200);
//		Verkaufsposition v2 = new Verkaufsposition("Apfelsaft", 1.00, 0, 150);
//		Verkaufsposition v3 = new Verkaufsposition("Turbohefe", 1.00, 10, 0);
//		Verkaufsposition v4 = new Verkaufsposition("5L-Beutel", 1.00, 40, 0);
//		vliste.add(v1);
//		vliste.add(v2);
//		vliste.add(v3);
//		vliste.add(v4);
//		Verkaufsposition v5 = new Verkaufsposition("Quittensaft", 1.00, 0, 200);
//		Verkaufsposition v6 = new Verkaufsposition("Apfelsaft", 1.00, 0, 150);
//		Verkaufsposition v7 = new Verkaufsposition("Turbohefe", 1.00, 10, 0);
//		Verkaufsposition v8 = new Verkaufsposition("5L-Beutel", 1.00, 40, 0);
//		vliste.add(v5);
//		vliste.add(v6);
//		vliste.add(v7);
//		vliste.add(v8);
//		Verkaufsposition v9 = new Verkaufsposition("Quittensaft", 1.00, 0, 200);
//		Verkaufsposition v10 = new Verkaufsposition("Apfelsaft", 1.00, 0, 150);
//		Verkaufsposition v11 = new Verkaufsposition("Turbohefe", 1.00, 10, 0);
//		Verkaufsposition v12 = new Verkaufsposition("5L-Beutel", 1.00, 40, 0);
//		vliste.add(v9);
//		vliste.add(v10);
//		vliste.add(v11);
//		vliste.add(v12);
//
//		VerkäufeFrame vFrame = new VerkäufeFrame(vliste);
//	}
}
