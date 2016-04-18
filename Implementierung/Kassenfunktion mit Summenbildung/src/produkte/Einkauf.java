package produkte;

import java.util.ArrayList;

public class Einkauf {

	private ArrayList<Einkaufsposition> einkaufsliste;
	private double total;

	public Einkauf() {
		einkaufsliste = new ArrayList<Einkaufsposition>();
		total = 0;
	}

	public void addEinkauf(Einkaufsposition position) {
		einkaufsliste.add(position);
	}

	public void printEinkauf() {
		for (Einkaufsposition e : einkaufsliste) {
			if(e.getVerkaufsMenge()!= 0){
			System.out.println(e.getVerkaufsMenge() + "x " + e.getName());
		}}
	}

	public void setSumme(double total){
		this.total = total;
	}
	
	public double getsumme(){
		return total;
	}
	
	
}
