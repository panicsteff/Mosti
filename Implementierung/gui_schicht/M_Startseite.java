package gui_schicht;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import produkte.Produkt;

public class M_Startseite extends JFrame{
	
	JMenuBar mbar;
	JMenu mDatei;
	JMenuItem abrechnen;
	
	
	public M_Startseite (){
		setSize(500,500);
		setTitle("Startseite");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		mbar = new JMenuBar();
		setJMenuBar(mbar);
		mDatei = new JMenu("Datei");
		mbar.add(mDatei);
		abrechnen = new JMenuItem("Abrechnen");
		mDatei.add(abrechnen);
		abrechnen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<Produkt> p = new ArrayList<Produkt>();
				Produkt p1 = new Produkt ("5L Box", 1.00, 10, 200);
				Produkt p2 = new Produkt ("10L Box", 2.00, 100, 200);
				Produkt p3 = new Produkt ("10L Box", 2.00, 100, 200);
				p.add(p1);
				p.add(p2);
				p.add(p3);
				Abrechnungsrahmen a = new Abrechnungsrahmen(p);
			}
		});
		
		
		setVisible(true);
	}

}
