package kassenfunktionTest;

import java.util.ArrayList;

import verkaufsverwaltung.Dienstleistung;
import verkaufsverwaltung.Produkt;
import kassenfunktion.Abf�llMaterialTableModel;
import kassenfunktion.DienstleistungenTableModel;
import kassenfunktion.ZusatzProduktTableModel;

public class AbrechnungsrahmenTest {

	public static void main(String[] avg) {
		
		//berechneTeilpreisDienstleistung();
		//berechneTeilpreisZusatzprodukt();
		berechneTeilpreisAbf�llMaterial();
	}


	public static void berechneTeilpreisDienstleistung(){
		ArrayList<Dienstleistung> d = new ArrayList<Dienstleistung>();
		Dienstleistung d1 = new Dienstleistung("Literzahl - hei� in Beutel", 0.1,0);
		Dienstleistung d2 = new Dienstleistung("Literzahl - hei� in Flaschen", 0.2,0);
		Dienstleistung d3 = new Dienstleistung("Literzahl - kalt", 0.3,0);
		d.add(d1);
		d.add(d2);
		d.add(d3);
		
		int[][] testwerte = {{5,5,5}, {5,0,5}, {0,0,0}, {1,1,-1}, {1000,0,-1}};
		double[] resultat = new double[testwerte.length];
		double[] l�sungen = new double[testwerte.length];
		boolean auswertung[] = new boolean[testwerte.length];
		
		for(int i=0; i<l�sungen.length; i++){
			if(testwerte[i][0] <0){
				testwerte[i][0] = 0;
			}	
			if(testwerte[i][1] <0){
				testwerte[i][1] = 0;
			}
			if(testwerte[i][2] <0){
				testwerte[i][2] = 0;
			}
			double l�sung =  0.1* testwerte[i][0] + 0.2*testwerte[i][1] + 0.3*testwerte[i][2];
			l�sung = Math.round(l�sung*100);
			l�sung = l�sung/100;
			l�sungen[i] = l�sung;
			 
		}
		
		DienstleistungenTableModel dtm = new DienstleistungenTableModel(d);
		
		for(int i=0; i<testwerte.length; i++){
			for(int j=0; j<3; j++){
				dtm.setValueAt(testwerte[i][j], 0, j);				
			}
			resultat[i] = dtm.berechneTeilpreis();
		}
		
		for(int i=0; i<resultat.length; i++){
			if(resultat[i] == l�sungen[i]){
				System.out.println(i +". true");
				auswertung[i] = true;
			} else{
				System.out.println(i +". false");
				auswertung[i] = false;
			}
		}
	}
	
	public static void berechneTeilpreisZusatzprodukt(){
		
		ArrayList<Produkt> z = new ArrayList<Produkt>();
		Produkt z1 = new Produkt("Saftbox-St�nder", 12.00, 10, 200, true,0);
		Produkt z2 = new Produkt("Hefe - unterg�rig", 1.50, 100, 300, true,0);
		Produkt z3 = new Produkt("Hefe - oberg�rig", 2.00, 100, 200, true,0);
		z.add(z1);
		z.add(z2);
		z.add(z3);
		
		int[][] testwerte = {{5,5,5}, {5,0,5}, {0,0,0}, {1,1,-1}, {1000,0,-1}};
		double[] resultat = new double[testwerte.length];
		double[] l�sungen = new double[testwerte.length];
		boolean auswertung[] = new boolean[testwerte.length];
		
		for(int i=0; i<l�sungen.length; i++){
			if(testwerte[i][0] <0){
				testwerte[i][0] = 0;
			}	
			if(testwerte[i][1] <0){
				testwerte[i][1] = 0;
			}
			if(testwerte[i][2] <0){
				testwerte[i][2] = 0;
			}
			double l�sung =  12* testwerte[i][0] + 1.5*testwerte[i][1] + 2.0*testwerte[i][2];
			l�sung = Math.round(l�sung*100);
			l�sung = l�sung/100;
			l�sungen[i] = l�sung;
			 
		}
		
		ZusatzProduktTableModel ztm = new ZusatzProduktTableModel(z);
		
		for(int i=0; i<testwerte.length; i++){
			for(int j=0; j<3; j++){
				ztm.setValueAt(testwerte[i][j], 0, j);				
			}
			resultat[i] = ztm.berechneTeilpreis();
		}
		
		for(int i=0; i<resultat.length; i++){
			if(resultat[i] == l�sungen[i]){
				System.out.println(i +". true");
				auswertung[i] = true;
			} else{
				System.out.println(i +". false");
				auswertung[i] = false;
			}
		}
	}
	
	public static void berechneTeilpreisAbf�llMaterial(){
		
		ArrayList<Produkt> p = new ArrayList<Produkt>();
		Produkt p1 = new Produkt("3L-Beutel", 1.00, 10, 200, false,0);
		Produkt p2 = new Produkt("5L-Beutel", 1.50, 100, 300, false,0);
		Produkt p3 = new Produkt("10L-Beutel", 2.00, 100, 200, false,0);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		
		int[][] testwerte = {{5,5,5}, {5,0,5}, {0,0,0}, {1,1,-1}, {1000,0,-1}};
		double[] resultat = new double[testwerte.length];
		double[] l�sungen = new double[testwerte.length];
		boolean auswertung[] = new boolean[testwerte.length];
		
		for(int i=0; i<l�sungen.length; i++){
			if(testwerte[i][0] <0){
				testwerte[i][0] = 0;
			}	
			if(testwerte[i][1] <0){
				testwerte[i][1] = 0;
			}
			if(testwerte[i][2] <0){
				testwerte[i][2] = 0;
			}
			double l�sung =  1* testwerte[i][0] + 1.5*testwerte[i][1] + 2.0*testwerte[i][2];
			l�sung = Math.round(l�sung*100);
			l�sung = l�sung/100;
			l�sungen[i] = l�sung;
			 
		}
		
		Abf�llMaterialTableModel atm = new Abf�llMaterialTableModel(p);
		
		for(int i=0; i<testwerte.length; i++){
			for(int j=0; j<3; j++){
				atm.setValueAt(testwerte[i][j], 0, j);				
			}
			resultat[i] = atm.berechneTeilpreis();
		}
		
		for(int i=0; i<resultat.length; i++){
			if(resultat[i] == l�sungen[i]){
				System.out.println(i +". true");
				auswertung[i] = true;
			} else{
				System.out.println(i +". false");
				auswertung[i] = false;
			}
		}
	}	
	
//	public static void einkaufAbschlie�en(){
//		
//		ArrayList<Produkt> p = new ArrayList<Produkt>();
//		Produkt p1 = new Produkt("3L-Beutel", 1.00, 10, 200);
//		Produkt p2 = new Produkt("5L-Beutel", 1.50, 100, 300);
//		Produkt p3 = new Produkt("10L-Beutel", 2.00, 100, 200);
//		p.add(p1);
//		p.add(p2);
//		p.add(p3);
//		
//		ArrayList<Produkt> z = new ArrayList<Produkt>();
//		Produkt z1 = new Produkt("Saftbox-St�nder", 12.00, 10, 200);
//		Produkt z2 = new Produkt("Hefe - unterg�rig", 1.50, 100, 300);
//		Produkt z3 = new Produkt("Hefe - oberg�rig", 2.00, 100, 200);
//		z.add(z1);
//		z.add(z2);
//		z.add(z3);
//		
//		new Abrechnungsrahmen(p,z);
//		
//	}
//	
//	
//	
}

