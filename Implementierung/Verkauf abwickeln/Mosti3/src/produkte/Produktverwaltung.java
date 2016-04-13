package produkte;

import java.util.ArrayList;

public class Produktverwaltung {

	private Produkt[] produktliste;

	public Produktverwaltung() {
		produktliste = new Produkt[20];
		
	}
	
	public void init(){
		for(int i = 0; i < produktliste.length; i++){
			produktliste[i] = null;
		}
	}


	
	public void printProdukte(){
		for(Produkt p : produktliste){
			System.out.println(p.getName() +", "+ p.getPreis() + " € pro L");
		}
	}

}
