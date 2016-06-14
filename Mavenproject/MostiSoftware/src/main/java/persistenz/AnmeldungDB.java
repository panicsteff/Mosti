package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logik.mitarbeiterverwaltung.Mitarbeiter;

public class AnmeldungDB {
	private Connection conn;
	
	public AnmeldungDB(){
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public Mitarbeiter mitarbeiterLaden(String benutzernameEingabe) {
     Mitarbeiter mitarbeiter = new Mitarbeiter();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [mitarbeiter] Where Benutzername = \""
							+ benutzernameEingabe + '\"');

			

			while (rs.next()) {
				mitarbeiter.setBenutzername(rs.getString("Benutzername"));
				mitarbeiter.setMitarbeiterID(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return mitarbeiter;

	}
	
	public String passwortLaden(String benutzername){
		String passwort = "";
	
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [mitarbeiter] Where Benutzername = \""
							+ benutzername + '\"');
			while (rs.next()) {
				passwort = rs.getString("Passwort");
			
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return passwort;
	}
	
	public String passwortLaden(int id){
		String passwort = "";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [mitarbeiter] Where id = " + id);
			while (rs.next()) {
				passwort = rs.getString("Passwort");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return passwort;
	}
	
	public void updatePasswort(String passwort, int id){
		
		PreparedStatement s = null;
		try {
			s = conn.prepareStatement("update mitarbeiter set passwort = ? where id = ? ");
			s.setString(1, passwort);
			s.setInt(2, id);
			
			s.executeUpdate();
			s.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



