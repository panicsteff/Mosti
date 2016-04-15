package guischicht;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JTextField;

import produkte.Produkt;

public class Abrechnungsrahmen extends JFrame {
	
		//private ListSelectionModel kundeSelectionModel;
		private ZusatzProduktTableModel abrechnungTableModel;

		public Abrechnungsrahmen(ArrayList<Produkt> produktsortiment) {
			
			setTitle("Abrechnung für Herrn Raptis");
			setSize(500, 500);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			JPanel contentPanel = new JPanel();
			//contentPanel.setLayout(new BorderLayout());
			contentPanel.setLayout(new GridLayout(4,1));
			//contentPanel.setLayout(new BorderLayout());
			
			abrechnungTableModel = new ZusatzProduktTableModel(produktsortiment);
			JTable table = new JTable(abrechnungTableModel);
			
			
//			JPanel einzelFeldPanel = new JPanel();
//			einzelFeldPanel.setLayout(new GridLayout(2,1));
//			einzelFeldPanel.add(new JLabel("Literzahl"));
//			einzelFeldPanel.add(new JLabel("Literzahl"));
//			
//			contentPanel.add(einzelFeldPanel);
//			
			contentPanel.add(table);
			contentPanel.add(new JLabel("Literzahl"));
			contentPanel.add(new JLabel("Literzahl"));
			contentPanel.add(new JLabel("Literzahl"));
			

			
			
//			contentPanel.add(new JTable(abrechnungTableModel));
			
			
			JMenuBar menubar = new JMenuBar();
			setJMenuBar(menubar);
		
			JMenu menu = new JMenu ("Datei"); 
			JMenuItem mi = new JMenuItem ("Kunde bearbeiten");
			
			menubar.add(menu);
			menu.add(mi);
//			
//			mi.addActionListener(new ActionListener(){
//				public void actionPerformed (ActionEvent e){
//					editKunde();
//				}
//			});
//			
//			mi.setEnabled(false);
			
//			menu.add(new JSeparator());
//			
//			JMenuItem mi2 = new JMenuItem ("Beenden");
//			menu.add(mi2);
//			
//			mi2.addActionListener(new ActionListener(){
//				public void actionPerformed (ActionEvent e){
//					dispose();
//				}
//			});
//			
			
//			abrechnungTableModel = new AbrechnungTableModel(produktsortiment);
//			JTable table = new JTable(abrechnungTableModel);
			
//			contentPanel.add(new JTable(abrechnungTableModel));
//			contentPanel.add(new JTable(abrechnungTableModel));
//			contentPanel.add(new JTable(abrechnungTableModel));
//			contentPanel.add(new JTable(abrechnungTableModel));
			
			
			
//			kundeSelectionModel= table.getSelectionModel();
//			kundeSelectionModel.setSelectionMode(
//					ListSelectionModel.SINGLE_SELECTION);
//			kundeSelectionModel.addListSelectionListener(new ListSelectionListener(){
//				public void valueChanged(ListSelectionEvent e){
//			         //JOptionPane.showMessageDialog(null,"Zeile "+table.getSelectedRow()+" angeklickt", "Info", JOptionPane.INFORMATION_MESSAGE);
//			         mi.setEnabled(true);
//				}
//			});
			
			//kundeSelectionModel = table.getSelectionModel();
			/*kundeSelectionModel.setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			kundeSelectionModel.addListSelectionListener(new ListSelectionListener(){

				public void valueChanged(ListSelectionEvent listselectionevent) {
					{
						mi.setEnabled(true);
					}
				}
			});*/
			
//			table.addMouseListener(new MouseAdapter(){
//				public void mousePressed(MouseEvent event){
//					if (event.getClickCount() == 2)
//						editKunde();
//				}
//			});
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane scrollpane = new JScrollPane(contentPanel);
			JPanel titlepane = new JPanel();
			titlepane.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), "Einkäufe"));
			titlepane.setLayout(new BorderLayout());
			titlepane.add(scrollpane);
			
			
			//titlepane.add(contentPanel);
			add(titlepane);
			
			setVisible(true);
			
			//new KundeBearbeitenDialog (this, kundeTableModel.getKunde(0));
		}
		
		public static void main(String[]args){
			ArrayList<Produkt> p = new ArrayList<Produkt>();
			Produkt p1 = new Produkt ("5L Box", 1.00, 10, 200);
			Produkt p2 = new Produkt ("10L Box", 2.00, 100, 200);
			p.add(p1);
			p.add(p2);
			Abrechnungsrahmen a = new Abrechnungsrahmen(p);
		}
		


	
	

}
