package gui.produktverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import logik.kundenverwaltung.NullableFormatter;
import logik.produktverwaltung.FoFormat;
import logik.produktverwaltung.Produkt;
import logik.produktverwaltung.ProduktSortiment;

@SuppressWarnings("serial")
class ProduktHinzufuegenFrame extends JDialog {

	private JTextField txtName;
	private JFormattedTextField txtPreis;
	private JFormattedTextField txtMenge;
	private JFormattedTextField txtUntergrenze;
	private JCheckBox cBoxIsAbfuellmaterial;
	private Produkt produkt;
//	private List<Produkt> aliste;
//	private List<Produkt> zliste;
	private List<Produkt> pliste;
	private ProduktSortiment pSortiment;

	//ProduktHinzufuegenFrame(JFrame parent, List<Produkt> a_auflistung, List<Produkt> z_auflistung) {
	//ProduktHinzufuegenFrame(JFrame parent, List<Produkt> pliste) {
		ProduktHinzufuegenFrame(JFrame parent, ProduktSortiment p) {

		super(parent);
		this.pSortiment = p;
		
//		this.aliste = a_auflistung;
//		this.zliste = z_auflistung;
		//this.pliste = pliste;
				
		setTitle("Neues Produkt erstellen");
		setSize(400, 300);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);

		setLayout(new GridLayout(6, 2));

		JLabel label;
		
		add(label = new JLabel("Name:"));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtName = new JTextField());
		txtName.setFont(txtName.getFont().deriveFont(16f));

		add(label = new JLabel("Einzelpreis [€]:"));
		label.setFont(label.getFont().deriveFont(16f));
//		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
//		NullableFormatter nf = new NullableFormatter(nuf);
//		add(txtPreis = new JFormattedTextField(nf));
		//txtPreis =new JFormattedTextField(FoFormat.preisformat);
		txtPreis =new JFormattedTextField(new DecimalFormat("0.00"));
		txtPreis.setFont(txtPreis.getFont().deriveFont(16f));
		add(txtPreis);

		add(label = new JLabel("Vorrätige Menge:"));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtMenge = new JFormattedTextField());
		txtMenge.setFont(txtMenge.getFont().deriveFont(16f));

		add(label = new JLabel("Untergrenze:"));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtUntergrenze = new JFormattedTextField());
		txtUntergrenze.setFont(txtUntergrenze.getFont().deriveFont(16f));
		
		add(label = new JLabel("Ist Abfüllmaterial:"));
		label.setFont(label.getFont().deriveFont(16f));
		add(cBoxIsAbfuellmaterial = new JCheckBox());
		cBoxIsAbfuellmaterial.setFont(cBoxIsAbfuellmaterial.getFont().deriveFont(16f));

		JButton okButton = new JButton("OK");
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.addActionListener(new MyOKHandler());
		add(okButton);

		JButton abbButton = new JButton("Abbrechen");
		abbButton.setFont(abbButton.getFont().deriveFont(16f));
		abbButton.addActionListener(new MyAbbHandler());
		add(abbButton);

		setVisible(true);
	}

	private class MyOKHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			try{
				Object preis_objekt = txtPreis.getValue();
				Double preis = Double.parseDouble(preis_objekt+"");
			produkt = new Produkt(txtName.getText(), preis, Integer.parseInt(txtMenge.getText()), 
					Integer.parseInt(txtUntergrenze.getText()), cBoxIsAbfuellmaterial.isSelected(), 0);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,
						"Bitte überprüfen Sie die Eingaben.", "Meldung",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				return;
				
			}
			//pliste.add(produkt);
			pSortiment.addProdukt(produkt);
			
//			if(produkt.isAbfüllmaterial()== true)
//				aliste.add(produkt);
//			else
//				zliste.add(produkt);
			
			dispose();

		}


	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();

		}

	}

}