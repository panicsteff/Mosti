package gui.terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import logik.kundenverwaltung.Formats;
import logik.kundenverwaltung.NullableFormatter;
import logik.terminplanung.TagFrameController;
import logik.terminplanung.Termin;
import logik.terminplanung.TerminHinzuf�genLogik;
import logik.terminplanung.TerminLogik;

public class TerminBearbeitenDialog extends JDialog {

	class MyVerschiebenHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (beginnzeit.equals(uhrzeittxt.getText()) == false) {
				String neueUhrzeit = uhrzeittxt.getText();
				int intUhrzeit = tfc.terminStringNachInt(neueUhrzeit);
				if (intUhrzeit == -1) {
					JOptionPane.showMessageDialog(TerminBearbeitenDialog.this,
							"Bitte gebe Sie eine g�ltige Uhrzeit ein");
					return;
				}
				if (intUhrzeit == -2) {
					JOptionPane.showMessageDialog(TerminBearbeitenDialog.this,
							"Das ist kein angebotener Termin-Slot");
					return;
				}
				if (intUhrzeit == -3) {
					JOptionPane.showMessageDialog(TerminBearbeitenDialog.this,
							"Termin lieg au�erhalb der Arbeitszeiten!");
					return;
				}
				neueMenge = termin.getMenge();
				neueDauer = dauer;
				boolean isDauerneu = false;
				if (((termin.getMenge() + "").equals(mengetxt.getText()) == false)
						|| (termin.isInFlaschen() == flaschenbox.isSelected() == false)) {
					try {
						neueMenge = Integer.parseInt(mengetxt.getText());
						neueDauer = thl.berechneTermindauer(mengetxt.getText(),
								flaschenbox.isSelected());
						isDauerneu = true;
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(
								TerminBearbeitenDialog.this,
								"Bitte gebe Sie eine g�ltige Obstmenge ein");
						return;
					}
				}

