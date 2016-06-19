package verkaufsverwaltungTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import logik.verkaufsverwaltung.VerkaufspositionPlus;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

import org.junit.Test;

public class VerkäufeBestimmterTagTest {

	@Test
	public void verkäufeVonBestimmtemTagTest() throws ParseException {
		Verkaufsverwaltung vv = new Verkaufsverwaltung();
		java.util.Date utilDate = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("26.05.2016");
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		ArrayList<VerkaufspositionPlus> result = vv.ladeTagesVerkäufe2(date);
		
		for(VerkaufspositionPlus v : result){
			assertEquals(date, v.getDate());
		}
	}
	

}
