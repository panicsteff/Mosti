package terminplanung;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


public class Kalenderframe {

	private JFrame frame;
	private JCalendar calendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kalenderframe window = new Kalenderframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Kalenderframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Kalender");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		calendar = new JCalendar();
		calendar.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(calendar);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(435, 270, 100, 30);
		frame.getContentPane().add(dateChooser);
		frame.setVisible(true);
	}
	
	public JCalendar getKalender(){
		return calendar;
	}
}
