package terminplanung;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kundenverwaltung.Formats;

public class TerminErstellenDialog extends JDialog {

	class NameCellRenderer extends DefaultListCellRenderer.UIResource{

		private static final long serialVersionUID = 1L;
		TerminDB termindb = new TerminDB();

		public Component getListCellRendererComponent(JList list, Object value,
				int arg2, boolean arg3, boolean arg4) {
			
			super.getListCellRendererComponent(list, value, arg2, arg3, arg4);
			int kundenId = Integer.parseInt((String)value);
			String name = termindb.kundenNamenLaden(kundenId);
			setText(name);
			return this;
			
		}
		
	}
	
	
	class MyKeyListener extends KeyAdapter {
		public void keyTyped(KeyEvent k) {
			
			boolean delete = false;
			
			char c = k.getKeyChar();
			if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
				eingabe = eingabe + c;
				delete = false;
			}
			if (c == '\b') {
				if (eingabe.length() > 0) {
					eingabe = eingabe.substring(0, eingabe.length() - 1);
					delete = true;
				}
			}
			
			if(eingabe != ""){
				ArrayList<Integer> kundenIds = terminlogik.kundenIDLaden(eingabe);
				kundetxt.removeAllItems();
				
				for(Integer i : kundenIds){
					kundetxt.addItem(i.toString());
				}
				kundetxt.setSelectedItem(null);
				if(delete == true){
					kundetxt.getEditor().setItem(eingabe);
				}else{
					if(eingabe.length() - 1 >= 0){
						kundetxt.getEditor().setItem(eingabe.substring(0, eingabe.length() - 1));
					}
					
				}
			}
			
		}
	}

	class MyOkListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			for (Termin t : terminliste) {
				t.setKundenId(kundenId);
			}
			TerminErstellenDialog.this.dispose();

		}
	}

	private static final long serialVersionUID = 1L;
	private JTextField dauertxt;
	private JTextField uhrzeittxt;
	private JComboBox<String> kundetxt;
	private ArrayList<Termin> terminliste;
	private String eingabe = "";
	private int kundenId;
	private TerminLogik terminlogik;

	public TerminErstellenDialog(int dauer, Date date, ArrayList<Termin> t,
			String uhrzeitAnzeige) {

		setModal(true);

		setTitle("Termin anlegen");
		setSize(400, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		terminliste = t;
		terminlogik = new TerminLogik();

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(5, 2));

		JLabel kunde = new JLabel("Kunde:");
		kunde.setFont(kunde.getFont().deriveFont(16f));
		kundetxt = new JComboBox<String>();
		kundetxt.setFont(kundetxt.getFont().deriveFont(16f));
		kundetxt.setEditable(true);
		kundetxt.getEditor().getEditorComponent().addKeyListener(new MyKeyListener());
		kundetxt.setRenderer(new NameCellRenderer());
	

		JLabel datum = new JLabel("Datum:");
		datum.setFont(datum.getFont().deriveFont(16f));
		JTextField datumtxt = new JTextField(Formats.DATE_FORMAT.format(date));
		datumtxt.setFont(datumtxt.getFont().deriveFont(16f));

		JLabel uhrzeit = new JLabel("Uhrzeit:");
		uhrzeit.setFont(uhrzeit.getFont().deriveFont(16f));
		uhrzeittxt = new JTextField(uhrzeitAnzeige);
		uhrzeittxt.setFont(uhrzeittxt.getFont().deriveFont(16f));

		JLabel dauerlabel = new JLabel("Dauer:");
		dauerlabel.setFont(dauerlabel.getFont().deriveFont(16f));
		dauertxt = new JTextField(dauer + " min");
		dauertxt.setFont(dauertxt.getFont().deriveFont(16f));

		JButton speichern = new JButton("Speichern");
		speichern.addActionListener(new MyOkListener());

		content.add(kunde);
		content.add(kundetxt);
		content.add(datum);
		content.add(datumtxt);
		content.add(uhrzeit);
		content.add(uhrzeittxt);
		content.add(dauerlabel);
		content.add(dauertxt);
		content.add(speichern);

		add(content);

		setVisible(true);

	}

	public static void main(String[] acgs) {

		new TerminErstellenDialog(5, new Date(), null, "Hallo");
	}
}
