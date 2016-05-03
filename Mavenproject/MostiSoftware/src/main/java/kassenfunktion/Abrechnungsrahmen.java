package kassenfunktion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import trester.Tresterabrechnung;
import main.Kundeneink�ufe;
import dienstleistungProdukt.Dienstleistung;
import dienstleistungProdukt.Einkauf;
import dienstleistungProdukt.Einkaufsposition;
import dienstleistungProdukt.Produkt;

public class Abrechnungsrahmen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZusatzProduktTableModel zusatzTableModel;
	private Abf�llMaterialTableModel abf�llTableModel;
	private DienstleistungenTableModel dienstTableModel;
	private JLabel label;
	private JTable zusatzTable;
	private double total;
	private JTextField totalText;
	private ArrayList<Produkt> aliste;
	private ArrayList<Produkt> zliste;
	private ArrayList<Dienstleistung> dienstleistungen;
	private Einkauf einkauf;
	private Kundeneink�ufe kundeneink�ufe;

	public Abrechnungsrahmen(ArrayList<Dienstleistung> dienstleistungen, ArrayList<Produkt> abf�llProduktSortiment,
			ArrayList<Produkt> zusatzProduktSortiment, Kundeneink�ufe kundeneink�ufe) {

		this.aliste = abf�llProduktSortiment;
		this.zliste = zusatzProduktSortiment;
		this.dienstleistungen = dienstleistungen;
		this.kundeneink�ufe = kundeneink�ufe;
		
		initVerkaufsmengen();

		setTitle("Abrechnung f�r <Kundenname>");
		setSize(700, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(8, 1));

		dienstTableModel = new DienstleistungenTableModel(dienstleistungen);
		JTable dienstTable = new JTable(dienstTableModel);

		abf�llTableModel = new Abf�llMaterialTableModel(abf�llProduktSortiment);
		JTable abf�llTable = new JTable(abf�llTableModel);

		zusatzTableModel = new ZusatzProduktTableModel(zusatzProduktSortiment);
		zusatzTable = new JTable(zusatzTableModel);
		// zusatzTable.getColumnModel().getColumn(0).setCellRenderer(new
		// AnzahlTableCellRenderer());
		// zusatzTable.getColumnModel().getColumn(2).setCellRenderer(new
		// AnzahlTableCellRenderer());

		JScrollPane tableContainer1 = new JScrollPane(dienstTable);
		JScrollPane tableContainer2 = new JScrollPane(abf�llTable);
		JScrollPane tableContainer3 = new JScrollPane(zusatzTable);

		label = new JLabel("Literzahlen");
		label.setFont(label.getFont().deriveFont(18f));
		label.setVerticalAlignment(JLabel.BOTTOM);
		contentPanel.add(label);
		contentPanel.add(tableContainer1);

		label = new JLabel("Abf�ll-Materialien");
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

		double sum1 = 0;
//		for (int i = 0; i < Dienstleistung.listeDienstleistungen.length; i++) {
//			sum1 = sum1
//					+ ((Integer) (dienstTableModel.getValueAt(0, i)) * Dienstleistung.listeDienstleistungen[i]
//							.getPreisProLiter());
//			System.out.println((Integer) (dienstTableModel.getValueAt(0, i))
//					+ " und "
//					+ Dienstleistung.listeDienstleistungen[i]
//							.getPreisProLiter());
//		}
		sum1 = dienstTableModel.berechneTeilpreis();									//
		System.out.println();
		double sum2 = 0;
//		for (int i = 0; i < abf�llProduktSortiment.size(); i++) {
//			sum2 = sum2
//					+ ((Integer) (abf�llTableModel.getValueAt(0, i)) * abf�llProduktSortiment
//							.get(i).getPreis());
//			System.out.println((Integer) (abf�llTableModel.getValueAt(0, i))
//					+ " und " + abf�llProduktSortiment.get(i).getPreis());
//
//		}
		sum2 = abf�llTableModel.berechneTeilpreis();									//
		System.out.println();
		double sum3 = 0;
//		for (int i = 0; i < zusatzProduktSortiment.size(); i++) {
//			sum3 = sum3
//					+ ((Integer) (zusatzTableModel.getValueAt(0, i)) * zusatzProduktSortiment
//							.get(i).getPreis());
//			System.out.println((Integer) (zusatzTableModel.getValueAt(0, i))
//					+ " und " + zusatzProduktSortiment.get(i).getPreis());
//		}
		sum3 = zusatzTableModel.berechneTeilpreis();									//
		total = sum1 + sum2 + sum3;

		totalText = new JTextField(13);
		totalText.setHorizontalAlignment(JTextField.RIGHT);
		totalText.setEditable(false);
		totalText.setText(String.valueOf(total));

		summePanel.add(totalText);
		contentPanel.add(summePanel);

		JPanel buttonPanel = new JPanel();
		JButton abschlussButton = new JButton("Einkauf abschlie�en");
		JButton aktualisiereSummeButton = new JButton(
				"Kostensumme aktualisieren");
		JButton abbrechButton = new JButton("Abbrechen");
		abbrechButton.addActionListener(new AbbruchHandler());
		aktualisiereSummeButton
				.addActionListener(new AktualisiereSummeHandler());
		abschlussButton.addActionListener(new EinkaufAbschlie�enHandler());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(abbrechButton);
		buttonPanel.add(aktualisiereSummeButton);
		buttonPanel.add(abschlussButton);
		contentPanel.add(buttonPanel);

		// getContentPane().add(contentPanel);

		JPanel titlepane = new JPanel();
		titlepane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Eink�ufe"));
		titlepane.setLayout(new BorderLayout());
		titlepane.add(contentPanel);
		add(titlepane);

		setVisible(true);

	}

	class AbbruchHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//initVerkaufsmengen();
			dispose();
		}
	}

	class AktualisiereSummeHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			berechneGesamtTotal();
			//System.out.println(total);
			totalText.setText(String.valueOf(total));
		}
	}
	
	public void initVerkaufsmengen(){
		for(Dienstleistung d: dienstleistungen){
			if(d.getVerkaufsMenge()!= 0)
				d.setVerkaufsMenge(0);
		}
		
		for(Produkt p: aliste){
			if(p.getVerkaufsMenge()!= 0)
				p.setVerkaufsMenge(0);
		}
		
		for(Produkt p: zliste){
			if(p.getVerkaufsMenge()!= 0)
				p.setVerkaufsMenge(0);
		}
	}
	
	
	public void berechneGesamtTotal(){
		total = dienstTableModel.berechneTeilpreis()+									//
				  abf�llTableModel.berechneTeilpreis()+									//
				  zusatzTableModel.berechneTeilpreis();									//
	}

	class EinkaufAbschlie�enHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			einkauf = new Einkauf();
			int literzahl=0;
			
			for(Dienstleistung d: dienstleistungen){
				if(d.getVerkaufsMenge()> 0){
					einkauf.addEinkauf(d);
					literzahl = literzahl + d.getVerkaufsMenge();
				}
			}
			
			for(Produkt p: aliste){
				if(p.getVerkaufsMenge()> 0)
					einkauf.addEinkauf(p);
			}
			
			for(Produkt p: zliste){
				if(p.getVerkaufsMenge()> 0)
					einkauf.addEinkauf(p);
			}

