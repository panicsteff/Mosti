package guischicht;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import produkte.Produkt;

public class testrahmen extends JFrame {

			private ProduktTableModel produktTableModel;
			private DienstleistungenTableModel dienstTableModel;

			public testrahmen(ArrayList<Produkt> produktsortiment) {
				
				setTitle("Abrechnung für Herrn Raptis");
				setSize(500, 500);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(new GridLayout(4,1));
				
				produktTableModel = new ProduktTableModel(produktsortiment);
				JTable table = new JTable(produktTableModel);
				
				dienstTableModel = new DienstleistungenTableModel(produktsortiment);
				JTable table1 = new JTable(dienstTableModel);
				
				JScrollPane tableContainer1 = new JScrollPane(table1);
				JScrollPane tableContainer = new JScrollPane(table);
				
				contentPanel.add(new JLabel("Literzahlen"));
				contentPanel.add(tableContainer1);
				contentPanel.add(new JLabel("Materialien"));
				contentPanel.add(tableContainer);
				
				getContentPane().add(contentPanel);
				

				

				
				
				JMenuBar menubar = new JMenuBar();
				setJMenuBar(menubar);
			
				JMenu menu = new JMenu ("Datei"); 
				JMenuItem mi = new JMenuItem ("Kunde bearbeiten");
				
				menubar.add(menu);
				menu.add(mi);

//				
//				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//				JScrollPane scrollpane = new JScrollPane(contentPanel);
//				JPanel titlepane = new JPanel();
//				titlepane.setBorder(BorderFactory.createTitledBorder(
//						BorderFactory.createEtchedBorder(), "Einkäufe"));
//				titlepane.setLayout(new BorderLayout());
//				titlepane.add(scrollpane);
				//add(titlepane);

				setVisible(true);
				
				//new KundeBearbeitenDialog (this, kundeTableModel.getKunde(0));
			}
			
			public static void main(String[]args){
				ArrayList<Produkt> p = new ArrayList<Produkt>();
				Produkt p2 = new Produkt ("5L Box", 1.00, 10, 200);
				Produkt p1 = new Produkt ("3L Box", 0.50, 100, 300);
				Produkt p3 = new Produkt ("10L Box", 2.00, 100, 200);
				p.add(p1);
				p.add(p2);
				p.add(p3);
				testrahmen a = new testrahmen(p);
			}
			


		
		

	

}
