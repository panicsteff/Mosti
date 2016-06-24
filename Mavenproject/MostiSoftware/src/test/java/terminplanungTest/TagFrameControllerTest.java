package terminplanungTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import logik.terminplanung.TagFrameController;
import logik.terminplanung.Termin;
import logik.terminplanung.TerminLogik;

import org.junit.Test;

public class TagFrameControllerTest {

	@Test
	public void berechneAnzahlZeitslotsTest(){
		
		TagFrameController tfc = new TagFrameController();
		int[] eingabe = {5,20,24};
		int[] lösung = {1,4,4};
		
		for(int i = 0; i<3; i++){
			assertEquals(tfc.berechneAnzahlZeitslots(eingabe[i]), lösung[i]);
		}
		
		
	}
	
	@Test
	public void getTermindauerTest(){
		TagFrameController tfc = new TagFrameController();
		assertEquals(tfc.getTermindauer(5), 25);
	}
	@Test
	public void getZeileTest(){
		TagFrameController tfc = new TagFrameController();
		assertEquals(tfc.getZeile(5, 1), 5);	
		assertEquals(tfc.getZeile(5, 2), 17);	
	}
	
	@Test
	public void anzahlAlleTermineTest(){
		TagFrameController tfc = new TagFrameController();
		assertEquals(tfc.anzahlAlleTermine(), 120);	
	}
	
	@Test
	public void istTerminFreiTest(){
		Termin t1 = new Termin();
		t1.setKundenId(1);
		Termin t2 = new Termin();
		t2.setKundenId(0);
		
		ArrayList<Termin> eingabe1 = new ArrayList<Termin>();
		eingabe1.add(t1);
		eingabe1.add(t1);
		ArrayList<Termin> eingabe2 = new ArrayList<Termin>();
		eingabe2.add(t2);
		eingabe2.add(t2);
		TagFrameController tfc = new TagFrameController();
		assertEquals(tfc.istTerminFrei(eingabe1),false);
		assertEquals(tfc.istTerminFrei(eingabe2),true);
		
	}
	
	@Test
	public void neueTermindauerPrüfenTest(){
		
		TagFrameController tfc = new TagFrameController();
		ArrayList<ArrayList<Termin>> eingabe = new ArrayList<ArrayList<Termin>>();
		ArrayList<Termin> passt = new ArrayList<Termin>();
		ArrayList<Termin> passtauch = new ArrayList<Termin>();
		ArrayList<Termin> passtnicht = new ArrayList<Termin>();
		ArrayList<Termin> passtauchnicht = new ArrayList<Termin>();
		
		Termin t1 = new Termin();
		t1.setKundenId(5);
		t1.setUhrzeit(540);
		Termin t2 = new Termin();
		t2.setKundenId(0);
		t2.setUhrzeit(540);
		Termin t3 = new Termin();
		t3.setKundenId(3);
		
		passt.add(t2);
		passt.add(t2);
		passt.add(t2);
		passt.add(t2);
		
		passtauch.add(t1);
		passtauch.add(t1);
		passtauch.add(t2);
		passtauch.add(t2);
		passtauch.add(t2);
		
		passtnicht.add(t2);
		passtnicht.add(t2);
		passtnicht.add(t3);
		passtnicht.add(t3);
		passtnicht.add(t3);
		
		passtauchnicht.add(t1);
		passtauchnicht.add(t1);
		passtauchnicht.add(t3);
		passtauchnicht.add(t3);
		passtauchnicht.add(t3);
		
		eingabe.add(passt);
		eingabe.add(passtauch);
		eingabe.add(passtnicht);
		eingabe.add(passtauchnicht);
		
		boolean[] lösung = {true, true, false, false};
		
		for(int i = 0; i<4; i++){
			assertEquals(tfc.neueTermindauerPrüfen(eingabe.get(i), t1, 3, 540), lösung[i]);
		}
		
	}
	
	@Test
	public void terminStringNachIntTest(){
		
		ArrayList<String> eingabe = new ArrayList<String>();
		eingabe.add("09:00");
		eingabe.add("10:00");
		eingabe.add("10:05");
		eingabe.add("10:17");
		eingabe.add("08:00");
		eingabe.add("20:30");
		eingabe.add("09:gf");
		eingabe.add("gf:00");
		
		int[] lösung = {540,600, 605, -2, -3, -3, -1,-1};
		TagFrameController tfc = new TagFrameController();
		
		for(int i = 0; i<8; i++){
			assertEquals(tfc.terminStringNachInt(eingabe.get(i)), lösung[i]);
		}
	}
	
	@Test
	public void startTerminfindenTest(){
		
		TagFrameController tfc = new TagFrameController();
		
		Termin t1 = new Termin();
		t1.setKundenId(0);
		t1.setTerminId(0);
		Termin t2 = new Termin();
		t2.setKundenId(0);
		t2.setTerminId(1);
		Termin t3 = new Termin();
		t3.setKundenId(0);
		t3.setTerminId(2);
		Termin t4 = new Termin();
		t4.setKundenId(5);
		t4.setTerminId(3);
		Termin t5 = new Termin();
		t5.setKundenId(5);
		t5.setTerminId(4);
		Termin t6 = new Termin();
		t6.setKundenId(5);
		t6.setTerminId(5);
		
		ArrayList<Termin> eingabe = new ArrayList<Termin>();
		eingabe.add(t1);
		eingabe.add(t2);
		eingabe.add(t3);
		eingabe.add(t4);
		eingabe.add(t5);
		eingabe.add(t6);
		
		assertEquals(tfc.startTerminfinden(eingabe, t5), t4);
		assertEquals(tfc.startTerminfinden(eingabe, t6), t4);
		
		t3.setKundenId(7);
		
		assertEquals(tfc.startTerminfinden(eingabe, t5), t4);
		assertEquals(tfc.startTerminfinden(eingabe, t6), t4);
	}
	
	@Test
	public void isFrueherEnabledTest(){
		TagFrameController tfc = new TagFrameController();
		int[] eingabe = {1, 0, 4, -1};
		boolean[] lösung = {false, true, true, true};
		
		for(int i=0; i<4; i++){
			assertEquals(tfc.isFrueherEnabled(eingabe[i]), lösung[i]);
		}
	}
	
	@Test
	public void isSpaeterEnabledTest(){
		TagFrameController tfc = new TagFrameController();
		int[] eingabe = {10, 0, 4, -1};
		boolean[] lösung = {false, true, true, true};
		
		for(int i=0; i<4; i++){
			assertEquals(tfc.isSpaeterEnabled(eingabe[i]), lösung[i]);
		}
	}
	
	
}