//			for (int i = 0; i < dienstleistungen.size(); i++) {
//				e = new Einkaufsposition(
//						dienstleistungen.get(i).getName(),
//						dienstleistungen.get(i)
//								.getVerkaufsMenge());
//				dienstleistungen.get(i).setVerkaufsMenge(0);
//			}
//			for (int i = 0; i < aliste.size(); i++) {
//				e = new Einkaufsposition1(aliste.get(i)
//						.getName(), aliste.get(i)
//						.getVerkaufsMenge());
//				aliste.get(i).setVerkaufsMenge(0);
//				einkauf.addEinkauf(e);
//			}
//			for (int i = 0; i < zliste.size(); i++) {
//				e = new Einkaufsposition1(zliste.get(i)
//						.getName(), zliste.get(i)
//						.getVerkaufsMenge());
//				zliste.get(i).setVerkaufsMenge(0);
//				einkauf.addEinkauf(e);
//			}

			berechneGesamtTotal();
			einkauf.setSumme(total);
			einkauf.setLiterzahl(literzahl);
			kundeneink�ufe.addEinkauf(einkauf);
			System.out.println("Einkauf abgeschlossen");
			kundeneink�ufe.printKundeneink�ufe();
			Tresterabrechnung tA = new Tresterabrechnung(kundeneink�ufe);
			tA.printTresterAbrechnung();
			//initVerkaufsmengen();
			
			dispose();

		}
		
		

	}

}