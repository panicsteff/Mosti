package lagerverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import kundenverwaltung.NullableFormatter;

import com.sun.glass.ui.Pixels.Format;

import dienstleistungProdukt.Produkt;

class ProduktHinzufuegenDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JFormattedTextField txtPreis;
	private JFormattedTextField txtMenge;
	private JFormattedTextField txtUntergrenze;
	private JCheckBox cBoxIsAbfuellmaterial;
	private Produkt produkt;
	private List<Produkt> liste;
	private List<Produkt> aliste;
	private List<Produkt> zliste;

	ProduktHinzufuegenDialog(JFrame parent, List<Produkt> a_auflistung, List<Produkt> z_auflistung) {

		super(parent);

		//this.liste = auflistung;
		this.aliste = a_auflistung;
		this.zliste = z_auflistung;
				
		setTitle("Neues Produkt erstellen");
		setSize(300, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);

		setLayout(new GridLayout(6, 2));

		add(new JLabel("Name:"));
		add(txtName = new JTextField());

		add(new JLabel("Einzelpreis [€]:"));
		NumberFormatter nuf = new NumberFormatter(FoFormat.pf);
		NullableFormatter nf = new NullableFormatter(nuf);
		add(txtPreis = new JFormattedTextField(nf));

		add(new JLabel("Menge:"));
		add(txtMenge = new JFormattedTextField());

		add(new JLabel("Untergrenze:"));
		add(txtUntergrenze = new JFormattedTextField());
		
		add(new JLabel("Ist Abfüllmaterial:"));
		add(cBoxIsAbfuellmaterial = new JCheckBox());

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new MyOKHandler());
		add(okButton);

		JButton abbButton = new JButton("Abbrechen");
		abbButton.addActionListener(new MyAbbHandler());
		add(abbButton);

		setVisible(true);
	}

	private class MyOKHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			produkt = new Produkt(txtName.getText(), Double.parseDouble(txtPreis.getText()), Integer.parseInt(txtMenge.getText()), 
					Integer.parseInt(txtUntergrenze.getText()), cBoxIsAbfuellmaterial.isSelected(), 0);
			
			if(produkt.isAbfüllmaterial()== true)
				aliste.add(produkt);
			else
				zliste.add(produkt);
			dispose();

		}

	}

	private class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();

		}

	}

}
