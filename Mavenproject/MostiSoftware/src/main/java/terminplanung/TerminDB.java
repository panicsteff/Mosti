package terminplanung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TerminDB {

	private Connection conn;
	
	ArrayList<Integer> termineLaden(int obergrenze, int laufenderTag){
		
		ArrayList<Integer> zahlenliste = new ArrayList<Integer>();
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT Tag" + laufenderTag + " FROM [termine] Where id between 1 and " + obergrenze);

			
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