package main;

import java.util.ArrayList;

import verkaufsverwaltung.Einkauf;

public class Kundeneink�ufe {
	
	private ArrayList<Einkauf> kundeneink�ufe;
	
	public Kundeneink�ufe (){
		kundeneink�ufe = new ArrayList<Einkauf>();
	}
	
	public void addEinkauf(Einkauf e){
		kundeneink�ufe.add(e);
	}
	
	public Einkauf getEinkauf(int index){
		return kundeneink�ufe.get(index);
	}
	
	public boolean containsEinkauf(Einkauf e){
		if(kundeneink�ufe.contains(e))
			return true;
		else return false;			
	}
	
	public void printKundeneink�ufe(){
		int count = 1;
		for(Einkauf e: kundeneink�ufe){
			System.out.print(count++ + ": ");
			e.printEinkauf();
		}
	}
	
	public int getSize(){
		return kundeneink�ufe != null? kundeneink�ufe.size():0;
	}
	



}
