package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import terminplanung.Kalenderframe;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Termintest {

	private JFrame frmKalender;
	//private Kalenderframe kalenderframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Termintest window = new Termintest();
					window.frmKalender.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Termintest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKalender = new JFrame();
		frmKalender.setTitle("Terminauswahl");
		frmKalender.setBounds(100, 100, 450, 300);
		frmKalender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKalender.getContentPane().setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Kalenderframe();
			}
		});
		dateChooser.setBounds(10, 11, 186, 20);
		frmKalender.getContentPane().add(dateChooser);
		
	}
}
