package verkaufsverwaltungTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import logik.verkaufsverwaltung.VerkaufspositionPlus;
import logik.verkaufsverwaltung.Verkaufsverwaltung;

import org.junit.Test;

public class VerkäufeBestimmterZeitraumTest {

	@Test
	public void verkäufeBestimmterZeitraumTest() throws ParseException {
		Verkaufsverwaltung vv = new Verkaufsverwaltung();
		java.util.Date utilDate1 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("01.05.2016");
		java.sql.Date date1 = new java.sql.Date(utilDate1.getTime());
		java.util.Date utilDate2 = logik.kundenverwaltung.Formats.DATE_FORMAT.parse("18.06.2016");
		java.sql.Date date2 = new java.sql.Date(utilDate2.getTime());
		
		ArrayList<VerkaufspositionPlus> result = vv.ladeAlleVerkäufeZeitraum2(date1, date2);
		
		for(VerkaufspositionPlus v : result){
			assertTrue(date1.getTime() <= v.getDate().getTime());
			assertTrue(v.getDate().getTime() <= date2.getTime());
		}
	}

}
