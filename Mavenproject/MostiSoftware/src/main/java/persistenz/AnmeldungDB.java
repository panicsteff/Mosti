package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnmeldungDB {
	private Connection conn;
	

	public String mitarbeiterSuchen(String benutzernameEingabe) {
     String benutzername = "";
		try {
			conn = DriverManager
					
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [mitarbeiter] Where Benutzername = \""
							+ benutzernameEingabe + '\"');

			

			while (rs.next()) {
				benutzername = rs.getString("Benutzername");
			
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return benutzername;

	}
	
	public String passwortLaden(String benutzername){
		String passwort = "";
	
		try {
			conn = DriverManager
					
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
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
}



