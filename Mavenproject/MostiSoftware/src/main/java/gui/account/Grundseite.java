package gui.account;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Grundseite {

	JFrame frame;

	public Grundseite() {
		frame = new JFrame("Mosti");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		frame.setSize(xSize, ySize);
		ImageIcon icon = new ImageIcon("./src/2971.jpg");
		JLabel l1 = new JLabel(icon);
		frame.add(l1);
		frame.setVisible(true);
		Anmeldung anmeldung = new Anmeldung();
	}
}
