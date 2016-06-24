package terminplanungTest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import logik.terminplanung.TerminHinzufügenLogik;

import org.junit.Test;

public class TerminHinzufügenLogikTest {
	
	@Test
	public void nächstenTagBerechnenTest(){
		TerminHinzufügenLogik tl = new TerminHinzufügenLogik();
		
		assertEquals(tl.nächstenTagBerechnen(10l), 24l*60*60*1000+10);
	}
	
	@Test
	public void vorherigenTagBerechnenTest(){
		
		TerminHinzufügenLogik tl = new TerminHinzufügenLogik();
		assertEquals(tl.vorherigenTagBerechnen(1000000000l), 1000000000l-24l*60*60*1000);
	}
	
	@Test
	public void berechneTermindauerTest(){
		
		String[] obstmenge = {"5", "5", "0"};
		boolean[] flaschen = {false, true, false};
		int[] lösung = {25, 85, 0};
		TerminHinzufügenLogik thl = new TerminHinzufügenLogik();
		
		for(int i=0; i<3; i++){
			try {
				assertEquals(thl.berechneTermindauer(obstmenge[i], flaschen[i]), lösung[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void formatierenTest(){
		TerminHinzufügenLogik thl = new TerminHinzufügenLogik();
		long abgerundet = new Date().getTime() - (new Date().getTime()%1000000);
		assertEquals(thl.formatieren("24.06.2016"), abgerundet);
	}
	
}
