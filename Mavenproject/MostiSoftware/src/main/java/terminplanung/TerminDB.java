package terminplanung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TerminDB {

	private Connection conn;
	private Calendar calendar;
	private int zeitslot;
	private int arbeitsbeginn;
	private int arbeitsende;
	private int aufteilung;
	private int anzahlProSeite;

	public TerminDB(){
		init();
	}
	
	ArrayList<Integer> termineLaden(Date datum, int anzeigeseite) {

		int obergrenze = anzeigeseite * anzahlProSeite;
		int untergrenze = (anzeigeseite - 1)*anzahlProSeite + 1;
		ArrayList<Integer> terminliste = new ArrayList<Integer>();
		calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [termine] Where id between "
							+ untergrenze + " and " + obergrenze);

			
			while (rs.next()) {
				int kundeID = rs.getInt("Tag" + laufenderTag);
				terminliste.add(kundeID);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return terminliste;
	}

	String kundenNamenLaden(int kundenId) {

		String name = new String();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [kunden] Where id = "
					+ kundenId);

			while (rs.next()) {
				name = rs.getString("Nachname") + ", "
						+ rs.getString("Vorname");
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return name;

	}

	ArrayList<Integer> adminwerteLaden() {

		ArrayList<Integer> adminwerte = new ArrayList<Integer>();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [Adminwerte] where id = 1");

			while (rs.next()) {
				Integer i = rs.getInt("Zeitslotlänge");
				adminwerte.add(i);
				i = rs.getInt("Arbeitsbeginn");
				adminwerte.add(i);
				i = rs.getInt("Arbeitsende");
				adminwerte.add(i);
				i = rs.getInt("AnzeigeAufteilung");
				adminwerte.add(i);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);

		}

		return adminwerte;
	}
	
	ArrayList<Integer> freieTermineSuchen(Date d){
		
		ArrayList<Integer> freierTermin = new ArrayList<Integer>();
		calendar = new GregorianCalendar();
		calendar.setTime(d);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR);
		int obergrenze = (arbeitsende - arbeitsbeginn) / zeitslot;
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT Tag"+ laufenderTag + ", ID FROM [termine] where id between 1 and " + obergrenze);

			while (rs.next()) {
				Integer i = rs.getInt("Tag"+ laufenderTag);
				Integer j = rs.getInt("ID");
				if(i == 0){
					freierTermin.add(j);
				}
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);

		}

		return freierTermin;
	}
	
	public void init(){
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [Adminwerte] where id = 1");

			while (rs.next()) {
				Integer i = rs.getInt("Zeitslotlänge");
				zeitslot = i;
				i = rs.getInt("Arbeitsbeginn");
				arbeitsbeginn = i;
				i = rs.getInt("Arbeitsende");
				arbeitsende = i;
				i = rs.getInt("AnzeigeAufteilung");
				aufteilung = i;
				anzahlProSeite = (arbeitsende-arbeitsbeginn)/(zeitslot*aufteilung);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);

		}
	}
	
	
}