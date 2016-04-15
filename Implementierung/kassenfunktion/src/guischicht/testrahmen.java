package guischicht;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import produkte.Produkt;

public class testrahmen extends JFrame {

			private ZusatzProduktTableModel zusatzTableModel;
			private AbfüllMaterialTableModel abfüllTableModel;
			private DienstleistungenTableModel dienstTableModel;
			private JLabel label;

			public testrahmen(ArrayList<Produkt> abfüllProduktSortiment, ArrayList<Produkt> zusatzProduktSortiment) {
				
				setTitle("Abrechnung für <Kundenname>");
				setSize(700, 400);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(new GridLayout(8,1));
				
				dienstTableModel = new DienstleistungenTableModel(abfüllProduktSortiment);
				JTable dienstTable = new JTable(dienstTableModel);
				
				abfüllTableModel = new AbfüllMaterialTableModel(abfüllProduktSortiment);
				JTable abfüllTable = new JTable(abfüllTableModel);
				
				zusatzTableModel = new ZusatzProduktTableModel(zusatzProduktSortiment);
				JTable zusatzTable = new JTable(zusatzTableModel);
				
				JScrollPane tableContainer1 = new JScrollPane(dienstTable);
				JScrollPane tableContainer2 = new JScrollPane(abfüllTable);
				JScrollPane tableContainer3 = new JScrollPane(zusatzTable);
				
				label = new JLabel("Literzahlen");
				label.setFont(label.getFont().deriveFont(18f));
				label.setVerticalAlignment(JLabel.BOTTOM);
				contentPanel.add(label);
				contentPanel.add(tableContainer1);
				
				label = new JLabel("Abfüll-Materialien");
				label.setFont(label.getFont().deriveFont(18f));
				label.setVerticalAlignment(JLabel.BOTTOM);
				contentPanel.add(label);
				contentPanel.add(tableContainer2);
				
				label = new JLabel("Weitere Produkte");
				label.setFont(label.getFont().deriveFont(18f));
				label.setVerticalAlignment(JLabel.BOTTOM);
				contentPanel.add(label);
				contentPanel.add(tableContainer3);
				
				JPanel summePanel = new JPanel();
				summePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				summePanel.add(new JLabel("Gesamtsumme in Euro: "));
				summePanel.add(new JTextField("28,55"));
				contentPanel.add(summePanel);
				
				JPanel buttonPanel = new JPanel();
				JButton abschlussButton = new JButton("Einkauf abschließen");
				JButton abbrechButton = new JButton("Abbrechen");
				buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(abbrechButton);
				buttonPanel.add(abschlussButton);
				contentPanel.add(buttonPanel);
				
				//getContentPane().add(contentPanel);
				
				JPanel titlepane = new JPanel();
				titlepane.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(), "Einkäufe"));
				titlepane.setLayout(new BorderLayout());
				titlepane.add(contentPanel);
				add(titlepane);
			
				setVisible(true);
				
			}
			
			public static void main(String[]args){
				ArrayList<Produkt> p = new ArrayList<Produkt>();
				Produkt p1 = new Produkt ("3L-Beutel", 1.00, 10, 200);
				Produkt p2 = new Produkt ("5L-Beutel", 1.50, 100, 300);
				Produkt p3 = new Produkt ("10L-Beutel", 2.00, 100, 200);
				Produkt p4 = new Produkt ("3L Beutel+Box", 2.00, 100, 200);
				Produkt p5 = new Produkt ("5L Beutel+Box", 2.50, 100, 200);
				Produkt p6 = new Produkt ("10L Beutel+Box", 3.00, 100, 200);
				p.add(p1);
				p.add(p2);
				p.add(p3);
				p.add(p4);
				p.add(p5);
				p.add(p6);
				
				ArrayList<Produkt> z = new ArrayList<Produkt>();
				Produkt z1 = new Produkt ("Saftbox-Ständer", 12.00, 10, 200);
				Produkt z2 = new Produkt ("Hefe - untergärig", 1.50, 100, 300);
				Produkt z3 = new Produkt ("Hefe - obergärig", 2.00, 100, 200);
				z.add(z1);
				z.add(z2);
				z.add(z3);
				
				testrahmen a = new testrahmen(p,z);
			}
			


		
		

	

}
