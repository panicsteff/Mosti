package terminplanung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import kundenverwaltung.Kunde;

public class TerminDB {

	private Connection conn;
	Calendar calendar;
	
	
	public ArrayList<Integer> termineLaden(Date datum, int tageszeit) {

		int obergrenze = tageszeit*36;
		int untergrenze = (tageszeit-1)*36 + 1;
		ArrayList<Integer> terminliste = new ArrayList<Integer>();
		calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR) - 244;       //erster September abziehen
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [termine] Where id between " + untergrenze + " and " + obergrenze);

			while (rs.next()) {
				int kundeID = rs.getInt("Tag"+laufenderTag);
				terminliste.add(kundeID);			
			}
			s.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return terminliste;
	}
	
	public String kundenNamenLaden(int kundenId){
		
		String name = new String();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [kunden] Where id = " + kundenId);

			while(rs.next()){
				name = rs.getString("Nachname") + ", " + rs.getString("Vorname");
			}
			s.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return name;
		
	}
	
	

}
