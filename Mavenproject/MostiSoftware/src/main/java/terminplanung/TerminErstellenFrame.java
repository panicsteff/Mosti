package terminplanung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TerminErstellenFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private TermineTableModel termineTableModel;
	private JTextField dauertxt;
	private JTextField uhrzeittxt;
	private Konfigurationswerte k = new Konfigurationswerte();

	public TerminErstellenFrame(int dauer, Date date, int terminId, TermineTableModel ttm){
		
		setTitle("Termin anlegen");
		setSize(300,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		termineTableModel = ttm;
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(5,2));
		
		JLabel kunde = new JLabel("Kunde:");
		kunde.setFont(kunde.getFont().deriveFont(16f));
		JTextField kundetxt = new JTextField();	
		kundetxt.setFont(kundetxt.getFont().deriveFont(16f));
		
		JLabel datum = new JLabel("Datum:");
		datum.setFont(datum.getFont().deriveFont(16f));
		JTextField datumtxt = new JTextField(date.toString());
		datumtxt.setFont(datumtxt.getFont().deriveFont(16f));
		
		JLabel uhrzeit = new JLabel("Uhrzeit:");
		uhrzeit.setFont(uhrzeit.getFont().deriveFont(16f));
		uhrzeittxt = new JTextField(terminId + "");
		uhrzeittxt.setFont(uhrzeittxt.getFont().deriveFont(16f));
		
		JLabel dauerlabel = new JLabel("Dauer:");
		dauerlabel.setFont(dauerlabel.getFont().deriveFont(16f));
		dauertxt = new JTextField(dauer + "");
		dauertxt.setFont(dauertxt.getFont().deriveFont(16f));
		
		JButton speichern = new JButton("Speichern");
		speichern.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String d = dauertxt.getText();
				String t = uhrzeittxt.getText();
				int dauer = 0;
				int startTermin = 0;
				try{
					dauer = Integer.parseInt(d);
					startTermin = Integer.parseInt(t);
				} catch(Exception ex){
					ex.printStackTrace();
				}
				int zeitslotAnzahl = dauer/k.getZeitslot();
				
				for(int i=0; i<zeitslotAnzahl; i++){
					int row = startTermin%40 + 1;						// termin wieder auf tabelle umrechnen
					termineTableModel.setValueAt(5, row +i, 0);
				}
				
			}
		});
		
		
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
	
}
