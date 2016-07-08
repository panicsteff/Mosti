package gui.terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.toedter.calendar.JCalendar;

public class TerminplanungsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	JCalendar calendar;

	public TerminplanungsFrame() {

		setBounds(500, 200, 720, 550);
		setLocationRelativeTo(getParent());
		setTitle("Terminplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JButton neuerTermin = new JButton("Neuen Termin anlegen");
		neuerTermin.setFont(neuerTermin.getFont().deriveFont(16f));
		neuerTermin.setBounds(230, 420, 230, 30);
		neuerTermin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date d = null;
				try {
					d = calendar.getDate();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage()
							+ "Datum");
				}
				try {
					new TerminHinzufügenFrame(d.getTime());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage()
							+ "PLanung");
				}

			}
		});
		add(neuerTermin);

		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 700, 420);
		calendar.setVisible(true);
		add(calendar);

		JButton terminuebersicht = new JButton("Terminübersicht");
		terminuebersicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date d = null;
				try {
					d = calendar.getDate();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage()
							+ "Datum");
				}
				try {
					new TagFrame(d.getTime(), 1, TerminplanungsFrame.this, 0,
							0, false);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage()
							+ "planung");
				}

			}
		});
		terminuebersicht.setFont(terminuebersicht.getFont().deriveFont(16f));
		terminuebersicht.setBounds(30, 420, 180, 30);
		add(terminuebersicht);

		setVisible(true);
	}

	public static void main(String[] avgs) {
		new TerminplanungsFrame();
	}

}
