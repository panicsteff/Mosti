package verkaufsverwaltung;

import java.util.ArrayList;

public class Kundeneink�ufe {
	
	private ArrayList<Verkauf> kundeneink�ufe;
	
	public Kundeneink�ufe (){
		kundeneink�ufe = new ArrayList<Verkauf>();
	}
	
	public void addEinkauf(Verkauf e){
		kundeneink�ufe.add(e);
	}
	
	public Verkauf getEinkauf(int index){
		return kundeneink�ufe.get(index);
	}
	
//	public boolean containsEinkauf(Einkauf e){
//		if(kundeneink�ufe.contains(e))
//			return true;
//		else return false;			
//	}
	
	public void printKundeneink�ufe(){
		int count = 1;
		for(Verkauf e: kundeneink�ufe){
			System.out.print(count++ + ": ");
			e.printEinkauf();
		}
	}
	
	public int getSize(){
		return kundeneink�ufe != null? kundeneink�ufe.size():0;
	}
	



}
