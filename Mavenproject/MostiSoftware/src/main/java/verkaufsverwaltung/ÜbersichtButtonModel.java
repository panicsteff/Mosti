package verkaufsverwaltung;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ÜbersichtButtonModel extends JToggleButton.ToggleButtonModel {

	private boolean auswahl;
	
	public ÜbersichtButtonModel (boolean auswahl){
		this.auswahl = auswahl;
	}
	
	public boolean getValue(){
		return auswahl;
	}
}


