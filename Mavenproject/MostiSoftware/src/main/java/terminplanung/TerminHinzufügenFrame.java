package terminplanung;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TerminHinzufügenFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	
	public TerminHinzufügenFrame(){
		setBounds(350, 200, 300, 200);
		setTitle("Neuer Termin");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(null);
		JLabel beginn = new JLabel("Terminbeginn");
		beginn.setBounds(10, 10, 100, 30);
		add(beginn);
		
		JTextField txtbeginn = new JTextField();
		txtbeginn.setBounds(150, 10, 100, 30);
		add(txtbeginn);
		
		
		
		setVisible(true);
	}
	
	
	public static void main(String[] avgs){
		new TerminHinzufügenFrame();
	}
}
