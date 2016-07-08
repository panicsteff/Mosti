package gui.mitarbeiterverwaltung;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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

import logik.kundenverwaltung.NullableFormatter;
import logik.mitarbeiterverwaltung.Formats;
import logik.mitarbeiterverwaltung.Mitarbeiter;
import persistenz.MitarbeiterDB;

public class MitarbeiterBearbeitenDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	class MyOkHandler implements ActionListener {
		private String beautify(String s) {
			if (s == null) {
				return null;
			}
			s = s.trim();
			if (s.trim().length() == 0) {
				return null;
			}
			return s;
		}

		public void actionPerformed(ActionEvent e) {
			if ( mitarbeiter.getBenutzername() != null && txtBenutzername.getText().equals("admin") == false
					&& mitarbeiter.getBenutzername().equals("admin")) {
				JOptionPane
						.showMessageDialog(
								MitarbeiterBearbeitenDialog.this,
								"Der Benutzername \"admin\" kann "
										+ "nicht geändert werden, da sonst eine Anmeldung als Administrator nicht mehr möglich ist");
				txtBenutzername.setText("admin");
				return;
			}

			if (benutzernamePrüfen(txtBenutzername.getText(),
					mitarbeiter.getBenutzername()) == false) {
				JOptionPane
						.showMessageDialog(MitarbeiterBearbeitenDialog.this,
								"Benutzername ist bereits vorhanden. Bitte wählen Sie einen anderen");
				return;
			}

			mitarbeiter.setNachname(beautify(txtNachname.getText()));
			mitarbeiter.setVorname(beautify(txtVorname.getText()));
			mitarbeiter.setStrasse(beautify(txtStrasse.getText()));
			mitarbeiter.setHausnummer(beautify(txtHausnummer.getText()));
			mitarbeiter.setPlz((String) txtPlz.getValue());
			mitarbeiter.setStadt(beautify(txtStadt.getText()));
			mitarbeiter.setTelefonnummer(beautify(txtTel.getText()));
			mitarbeiter.setBenutzername(beautify(txtBenutzername.getText()));
			dispose();

			
		}
	}

	class MyCancelHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private Mitarbeiter mitarbeiter;

	private JTextField txtNachname;
	private JTextField txtVorname;
	private JTextField txtStrasse;
	private JTextField txtHausnummer;
	private JFormattedTextField txtPlz;
	private JTextField txtStadt;
	private JTextField txtTel;
	private JTextField txtBenutzername;
	private MitarbeiterDB mdb;

	public MitarbeiterBearbeitenDialog(JFrame parent, Mitarbeiter mitarbeiter) {
		super(parent);
		this.mitarbeiter = mitarbeiter;
		mdb = new MitarbeiterDB();

		setModal(true);
		setTitle("Mitarbeiter bearbeiten");
		setSize(270, 410);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(10, 2));

		JLabel label;

		label = new JLabel("Nachname:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

		txtNachname = new JTextField(mitarbeiter.getNachname());
		txtNachname.setFont(txtNachname.getFont().deriveFont(16f));
		add(txtNachname);

		label = new JLabel("Vorname:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

		txtVorname = new JTextField(mitarbeiter.getVorname());
		txtVorname.setFont(txtVorname.getFont().deriveFont(16f));
		add(txtVorname);

		DateFormatter df = new DateFormatter(Formats.DATE_FORMAT);
		NullableFormatter nf = new NullableFormatter(df);

		label = new JLabel("Straße:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

		txtStrasse = new JTextField(mitarbeiter.getStrasse());
		txtStrasse.setFont(txtStrasse.getFont().deriveFont(16f));
		add(txtStrasse);

		label = new JLabel("Hausnummer:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

		txtHausnummer = new JTextField(mitarbeiter.getHausnummer());
		txtHausnummer.setFont(txtHausnummer.getFont().deriveFont(16f));
		add(txtHausnummer);

		label = new JLabel("Plz:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter("#####");
		} catch (ParseException e) {
			System.out.println(e);

		}
		nf = new NullableFormatter(mf);
		txtPlz = new JFormattedTextField(nf);
		txtPlz.setValue(mitarbeiter.getPlz());
		txtPlz.setFont(txtPlz.getFont().deriveFont(16f));
		add(txtPlz);

		label = new JLabel("Ort:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

		txtStadt = new JTextField(mitarbeiter.getStadt());
		txtStadt.setFont(txtStadt.getFont().deriveFont(16f));
		add(txtStadt);

		label = new JLabel("Telefonnummer:");
		label.setFont(label.getFont().deriveFont(16f));
		add(label);

		txtTel = new JTextField(mitarbeiter.getTelefonnummer());
		txtTel.setFont(txtTel.getFont().deriveFont(16f));
		add(txtTel);

		/*
		 * label = new JLabel("Benutzername:"); add(label);
		 * 
		 * txtBenutzername = new JTextField(mitarbeiter.getBenutzername());
		 * add(txtBenutzername);
		 */

		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2, 1));
		add(jp);

		jp.add(label = new JLabel("Benutzername:"));
		label.setFont(label.getFont().deriveFont(16f));
		add(txtBenutzername = new JTextField(mitarbeiter.getBenutzername()));
		txtBenutzername.setFont(txtBenutzername.getFont().deriveFont(16f));
		JLabel jl = new JLabel("(Bitte nur Kleinbuchstaben verwenden!)");
		jp.add(jl);
		jl.setFont(new Font("Dialog", Font.BOLD, 10));

		add(new JLabel());
		add(new JLabel());

		JButton okButton = new JButton("OK");
		okButton.setFont(okButton.getFont().deriveFont(16f));
		okButton.addActionListener(new MyOkHandler());
		add(okButton);

		JButton cmdButton = new JButton("Abbrechen");
		cmdButton.setFont(cmdButton.getFont().deriveFont(16f));
		cmdButton.addActionListener(new MyCancelHandler());
		add(cmdButton);

		/*
		 * JPanel pane = new JPanel(); pane.setLayout(new
		 * FlowLayout(FlowLayout.LEFT)); add(pane);
		 */

		/*
		 * JButton cmdok = new JButton("OK"); pane.add(cmdok); JButton cmdcancel
		 * = new JButton("Abbrechen"); pane.add(cmdcancel);
		 * 
		 * cmdok.addActionListener(new MyOkHandler());
		 * cmdcancel.addActionListener(new MyCancelHandler());
		 */

		pack();
		setVisible(true);
	}

	private boolean benutzernamePrüfen(String benutzername,
			String momentanerBenutzername) {
		if (benutzername.equals(momentanerBenutzername)) {
			return true;
		}
		boolean frei = true;
		frei = mdb.benutzernamenSuchen(benutzername);
		return frei;
	}

}
