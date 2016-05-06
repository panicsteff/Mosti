package main;

import java.util.ArrayList;

import verkaufsverwaltung.Einkauf;

public class Kundeneinkäufe {
	
	private ArrayList<Einkauf> kundeneinkäufe;
	
	public Kundeneinkäufe (){
		kundeneinkäufe = new ArrayList<Einkauf>();
	}
	
	public void addEinkauf(Einkauf e){
		kundeneinkäufe.add(e);
	}
	
	public Einkauf getEinkauf(int index){
		return kundeneinkäufe.get(index);
	}
	
	public boolean containsEinkauf(Einkauf e){
		if(kundeneinkäufe.contains(e))
			return true;
		else return false;			
	}
	
	public void printKundeneinkäufe(){
		int count = 1;
		for(Einkauf e: kundeneinkäufe){
			System.out.print(count++ + ": ");
			e.printEinkauf();
		}
	}
	
	public int getSize(){
		return kundeneinkäufe != null? kundeneinkäufe.size():0;
	}
	



}
