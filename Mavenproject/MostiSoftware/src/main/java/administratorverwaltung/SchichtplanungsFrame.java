package administratorverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.toedter.calendar.JCalendar;

public class SchichtplanungsFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JCalendar calendar;
	private JMenuBar mbar;
	
	
	public SchichtplanungsFrame(){
		
		setBounds(500, 200, 520, 500);
		setTitle("Schichtplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		mbar = new JMenuBar();
		setJMenuBar(mbar);
		
		JMenu schicht = new JMenu("Schichten");
		schicht.setFont(schicht.getFont().deriveFont(15f));
		mbar.add(schicht);
		
		JMenuItem neueSchicht = new JMenuItem("Neuen Schicht anlegen");
		schicht.add(neueSchicht);
		neueSchicht.setFont(neueSchicht.getFont().deriveFont(15f));
		neueSchicht.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SchichtHinzufügenFrame();
			}
		});
		
		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 500, 350);
		calendar.setVisible(true);
		add(calendar);

		JButton schichtuebersicht = new JButton("Schichtübersicht");
		schichtuebersicht.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Date d = calendar.getDate();
				new SchichtTagFrame(d, 1, null);
			}
		});
		schichtuebersicht.setBounds(30, 360, 180, 30);
		add(schichtuebersicht);
		
		setVisible(true);
	}
	
	public static void main(String[] avgs){
		new SchichtplanungsFrame();
	}
	

}
