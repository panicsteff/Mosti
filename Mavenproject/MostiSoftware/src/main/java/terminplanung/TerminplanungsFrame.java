package terminplanung;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.toedter.calendar.JCalendar;

public class TerminplanungsFrame extends JFrame{

	JCalendar calendar;
	
	public TerminplanungsFrame(){
		
		setSize(600,400);
		setTitle("Terminplanung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(0, 0, 500, 350);
		calendar.setVisible(true);
		add(calendar);

		setVisible(true);
	}
	
	public static void main(String[] avgs){
		
		new TerminplanungsFrame();
	}
}
