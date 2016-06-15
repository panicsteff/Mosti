package gui.schichtverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

public class SchichtplanungsFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JCalendar calendar;
	
	
	public SchichtplanungsFrame(boolean isAdmin){
		
		setBounds(500, 200, 520, 500);
		setTitle("Schichtplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 500, 350);
		calendar.setVisible(true);
		add(calendar);

		if(!isAdmin){
			JButton schichtuebersicht = new JButton("Schichtübersicht");
			schichtuebersicht.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Date d = calendar.getDate();
					new SchichtTagFrame(d);
				}
			});
			schichtuebersicht.setBounds(30, 360, 180, 30);
			add(schichtuebersicht);
		
			setVisible(true);
		}
		else{
			JButton neueSchicht = new JButton("Schicht bearbeiten");
			neueSchicht.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					Date d = calendar.getDate();
					new SchichtBearbeitenFrame(d.getTime());
				}
			
			});
			neueSchicht.setBounds(30, 360, 180, 30);
			add(neueSchicht);
		
			setVisible(true);
		}
	}
	
}
