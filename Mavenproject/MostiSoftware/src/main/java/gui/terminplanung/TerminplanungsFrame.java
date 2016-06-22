package gui.terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

public class TerminplanungsFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	JCalendar calendar;	
	
	public TerminplanungsFrame(){
		
		setBounds(500, 200, 520, 500);
		setLocationRelativeTo(getParent());
		setTitle("Terminplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JButton neuerTermin = new JButton("Neuen Termin anlegen");
		neuerTermin.setBounds(230, 360, 180, 30);
		neuerTermin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Date d = calendar.getDate();
				new TerminHinzufügenFrame(d.getTime());
			}
		});
		add(neuerTermin);
		
		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 500, 350);
		calendar.setVisible(true);
		add(calendar);

		JButton terminuebersicht = new JButton("Terminübersicht");
		terminuebersicht.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Date d = calendar.getDate();
				new TagFrame(d.getTime(), 1, TerminplanungsFrame.this,0,0, false);
			}
		});
		terminuebersicht.setBounds(30, 360, 180, 30);
		add(terminuebersicht);
		
		setVisible(true);
	}
	
	public static void main(String[] avgs){
		new TerminplanungsFrame();
	}

	
}
