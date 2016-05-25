package verkaufsverwaltung;

import java.util.ArrayList;

public class Kundeneinkäufe {
	
	private ArrayList<Verkauf> kundeneinkäufe;
	
	public Kundeneinkäufe (){
		kundeneinkäufe = new ArrayList<Verkauf>();
	}
	
	public void addEinkauf(Verkauf e){
		kundeneinkäufe.add(e);
	}
	
	public Verkauf getEinkauf(int index){
		return kundeneinkäufe.get(index);
	}
	
//	public boolean containsEinkauf(Einkauf e){
//		if(kundeneinkäufe.contains(e))
//			return true;
//		else return false;			
//	}
	
	public void printKundeneinkäufe(){
		int count = 1;
		for(Verkauf e: kundeneinkäufe){
			System.out.print(count++ + ": ");
			e.printEinkauf();
		}
	}
	
	public int getSize(){
		return kundeneinkäufe != null? kundeneinkäufe.size():0;
	}
	



}
