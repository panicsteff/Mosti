package logik.trester;

public class Tresterverwaltung {
	
	private double preisPro1000L;
	
	public Tresterverwaltung(){
		preisPro1000L = 15.00; //Default-Wert
	}

	public double getPreisPro1000L() {
		return preisPro1000L;
	}

	public void setPreisPro1000L(double preis) {
		preisPro1000L = preis;
	}


}
