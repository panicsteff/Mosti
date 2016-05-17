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
	
	public TerminDB(){
		init();												//braucht man des dann no?
	}
	
	ArrayList<Integer> termineLaden(int obergrenze, int untergrenze, int laufenderTag){
		
		ArrayList<Integer> zahlenliste = new ArrayList<Integer>();
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT Tag" + laufenderTag + " FROM [termine] Where id between "
							+ untergrenze + " and " + obergrenze);

			
			while (rs.next()) {
				Integer i = rs.getInt("Tag" + laufenderTag);
				zahlenliste.add(i);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return zahlenliste;
	}
	
	void termineSpeichern(ArrayList<Integer> terminIdListe, ArrayList<Integer> kundenIdListe, int laufenderTag){
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s=null;
			
			for(int i=0; i<terminIdListe.size(); i++){
				s = conn.prepareStatement("Update termine set Tag" + laufenderTag + " = ? where id = ?");
				s.setInt(1, kundenIdListe.get(i));
				s.setInt(2, terminIdListe.get(i));
				
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
	
	private void init(){
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