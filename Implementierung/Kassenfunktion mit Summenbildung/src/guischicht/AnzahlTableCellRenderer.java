package guischicht;

import java.text.DecimalFormat;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class AnzahlTableCellRenderer extends DefaultTableCellRenderer.UIResource {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DecimalFormat format;
	
	public AnzahlTableCellRenderer(){
		format = new DecimalFormat("##0");
		setHorizontalAlignment(SwingConstants.RIGHT);
	}


	
//	public String getTableCellRendererComponent(int zahl) {
//
//		String s = format.format(zahl);
//		setText(s);
//		this.setHorizontalAlignment(RIGHT);
//		return s;
//	}
	
	
	
	
	
//	private DecimalFormat format;
//
//	public RabattTableCellRenderer() {
//		format = new DecimalFormat("##0,00 %");
//		setHorizontalAlignment(SwingConstants.RIGHT);
//	}
//
//	public String getmyTableCellRendererComponent(double zahl) {
//
//		double d = (Double) zahl * 100.0;
//		String s = format.format(d);
//		setText(s);
//		this.setHorizontalAlignment(RIGHT);
//		return s;
//	}

}
