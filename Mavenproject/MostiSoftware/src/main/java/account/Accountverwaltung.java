package account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Accountverwaltung {

	private Connection conn;
	private String passwort;

	boolean mitarbeiterSuchen(String benutzernameEingabe,
			String passwortEingabe) {

		try {
			conn = DriverManager
					//.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [mitarbeiter] Where Benutzername = \""
							+ benutzernameEingabe + '\"');

			if (rs.getNString(0) == null) {
				JOptionPane.showMessageDialog(null,
						"Der Benutzername ist nicht vorhanden");
				return false;
			}

			while (rs.next()) {
				passwort = rs.getString("Passwort");
			}

			if (passwort.equals(passwortEingabe)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Falsches Passwort");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return false;

	}
}
