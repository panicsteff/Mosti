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
		
		setBounds(500, 200, 720, 600);
		setLocationRelativeTo(getParent());
		setTitle("Schichtplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 700, 450);
		calendar.setVisible(true);
		add(calendar);

		if(!isAdmin){
			JButton schichtuebersicht = new JButton("Schichtübersicht");
			schichtuebersicht.setFont(schichtuebersicht.getFont().deriveFont(16f));
			schichtuebersicht.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Date d = calendar.getDate();
					new SchichtTagFrame(d);
				}
			});
			schichtuebersicht.setBounds(30, 450, 180, 30);
			add(schichtuebersicht);
		
			setVisible(true);
		}
		else{
			JButton neueSchicht = new JButton("Schicht bearbeiten");
			neueSchicht.setFont(neueSchicht.getFont().deriveFont(16f));
			neueSchicht.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					Date d = calendar.getDate();
					new SchichtBearbeitenFrame(d.getTime());
				}
			
			});
			neueSchicht.setBounds(30, 450, 180, 30);
			add(neueSchicht);
		
			setVisible(true);
		}
	}
	
}
