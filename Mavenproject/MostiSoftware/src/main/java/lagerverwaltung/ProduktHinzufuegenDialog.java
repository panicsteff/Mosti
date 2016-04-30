package lagerverwaltung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import kundenverwaltung.NullableFormatter;
import com.sun.glass.ui.Pixels.Format;
import dienstleistungProdukt.Produkt;

public class ProduktHinzufuegenDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JFormattedTextField txtPreis;
	private JFormattedTextField txtMenge;
	private JFormattedTextField txtUntergrenze;
	private Produkt produkt;
	private List<Produkt> liste;
	private Format format;

	public ProduktHinzufuegenDialog(JFrame parent, List<Produkt> auflistung) {

		super(parent);

		this.liste = auflistung;

		setTitle("Neues Produkt erstellen");
		setSize(300, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);

		setLayout(new GridLayout(5, 2));

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

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new MyOKHandler());
		add(okButton);

		JButton abbButton = new JButton("Abbrechen");
		abbButton.addActionListener(new MyAbbHandler());
		add(abbButton);

		setVisible(true);
	}

	class MyOKHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			produkt = new Produkt();
			produkt.setName(txtName.getText());
			produkt.setPreis(Double.parseDouble(txtPreis.getText()));
			produkt.setMenge(Integer.parseInt(txtMenge.getText()));
			produkt.setUntergrenze(Integer.parseInt(txtUntergrenze.getText()));

			liste.add(produkt);
			dispose();

		}

	}

	class MyAbbHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();

		}

	}

}
