package verkaufsverwaltung;

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

import lagerverwaltung.PreisCellRenderer;

@SuppressWarnings("serial")
public class VerkäufeFrame extends JFrame {

	private ArrayList<Verkaufsposition> einkäufe;
	private VerkäufeTableModel vTableModel;
	private JLabel label;

	public VerkäufeFrame(ArrayList<Verkaufsposition> liste) {
		einkäufe = liste;

		setTitle("Verkäufe");
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

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		vTableModel = new VerkäufeTableModel(einkäufe);
		JTable vTabelle = new JTable(vTableModel);

		TableColumn preisspalte = vTabelle.getColumnModel().getColumn(1);
		preisspalte.setCellRenderer(new PreisCellRenderer());

		vTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		vTabelle.getColumnModel().getColumn(0).setPreferredWidth(200);
		vTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
		vTabelle.getColumnModel().getColumn(2).setPreferredWidth(100);
		vTabelle.getColumnModel().getColumn(3).setPreferredWidth(100);

		JScrollPane scrollpane = new JScrollPane(vTabelle);
		JPanel titlepanel = new JPanel();
		titlepanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Verkäufe"));
		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(scrollpane, BorderLayout.CENTER);

		JPanel summenPanel = new JPanel();
		summenPanel.setLayout(new GridLayout(2, 2));
		label = new JLabel("Kostensumme gesamt");
		summenPanel.add(label);
		label = new JLabel();
		label.setText(String.valueOf(vTableModel.berechneKostenGesamt()) + " €");
		summenPanel.add(label);
		label = new JLabel("Literzahl gesamt");
		summenPanel.add(label);
		label = new JLabel();
		label.setText(String.valueOf(vTableModel.berechneLiterGesamt()) + " L");
		summenPanel.add(label);
		
		titlepanel.add(summenPanel, BorderLayout.SOUTH);

//		panel.add(titlepane, BorderLayout.NORTH);
//		panel.add(summenPanel, BorderLayout.CENTER);
		add(titlepanel);
		setVisible(true);

	}

	public static void main(String[] args) {
		ArrayList<Verkaufsposition> vliste = new ArrayList<Verkaufsposition>();
		Verkaufsposition v1 = new Verkaufsposition("Quittensaft", 1.00, 0, 200);
		Verkaufsposition v2 = new Verkaufsposition("Apfelsaft", 1.00, 0, 150);
		Verkaufsposition v3 = new Verkaufsposition("Turbohefe", 1.00, 10, 0);
		Verkaufsposition v4 = new Verkaufsposition("5L-Beutel", 1.00, 40, 0);
		vliste.add(v1);
		vliste.add(v2);
		vliste.add(v3);
		vliste.add(v4);
		Verkaufsposition v5 = new Verkaufsposition("Quittensaft", 1.00, 0, 200);
		Verkaufsposition v6 = new Verkaufsposition("Apfelsaft", 1.00, 0, 150);
		Verkaufsposition v7 = new Verkaufsposition("Turbohefe", 1.00, 10, 0);
		Verkaufsposition v8 = new Verkaufsposition("5L-Beutel", 1.00, 40, 0);
		vliste.add(v5);
		vliste.add(v6);
		vliste.add(v7);
		vliste.add(v8);
		Verkaufsposition v9 = new Verkaufsposition("Quittensaft", 1.00, 0, 200);
		Verkaufsposition v10 = new Verkaufsposition("Apfelsaft", 1.00, 0, 150);
		Verkaufsposition v11 = new Verkaufsposition("Turbohefe", 1.00, 10, 0);
		Verkaufsposition v12 = new Verkaufsposition("5L-Beutel", 1.00, 40, 0);
		vliste.add(v9);
		vliste.add(v10);
		vliste.add(v11);
		vliste.add(v12);

		VerkäufeFrame vFrame = new VerkäufeFrame(vliste);
	}
}
