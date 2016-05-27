package logik.verkaufsverwaltung;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class ÜbersichtButtonGroup extends ButtonGroup {
		
		public boolean getValue(){
			ÜbersichtButtonModel model = (ÜbersichtButtonModel)getSelection();
			return model.getValue();
		}
		
		public void setValue (boolean a){
			for (AbstractButton b : buttons){
				ÜbersichtButtonModel model = (ÜbersichtButtonModel)b.getModel();
				if(model.getValue() == a)
					b.setSelected(true);
			}
		}


}
