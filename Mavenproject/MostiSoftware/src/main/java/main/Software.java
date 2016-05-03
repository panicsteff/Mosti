package main;

import gui.M_Startseite;

import java.util.ArrayList;

import kassenfunktion.Abrechnungsrahmen;
import dienstleistungProdukt.Produkt;

public class Software {

	public static void main(String[] args) {
		
		Angebote a = new Angebote();
		Kundeneinkäufe k = new Kundeneinkäufe();
		M_Startseite startseite = new M_Startseite(a,k);
		
		
		
		
//		ArrayList<Produkt> p = new ArrayList<Produkt>();
//		Produkt p1 = new Produkt("3L-Beutel", 1.00, 10, 200,true);
//		Produkt p2 = new Produkt("5L-Beutel", 1.50, 100, 300);
//		Produkt p3 = new Produkt("10L-Beutel", 2.00, 100, 200);
//		Produkt p4 = new Produkt("3L Beutel+Box", 2.00, 100, 200);
//		Produkt p5 = new Produkt("5L Beutel+Box", 2.50, 100, 200);
//		Produkt p6 = new Produkt("10L Beutel+Box", 3.00, 100, 200);
//		p.add(p1);
//		p.add(p2);
//		p.add(p3);
//		p.add(p4);
//		p.add(p5);
//		p.add(p6);

//		ArrayList<Produkt> z = new ArrayList<Produkt>();
//		Produkt z1 = new Produkt("Saftbox-Ständer", 12.00, 10, 200,true);
//		Produkt z2 = new Produkt("Hefe - untergärig", 1.50, 100, 300,true);
//		Produkt z3 = new Produkt("Hefe - obergärig", 2.00, 100, 200,true);
//		z.add(z1);
//		z.add(z2);
//		z.add(z3);
//
//		Abrechnungsrahmen a = new Abrechnungsrahmen(p, z);
	}

}