				boolean frei = tfc.neueTermindauerPr�fen(alleTermine, termin,
						tfc.berechneAnzahlZeitslots(neueDauer), intUhrzeit);
				if (frei == false) {
					JOptionPane
							.showMessageDialog(TerminBearbeitenDialog.this,
									"Diese Termine sind schon belegt. Verschiebung nicht m�glich");
					return;

				} else {
					TerminLogik.terminL�schen(termin);
					TerminLogik.termineSpeichern(termin.getKundenId(),
							tfc.berechneAnzahlZeitslots(neueDauer),
							termin.getDatum(), intUhrzeit, neueMenge,
							flaschenbox.isSelected());
					int kundenId = termin.getKundenId();
					int menge = termin.getMenge();
					boolean flasche = termin.isInFlaschen();
					for (Termin t : alleTermine) {
						if (t.getTerminId() == termin.getTerminId()) {
							t.setKundenId(0);
							t.setMenge(0);
							t.setInFlaschen(false);
						}
					}
					for (int i = 0; i < alleTermine.size(); i++) {
						if (alleTermine.get(i).getUhrzeit() == intUhrzeit) {
							for (int j = i; j < i
									+ tfc.berechneAnzahlZeitslots(neueDauer); j++) {
								alleTermine.get(j).setKundenId(kundenId);
								alleTermine.get(j).setMenge(menge);
								alleTermine.get(j).setInFlaschen(flasche);
								alleTermine.get(j).setAnzahlZeitslots(
										tfc.berechneAnzahlZeitslots(neueDauer));

							}
						}
					}
				}
				if (isDauerneu == true) {
					JOptionPane.showMessageDialog(TerminBearbeitenDialog.this,
							"Termin ge�ndert. Der neue Termin dauert "
									+ neueDauer + " Minuten");
					dauertxt.setText(neueDauer + " min");
				}
				JOptionPane.showMessageDialog(TerminBearbeitenDialog.this,
						"Termin ge�ndert");

			} else {
				if (((termin.getMenge() + "").equals(mengetxt.getText()) == false)
						|| (termin.isInFlaschen() == flaschenbox.isSelected() == false)) {
					try {
						neueMenge = Integer.parseInt(mengetxt.getText());
						neueDauer = thl.berechneTermindauer(mengetxt.getText(),
								flaschenbox.isSelected());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(
								TerminBearbeitenDialog.this,
								"Bitte gebe Sie eine g�ltige Obstmenge ein");
						return;
					}
					boolean frei = tfc.neueTermindauerPr�fen(alleTermine,
							termin, tfc.berechneAnzahlZeitslots(neueDauer),
							termin.getUhrzeit());
					if (frei == false) {
						JOptionPane
								.showMessageDialog(TerminBearbeitenDialog.this,
										"Diese Termine sind schon belegt. Verschiebung nicht m�glich");
						return;

					} else {
						TerminLogik.terminL�schen(termin);
						TerminLogik.termineSpeichern(termin.getKundenId(),
								tfc.berechneAnzahlZeitslots(neueDauer),
								termin.getDatum(), termin.getUhrzeit(),
								neueMenge, flaschenbox.isSelected());

						int kundenId = termin.getKundenId();
						int menge = termin.getMenge();
						boolean flasche = termin.isInFlaschen();
						for (Termin t : alleTermine) {
							if (t.getTerminId() == termin.getTerminId()) {
								t.setKundenId(0);
								t.setMenge(0);
								t.setInFlaschen(false);
							}
						}

						for (int i = 0; i < alleTermine.size(); i++) {
							if (alleTermine.get(i).getUhrzeit() == termin
									.getUhrzeit()) {
								for (int j = i; j < i
										+ tfc.berechneAnzahlZeitslots(neueDauer); j++) {
									alleTermine.get(j).setKundenId(kundenId);
									alleTermine.get(j).setMenge(menge);
									alleTermine.get(j).setInFlaschen(flasche);
									alleTermine
											.get(j)
											.setAnzahlZeitslots(
													tfc.berechneAnzahlZeitslots(neueDauer));
								}
							}
						}
						JOptionPane.showMessageDialog(
								TerminBearbeitenDialog.this,
								"Termin ge�ndert. Der neue Termin dauert "
										+ neueDauer + " Minuten");
						dauertxt.setText(neueDauer + " min");
					}
				} else {

					JOptionPane.showMessageDialog(TerminBearbeitenDialog.this,
							"Es haben sich keine Daten ge�ndert");
				}

			}

		}
	}

	private static final long serialVersionUID = 1L;
	private TagFrameController tfc;
	private int dauer;
	private Termin termin;
	private ArrayList<Termin> alleTermine;
	private String beginnzeit;
	private JTextField datumtxt;
	private JFormattedTextField uhrzeittxt;
	private JTextField mengetxt;
	private JTextField dauertxt;
	private JCheckBox flaschenbox;
	private TerminHinzuf�genLogik thl;

	private int neueDauer;
	private int neueMenge;

	public TerminBearbeitenDialog(Termin t, int l�nge, String name,
			String zeit, ArrayList<Termin> aTermine) {
		setSize(320, 400);
		setTitle("Termin bearbeiten");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setModal(true);

		tfc = new TagFrameController();
		termin = t;
		dauer = l�nge;
		alleTermine = aTermine;
		beginnzeit = zeit;
		neueMenge = t.getMenge();
		thl = new TerminHinzuf�genLogik();

		JLabel kunde = new JLabel("Kunde");
		kunde.setBounds(0, 0, 200, 40);
		kunde.setFont(kunde.getFont().deriveFont(16f));
		add(kunde);

		JTextField kundetxt = new JTextField(name);
		kundetxt.setBounds(100, 0, 200, 40);
		kundetxt.setFont(kundetxt.getFont().deriveFont(16f));
		kundetxt.setEditable(false);
		add(kundetxt);

		JLabel datum = new JLabel("Datum:");
		datum.setFont(datum.getFont().deriveFont(16f));
		datum.setBounds(0, 40, 200, 40);
		add(datum);

		datumtxt = new JTextField(Formats.DATE_FORMAT.format(t.getDatum()));
		datumtxt.setFont(datumtxt.getFont().deriveFont(16f));
		datumtxt.setBounds(100, 40, 200, 40);
		datumtxt.setEditable(false);
		add(datumtxt);

		JLabel uhrzeit = new JLabel("Uhrzeit:");
		uhrzeit.setFont(uhrzeit.getFont().deriveFont(16f));
		uhrzeit.setBounds(0, 80, 200, 40);
		add(uhrzeit);

		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		NullableFormatter nf = new NullableFormatter(mf);
		uhrzeittxt = new JFormattedTextField(nf);

		uhrzeittxt.setValue(zeit);
		uhrzeittxt.setFont(uhrzeittxt.getFont().deriveFont(16f));
		uhrzeittxt.setBounds(100, 80, 200, 40);
		add(uhrzeittxt);

		JLabel dauer = new JLabel("Dauer:");
		dauer.setFont(dauer.getFont().deriveFont(16f));
		dauer.setBounds(0, 120, 200, 40);
		add(dauer);

		dauertxt = new JTextField(l�nge + " min");
		dauertxt.setFont(dauertxt.getFont().deriveFont(16f));
		dauertxt.setBounds(100, 120, 200, 40);
		dauertxt.setEditable(false);
		add(dauertxt);

		JLabel mengelabel = new JLabel("Menge:");
		mengelabel.setFont(mengelabel.getFont().deriveFont(16f));
		mengelabel.setBounds(0, 160, 200, 40);
		add(mengelabel);

		mengetxt = new JTextField(t.getMenge() + "");
		mengetxt.setFont(mengetxt.getFont().deriveFont(16f));
		mengetxt.setBounds(100, 160, 200, 40);
		add(mengetxt);

		JLabel flaschenlabel = new JLabel("Flaschen:");
		flaschenlabel.setFont(flaschenlabel.getFont().deriveFont(16f));
		flaschenlabel.setBounds(0, 200, 200, 40);
		add(flaschenlabel);

		flaschenbox = new JCheckBox();
		flaschenbox.setSelected(t.isInFlaschen());
		flaschenbox.setBounds(160, 205, 30, 30);
		add(flaschenbox);

		JButton l�schen = new JButton("L�schen");
		l�schen.setBounds(0, 240, 107, 40);
		add(l�schen);
		l�schen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(
						TerminBearbeitenDialog.this,
						"Wollen sie den Termin wirklich l�schen?");
				if (result == JOptionPane.YES_OPTION) {
					for (Termin t : alleTermine) {
						if (t.getTerminId() == termin.getTerminId()) {
							t.setKundenId(0);
						}
					}
					TerminLogik.terminL�schen(termin);
					TerminBearbeitenDialog.this.dispose();
				}
			}
		});

		JButton schliessen = new JButton("Schlie�en");
		schliessen.setBounds(214, 240, 107, 40);
		add(schliessen);
		schliessen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				TerminBearbeitenDialog.this.dispose();
			}

		});

		JButton verschieben = new JButton("Verschieben");
		verschieben.setBounds(107, 240, 107, 40);
		verschieben.addActionListener(new MyVerschiebenHandler());
		add(verschieben);

		JButton verschiebeTermin = new JButton("Termin verschieben");
		verschiebeTermin.setBounds(400, 160, 200, 40);
		add(verschiebeTermin);
		verschiebeTermin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TerminHinzuf�genFrame(termin.getDatum().getTime());

			}
		});

		setVisible(true);
	}
}
