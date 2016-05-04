package terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

public class TerminplanungsFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	JCalendar calendar;
	TerminDB terminDb;
	private ArrayList<Integer> adminwerte;
	
	public TerminplanungsFrame(){
		
		setBounds(500, 200, 520, 500);
		setTitle("Terminplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 500, 350);
		calendar.setVisible(true);
		add(calendar);

		JButton terminwaehlen = new JButton("Termin wählen");
		terminwaehlen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				terminDb = new TerminDB();
				adminwerte = terminDb.adminwerteLaden();
				
				int zeitslot = adminwerte.get(0);
				int beginn = adminwerte.get(1);
				int ende = adminwerte.get(2);
				int aufteilung = adminwerte.get(3);
				
				int zeilenanzahl = (ende-beginn)/(zeitslot*aufteilung);
				
				Date d = calendar.getDate();
				new TagFrame(d, zeilenanzahl, 1, adminwerte);
			}
		});
		terminwaehlen.setBounds(30, 360, 180, 30);
		add(terminwaehlen);
		
		setVisible(true);
	}
	
}
