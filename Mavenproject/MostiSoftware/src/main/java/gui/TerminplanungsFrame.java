package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

public class TerminplanungsFrame extends JFrame{

	JDateChooser dateChooser; 
	
	public TerminplanungsFrame(){
		
		setSize(500,400);
		setTitle("Terminplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(20, 20, 200, 20);
		JPanel content = new JPanel();
		content.add(dateChooser);
		
		JButton terminUebersicht = new JButton("Terminübersicht");
		terminUebersicht.setBounds(30, 60, 50, 50);
		terminUebersicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date d = dateChooser.getDate();
				new TagFrame(d,1);
			}
		});
		content.add(terminUebersicht);
		
		
		
		add(content);
		setVisible(true);
	}
	
	public static void main(String[] avgs){
		
		new TerminplanungsFrame();
	}
}
