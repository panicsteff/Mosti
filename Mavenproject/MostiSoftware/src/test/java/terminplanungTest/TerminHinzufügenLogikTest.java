package terminplanungTest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import logik.terminplanung.TerminHinzuf�genLogik;

import org.junit.Test;

public class TerminHinzuf�genLogikTest {
	
	@Test
	public void n�chstenTagBerechnenTest(){
		TerminHinzuf�genLogik tl = new TerminHinzuf�genLogik();
		
		assertEquals(tl.n�chstenTagBerechnen(10l), 24l*60*60*1000+10);
	}
	
	@Test
	public void vorherigenTagBerechnenTest(){
		
		TerminHinzuf�genLogik tl = new TerminHinzuf�genLogik();
		assertEquals(tl.vorherigenTagBerechnen(1000000000l), 1000000000l-24l*60*60*1000);
	}
	
	@Test
	public void berechneTermindauerTest(){
		
		String[] obstmenge = {"5", "5", "0"};
		boolean[] flaschen = {false, true, false};
		int[] l�sung = {25, 85, 0};
		TerminHinzuf�genLogik thl = new TerminHinzuf�genLogik();
		
		for(int i=0; i<3; i++){
			try {
				assertEquals(thl.berechneTermindauer(obstmenge[i], flaschen[i]), l�sung[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void formatierenTest(){
		TerminHinzuf�genLogik thl = new TerminHinzuf�genLogik();
		long abgerundet = new Date().getTime() - (new Date().getTime()%1000000);
		assertEquals(thl.formatieren("24.06.2016"), abgerundet);
	}
	
}
