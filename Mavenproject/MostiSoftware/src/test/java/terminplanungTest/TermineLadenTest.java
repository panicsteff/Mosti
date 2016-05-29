package terminplanungTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import logik.terminplanung.TagFrameController;
import logik.terminplanung.Termin;

import org.junit.Test;

public class TermineLadenTest {

	@Test
	public void termineLadenTest() {
		
		TagFrameController tfc = new TagFrameController();
		ArrayList<Termin> resultat = tfc.termineLaden(new Date(1463608800000l));
		
		assertEquals(204, resultat.get(0).getTerminId());
		assertEquals(205, resultat.get(1).getTerminId());
	}

}
