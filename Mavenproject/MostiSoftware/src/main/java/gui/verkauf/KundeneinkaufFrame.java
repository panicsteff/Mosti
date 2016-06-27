package gui.verkauf;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import javax.swing.RepaintManager;
import javax.swing.table.TableColumn;

import logik.mitarbeiterverwaltung.Formats;
import logik.produktverwaltung.PreisCellRenderer;
import logik.verkaufsverwaltung.Verkauf;
import logik.verkaufsverwaltung.Verkaufsposition;

@SuppressWarnings("serial")
public class KundeneinkaufFrame extends JFrame implements Printable {

	private ArrayList<Verkaufsposition> einkäufe;
	private KundeneinkaufTableModel kTableModel;
	private JLabel label;
	private Verkauf verkauf;

	public KundeneinkaufFrame(Verkauf verkauf) {
		this.verkauf = verkauf;
		einkäufe = verkauf.getVerkäufeListe();

		setTitle("Verkäufe");
		setSize(685, 850);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu datei = new JMenu("Kundenabrechnung");
		menubar.add(datei);
		JMenuItem drucken = new JMenuItem("Drucken");
		datei.add(drucken);
		drucken.addActionListener(new MyPrintHandler());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		
		label = new JLabel();
		label.setFont(label.getFont().deriveFont(20f));
		panel.add(label);
		label = new JLabel("Kostenzusammenstellung für Kunde " + verkauf.getKunde().getNachname() +
				" am " + Formats.DATE_FORMAT.format(verkauf.getVerkaufsDatum()));
		label.setFont(label.getFont().deriveFont(20f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label);
		label = new JLabel();
		label.setFont(label.getFont().deriveFont(20f));
		panel.add(label);
		

		kTableModel = new KundeneinkaufTableModel(einkäufe);
		JTable vTabelle = new JTable(kTableModel);
		//vTabelle.setShowGrid(false);
		vTabelle.setShowHorizontalLines(false);
		vTabelle.setShowVerticalLines(false);
		vTabelle.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		vTabelle.setFont(new Font("Arial", Font.BOLD, 15));
		vTabelle.setRowHeight(25); 


		TableColumn preisspalte = vTabelle.getColumnModel().getColumn(1);
		preisspalte.setCellRenderer(new PreisCellRenderer());
		TableColumn zwischenspalte = vTabelle.getColumnModel().getColumn(3);
		zwischenspalte.setCellRenderer(new PreisCellRenderer());

		vTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		vTabelle.getColumnModel().getColumn(0).setPreferredWidth(230);
		vTabelle.getColumnModel().getColumn(1).setPreferredWidth(200);
		vTabelle.getColumnModel().getColumn(2).setPreferredWidth(80);
		vTabelle.getColumnModel().getColumn(3).setPreferredWidth(150);

		JScrollPane scrollpane = new JScrollPane(vTabelle);
		JPanel titlepanel = new JPanel();
		titlepanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), ""));
		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(panel, BorderLayout.NORTH);
		titlepanel.add(scrollpane, BorderLayout.CENTER);

		JPanel summenPanel = new JPanel();
		summenPanel.setLayout(new GridLayout(2, 3));
		label = new JLabel();
		summenPanel.add(label);
		label = new JLabel("Kostensumme gesamt: ");
		label.setFont(label.getFont().deriveFont(15f));
		label.setHorizontalAlignment(JLabel.RIGHT);
		summenPanel.add(label);
		label = new JLabel();
		label.setText(String.valueOf(Math.round(verkauf.getSumme()* 100.0) / 100.0) + " €  ");
		label.setFont(label.getFont().deriveFont(15f));
		label.setHorizontalAlignment(JLabel.RIGHT);
		summenPanel.add(label);
		label = new JLabel();
		summenPanel.add(label);
		label = new JLabel("Literzahl gesamt: ");
		label.setFont(label.getFont().deriveFont(15f));
		label.setHorizontalAlignment(JLabel.RIGHT);
		summenPanel.add(label);
		label = new JLabel();
		label.setText(String.valueOf(verkauf.getLiterzahl()) + " L  ");
		label.setFont(label.getFont().deriveFont(15f));
		label.setHorizontalAlignment(JLabel.RIGHT);
		summenPanel.add(label);
		
		titlepanel.add(summenPanel, BorderLayout.SOUTH);

//		panel.add(titlepane, BorderLayout.NORTH);
//		panel.add(summenPanel, BorderLayout.CENTER);
		add(titlepanel);
		setVisible(true);

	}
	
	private class MyPrintHandler implements ActionListener{

			public void actionPerformed(ActionEvent evt) {
					PrinterJob printJob = PrinterJob.getPrinterJob();
					Paper paper = new Paper();
				      paper.setSize(210.0,297.0);
				      paper.setImageableArea(5.0,5.0,190.0,280.0);
				      PageFormat pf = new PageFormat();
				      pf.setPaper(paper);
					printJob.setPrintable(KundeneinkaufFrame.this, pf);
					if (printJob.printDialog())
						try {
							printJob.print();
						} catch (PrinterException pe) {
							System.out.println("Error printing: " + pe);
						}
				}
			}

	public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
		if (pageIndex > 0) {
			return (NO_SUCH_PAGE);
		} else {
			Dimension dim = KundeneinkaufFrame.this.getSize();
		    double cHeight = dim.getHeight();
		    double cWidth = dim.getWidth();
		    System.out.println("dimensionen: " + cHeight + " " + cWidth);
		    
			int x = (int) pageFormat.getImageableX() + 1;
			int y = (int) pageFormat.getImageableY() + 1;
			
			double pHeight = pageFormat.getImageableHeight();
			double pWidth = pageFormat.getImageableWidth();
			System.out.println("page format height: " + pHeight + " " + pWidth);
			double xRatio = pWidth / cWidth;
			double yRatio = pHeight / cHeight;
		    
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(x, y);
			g2.scale(xRatio, xRatio);
			
			System.out.println(x+" "+y);
			RepaintManager currentManager = RepaintManager.currentManager(this);
			currentManager.setDoubleBufferingEnabled(false);
			this.paint(g2);
			currentManager.setDoubleBufferingEnabled(true);
			return (PAGE_EXISTS);
		}
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
//
//		//KundeneinkaufFrame vFrame = new KundeneinkaufFrame(vliste, "biber");
//	}
}
