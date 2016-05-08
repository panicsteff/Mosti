package terminplanung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.toedter.calendar.JCalendar;

public class TerminplanungsFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	JCalendar calendar;
	private JMenuBar mbar;
	
	
	public TerminplanungsFrame(){
		
		setBounds(500, 200, 520, 500);
		setTitle("Terminplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		mbar = new JMenuBar();
		setJMenuBar(mbar);
		
		JMenu termine = new JMenu("Termine");
		termine.setFont(termine.getFont().deriveFont(15f));
		mbar.add(termine);
		
		JMenuItem neuerTermin = new JMenuItem("Neuen Termin anlegen");
		termine.add(neuerTermin);
		neuerTermin.setFont(neuerTermin.getFont().deriveFont(15f));
		neuerTermin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new TerminHinzufügenFrame();
			}
		});
		
		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 500, 350);
		calendar.setVisible(true);
		add(calendar);

		JButton terminuebersicht = new JButton("Terminübersicht");
		terminuebersicht.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Date d = calendar.getDate();
				new TagFrame(d, 1, null);
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
