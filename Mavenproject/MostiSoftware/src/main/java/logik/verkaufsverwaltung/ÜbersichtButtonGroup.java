package logik.verkaufsverwaltung;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class �bersichtButtonGroup extends ButtonGroup {
		
		public boolean getValue(){
			�bersichtButtonModel model = (�bersichtButtonModel)getSelection();
			return model.getValue();
		}
		
		public void setValue (boolean a){
			for (AbstractButton b : buttons){
				�bersichtButtonModel model = (�bersichtButtonModel)b.getModel();
				if(model.getValue() == a)
					b.setSelected(true);
			}
		}


}
