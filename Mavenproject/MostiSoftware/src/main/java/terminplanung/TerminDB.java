package terminplanung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	ArrayList<Termin> termineLaden(Date datum, int anzeigeseite) {

		int obergrenze = anzeigeseite * anzahlProSeite;
		int untergrenze = (anzeigeseite - 1)*anzahlProSeite + 1;
		ArrayList<Termin> terminliste = new ArrayList<Termin>();
		calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [termine] Where id between "
							+ untergrenze + " and " + obergrenze);

			
			while (rs.next()) {
				Termin t = new Termin();
				t.setKundenId(rs.getInt("Tag" + laufenderTag));
				t.setTerminId(rs.getInt("ID"));
				terminliste.add(t);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return terminliste;
	}
	
	void termineSpeichern(ArrayList<Termin> terminliste, Date datum){
		
		calendar = new GregorianCalendar();
		calendar.setTime(datum);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR); 
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s=null;
			
			for(Termin t: terminliste){
				s = conn.prepareStatement("Update termine set Tag" + laufenderTag + " = ? where id = ?");
				s.setInt(1, t.getKundenId());
				s.setInt(2, t.getTerminId());
				
				s.executeUpdate();
				
			}

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	String kundenNamenLaden(int kundenId) {

		String name = new String();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT vorname, nachname FROM [kunden] Where id = "
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
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
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
	
	ArrayList<Termin> freieTermineSuchen(Date d){
		
		ArrayList<Termin> freierTermin = new ArrayList<Termin>();
		calendar = new GregorianCalendar();
		calendar.setTime(d);
		int laufenderTag = calendar.get(Calendar.DAY_OF_YEAR);
		int obergrenze = (arbeitsende - arbeitsbeginn) / zeitslot;
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT Tag"+ laufenderTag + ", ID FROM [termine] where id between 1 and " + obergrenze);

			while (rs.next()) {
				Termin t = new Termin();
				t.setKundenId(rs.getInt("Tag"+ laufenderTag));
				t.setTerminId(rs.getInt("ID"));
				if(rs.getInt("Tag" + laufenderTag) == 0){
					freierTermin.add(t);
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
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
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
	
	int kundenIdLaden(String name){
		
		int kundenId = 0;
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [kunden] where nachname like '" + name + "%'");

			while(rs.next()){
				kundenId= rs.getInt("ID");
			}
			
			s.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return kundenId;
	}
	
	
}