package verkaufsverwaltung;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class �bersichtButtonModel extends JToggleButton.ToggleButtonModel {

	private boolean auswahl;
	
	public �bersichtButtonModel (boolean auswahl){
		this.auswahl = auswahl;
	}
	
	public boolean getValue(){
		return auswahl;
	}
}


