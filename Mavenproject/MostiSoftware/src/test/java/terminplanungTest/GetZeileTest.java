package terminplanungTest;

import static org.junit.Assert.*;
import logik.terminplanung.TagFrameController;

import org.junit.Test;

public class GetZeileTest {

	@Test
	public void test() {
		TagFrameController tfc = new TagFrameController();
		
		int resultat = tfc.getZeile(7, 2);
		assertEquals(47, resultat);
	}

}
