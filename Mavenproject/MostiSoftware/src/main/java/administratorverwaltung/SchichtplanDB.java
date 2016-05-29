package administratorverwaltung;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SchichtplanDB {

	private Connection conn;
	
	ArrayList<Schicht> schichtLaden(Date datum){
		
		ArrayList<Schicht> liste = new ArrayList<Schicht>();
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement st = conn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM [schichtplan] Where datum between {ts '"
							+ datum
							+ " 00:00:00'} AND {ts '"
							+ datum + " 23:59:59'} ");
			
			while (rs.next()) {
				Schicht s = new Schicht();
				s.setDatum(datum);
				s.addMitarbeiterId(rs.getInt("Mitarbeiter"));
				s.setSchichtId(rs.getShort("ID"));
				s.setUhrzeit(rs.getInt("Uhrzeit"));
				liste.add(s);
				
			}
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return liste;
	}
	
	void schichtSpeichern(Date datum, int mitarbeiterId, int uhrzeit) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;

			s = conn.prepareStatement("Insert into schichtplan (datum, uhrzeit, mitarbeiter) values (?,?,?) ");
			s.setDate(1, datum);
			s.setInt(2, uhrzeit);
			s.setInt(3, mitarbeiterId);

			s.executeUpdate();

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	String mitarbeiterNamenLaden(int mitarbeiterId) {

		String name = new String();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT vorname, nachname FROM [mitarbeiter] Where id = "
					+ mitarbeiterId);

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
				Integer i = rs.getInt("MitarbeiterProSchicht");
				adminwerte.add(i);
				i = rs.getInt("SchichtenProTag");
				adminwerte.add(i);
				i = rs.getInt("Arbeitsbeginn");
				adminwerte.add(i);
				i = rs.getInt("Arbeitsende");
				adminwerte.add(i);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);

		}

		return adminwerte;
	}
	
	
	

	ArrayList<Integer> mitarbeiterIdLaden(String name){
		
		ArrayList<Integer> mitarbeiterId = new ArrayList<Integer>();
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [mitarbeiter] where nachname like '" + name + "%'");

			while(rs.next()){
				Integer i = rs.getInt("ID");
				mitarbeiterId.add(i);
			}
			
			s.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return mitarbeiterId;
	}
	
	
}