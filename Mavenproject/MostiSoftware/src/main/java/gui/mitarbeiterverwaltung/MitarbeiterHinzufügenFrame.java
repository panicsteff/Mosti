package gui.mitarbeiterverwaltung;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import logik.kundenverwaltung.Formats;
import logik.kundenverwaltung.NullableFormatter;
import logik.mitarbeiterverwaltung.Mitarbeiter;

import org.apache.commons.codec.binary.Base64;

import persistenz.MitarbeiterDB;

public class MitarbeiterHinzuf�genFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtNachname;
	private JTextField txtVorname;
	private JTextField txtStrasse;
	private JTextField txtHausnummer;
	private JTextField txtPlz;
	private JTextField txtStadt;
	private JTextField txtTelefonnummer;
	private JTextField txtBenutzername;
	private Mitarbeiter mitarbeiter;
	private ArrayList<Mitarbeiter> liste;
	private MitarbeiterDB mdb = new MitarbeiterDB();

	MitarbeiterHinzuf�genFrame(JFrame parent, ArrayList<Mitarbeiter> auflistung) {
		super(parent);

		liste = auflistung;
		setModal(true);

		setTitle("Neuen Mitarbeiter hinzuf�gen");
		setSize(350, 250);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(10, 2));

		JLabel label;

		add(label = new JLabel("Nachname: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtNachname = new JTextField());
		txtNachname.setFont(txtNachname.getFont().deriveFont(16f));

		add(label = new JLabel("Vorname: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtVorname = new JTextField());
		txtVorname.setFont(txtVorname.getFont().deriveFont(16f));

		add(label = new JLabel("Stra�e: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtStrasse = new JTextField());
		txtStrasse.setFont(txtStrasse.getFont().deriveFont(16f));

		add(label = new JLabel("Hausnummer: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtHausnummer = new JTextField());
		txtHausnummer.setFont(txtHausnummer.getFont().deriveFont(16f));

		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
		NullableFormatter nf = new NullableFormatter(df);

		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter("#####");
		} catch (ParseException e) {
			System.out.println(e);
		}

		NullableFormatter ff = new NullableFormatter(mf);
		add(label = new JLabel("PLZ: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtPlz = new JFormattedTextField(ff));
		txtPlz.setFont(txtPlz.getFont().deriveFont(16f));

		add(label = new JLabel("Stadt: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtStadt = new JTextField());
		txtStadt.setFont(txtStadt.getFont().deriveFont(16f));

		add(label = new JLabel("Telefonnummer: "));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtTelefonnummer = new JTextField());
		txtTelefonnummer.setFont(txtTelefonnummer.getFont().deriveFont(16f));

		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2, 1));
		add(jp);

		jp.add(label = new JLabel("Benutzername:"));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtBenutzername = new JTextField());
		txtBenutzername.setFont(txtBenutzername.getFont().deriveFont(16f));
		JLabel jl = new JLabel("(Bitte nur Kleinbuchstaben verwenden!)");
		jp.add(jl);
		jl.setFont(new Font("Dialog", Font.BOLD, 10));

		add(new JLabel());
		add(new JLabel());

		JButton okButton = new JButton("OK");
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.addActionListener(new MyOKHandler());
		add(okButton);

		JButton cmdButton = new JButton("Abbrechen");
		cmdButton.setFont(cmdButton.getFont().deriveFont(16f));
		cmdButton.addActionListener(new MyCancelHandler());
		add(cmdButton);

		pack();
		setVisible(true);
	}

	private class MyOKHandler implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			if (benutzernamePr�fen(txtBenutzername.getText()) == false) {
				JOptionPane
						.showMessageDialog(MitarbeiterHinzuf�genFrame.this,
								"Benutzername ist bereits vorhanden. Bitte w�hlen Se einen anderen");
				return;
			}

			// String passwort = new
			// String(Base64.encodeBase64(txtBenutzername.getText().getBytes()));
			mitarbeiter = new Mitarbeiter(txtNachname.getText(),
					txtVorname.getText(), txtStrasse.getText(),
					txtHausnummer.getText(), txtPlz.getText(),
					txtStadt.getText(), txtTelefonnummer.getText(), 0,
					txtBenutzername.getText().toLowerCase(), new String(
							Base64.encodeBase64(txtBenutzername.getText()
									.getBytes())));

			// mitarbeiter.setPasswort(mitarbeiter.getBenutzername());
			JOptionPane.showMessageDialog(MitarbeiterHinzuf�genFrame.this,
					"Benutzername: " + mitarbeiter.getBenutzername()
							+ "\n Passwort: " + mitarbeiter.getBenutzername());

			liste.add(mitarbeiter);
			mdb.mitarbeiterEinf�gen(mitarbeiter);

			dispose();
		}
	}

	private class MyCancelHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private boolean benutzernamePr�fen(String benutzername) {
		boolean frei = true;
		frei = mdb.benutzernamenSuchen(benutzername);
		return frei;
	}

}
