package kassenfunktionTest;

import java.util.ArrayList;

import kassenfunktion.Abrechnungsrahmen;
import dienstleistungProdukt.Produkt;

public class AbrechnungsrahmenTest {

	public static void main(String[] avg) {
		//mehrereProdukte();										// funktioniert
		//keineProdukte();											// funktioniert
		//kostenAktualisieren();									// funktioniert
		einkaufAbschlieﬂen();
		
	}

	public static void mehrereProdukte() {

			ArrayList<Produkt> p = new ArrayList<Produkt>();
			Produkt p1 = new Produkt("3L-Beutel", 1.00, 10, 200);
			Produkt p2 = new Produkt("5L-Beutel", 1.50, 100, 300);
			Produkt p3 = new Produkt("10L-Beutel", 2.00, 100, 200);
			Produkt p4 = new Produkt("3L Beutel+Box", 2.00, 100, 200);
			Produkt p5 = new Produkt("5L Beutel+Box", 2.50, 100, 200);
			Produkt p6 = new Produkt("10L Beutel+Box", 3.00, 100, 200);
			Produkt p7 = new Produkt("10L Beutel+Box", 3.00, 100, 200);
			p.add(p1);
			p.add(p2);
			p.add(p3);
			p.add(p4);
			p.add(p5);
			p.add(p6);
			p.add(p7);
			
			ArrayList<Produkt> z = new ArrayList<Produkt>();
			Produkt z1 = new Produkt("Saftbox-St‰nder", 12.00, 10, 200);
			Produkt z2 = new Produkt("Hefe - unterg‰rig", 1.50, 100, 300);
			Produkt z3 = new Produkt("Hefe - oberg‰rig", 2.00, 100, 200);
			z.add(z1);
			z.add(z2);
			z.add(z3);
			z.add(z3);
			z.add(z1);

			new Abrechnungsrahmen(p, z);
		
	}
	
	public static void keineProdukte(){
		
		ArrayList<Produkt> liste = new ArrayList<Produkt>();
		new Abrechnungsrahmen(liste, liste);
				
	}

	public static void kostenAktualisieren(){   //verschedene Benutzereingaben
		
		ArrayList<Produkt> p = new ArrayList<Produkt>();
		Produkt p1 = new Produkt("3L-Beutel", 1.00, 10, 200);
		Produkt p2 = new Produkt("5L-Beutel", 1.50, 100, 300);
		Produkt p3 = new Produkt("10L-Beutel", 2.00, 100, 200);
		Produkt p4 = new Produkt("3L Beutel+Box", 2.00, 100, 200);
		Produkt p5 = new Produkt("5L Beutel+Box", 2.50, 100, 200);
		Produkt p6 = new Produkt("10L Beutel+Box", 3.00, 100, 200);
		Produkt p7 = new Produkt("10L Beutel+Box", 3.00, 100, 200);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		
		ArrayList<Produkt> z = new ArrayList<Produkt>();
		Produkt z1 = new Produkt("Saftbox-St‰nder", 12.00, 10, 200);
		Produkt z2 = new Produkt("Hefe - unterg‰rig", 1.50, 100, 300);
		Produkt z3 = new Produkt("Hefe - oberg‰rig", 2.00, 100, 200);
		Produkt z4 = new Produkt("Hefe - oberg‰rig", 2.00, 100, 200);
		z.add(z1);
		z.add(z2);
		z.add(z3);
		z.add(z4);
		
		Abrechnungsrahmen a = new Abrechnungsrahmen(p,z);
	}
	
	public static void einkaufAbschlieﬂen(){
		
		ArrayList<Produkt> p = new ArrayList<Produkt>();
		Produkt p1 = new Produkt("3L-Beutel", 1.00, 10, 200);
		Produkt p2 = new Produkt("5L-Beutel", 1.50, 100, 300);
		Produkt p3 = new Produkt("10L-Beutel", 2.00, 100, 200);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		
		ArrayList<Produkt> z = new ArrayList<Produkt>();
		Produkt z1 = new Produkt("Saftbox-St‰nder", 12.00, 10, 200);
		Produkt z2 = new Produkt("Hefe - unterg‰rig", 1.50, 100, 300);
		Produkt z3 = new Produkt("Hefe - oberg‰rig", 2.00, 100, 200);
		z.add(z1);
		z.add(z2);
		z.add(z3);
		
		new Abrechnungsrahmen(p,z);
		
	}
	
	
	
}
