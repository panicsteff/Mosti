package logik.trester;


public class Tresterabrechnung {
	
	private int gesamtLiter;
	private double preis;
	Tresterverwaltung tv;
	
	public Tresterabrechnung(Tresterverwaltung tv, int literzahl){
		//this.kundeneink�ufe = k;
//		gesamtLiter = berechneGesamtLiter();
//		setPreis(berechnePreis());
		this.tv = tv;
		gesamtLiter = literzahl;
		preis = berechnePreis();
	}
	
//	private int berechneGesamtLiter(){
//		int total = 0;
//		for(int i = 0; i < kundeneink�ufe.getSize(); i++){
//			total = total + kundeneink�ufe.getEinkauf(i).getLiterzahl();
//		}
//		return total;
//	}
	
	private double berechnePreis(){
		//return gesamtLiter * 1.00;
		return (getLiterzahl()/1000) * tv.getPreisPro1000L();
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
		System.out.println("Trester"+preis + " � f�r " + gesamtLiter + " L");
	}

}
