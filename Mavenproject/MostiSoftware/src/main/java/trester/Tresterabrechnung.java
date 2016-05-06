package trester;

import main.Kundeneinkäufe;

public class Tresterabrechnung {
	
	private Kundeneinkäufe kundeneinkäufe;
	private int gesamtLiter;
	private double preis;
	
	public Tresterabrechnung(Kundeneinkäufe k){
		this.kundeneinkäufe = k;
		gesamtLiter = berechneGesamtLiter();
		setPreis(berechnePreis());
	}
	
	private int berechneGesamtLiter(){
		int total = 0;
		for(int i = 0; i < kundeneinkäufe.getSize(); i++){
			total = total + kundeneinkäufe.getEinkauf(i).getLiterzahl();
		}
		return total;
	}
	
	private double berechnePreis(){
		return gesamtLiter * 1.00;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	public int getLiterzahl() {
		return gesamtLiter;
	}

	public void setLiterzahl(int liter) {
		this.gesamtLiter = liter;
	}
	
	public void printTresterAbrechnung(){
		System.out.println("Trester"+preis + " € für " + gesamtLiter + " L");
	}

}
