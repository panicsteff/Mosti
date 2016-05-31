package terminplanungTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logik.terminplanung.TagFrameController;
import logik.terminplanung.Termin;

import org.junit.Test;

public class IstTerminFreiTest {

	@Test
	public void test() {
		
		TagFrameController tfc = new TagFrameController();
		ArrayList<Termin> liste = new ArrayList<Termin>();
		for(int i=0; i<5; i++){
			Termin t = new Termin();
			t.setKundenId(i);
			liste.add(t);
		}
		boolean resultat1 = tfc.istTerminFrei(liste);
		
		liste = new ArrayList<Termin>();
		
		for(int i=0; i<5; i++){
			Termin t = new Termin();
			t.setKundenId(0);
			liste.add(t);
		}
		
		boolean resultat2 = tfc.istTerminFrei(liste);
		
		assertEquals(false, resultat1);
		assertEquals(true, resultat2);
		
	}

}
