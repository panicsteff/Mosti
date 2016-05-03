package terminplanung;

import gui.TagFrame;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;


public class MyDateChooser extends JDateChooser {

	private static final long serialVersionUID = 1L;

	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	public MyDateChooser(){
		super();
		
	}
	
	public void actionPerformed(ActionEvent e){
		String s = "1.9.2016";
		try {
			new TagFrame(df.parse(s), 1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
