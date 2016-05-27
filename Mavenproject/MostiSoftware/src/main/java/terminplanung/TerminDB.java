package terminplanung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class TerminDB {

	private Connection conn;

	ArrayList<Termin> termineLaden(Date datum) {

		ArrayList<Termin> terminliste = new ArrayList<Termin>();

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT  kundenId, AnzahlZeitslot, Datum, Beginn FROM [termine] Where Datum  "
							+ "BETWEEN{ts '"
							+ datum
							+ " 00:00:00'} AND {ts '"
							+ datum + " 23:59:59'} ");

			while (rs.next()) {
				Termin t = new Termin();
				t.setKundenId(rs.getInt("kundenId"));
				t.setAnzahlZeitslots(rs.getInt("AnzahlZeitslot"));
				t.setUhrzeit(rs.getInt("Beginn"));
				t.setDatum(rs.getDate("datum"));
				terminliste.add(t);
			}
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return terminliste;
	}

	void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum,
			int beginn) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;

			s = conn.prepareStatement("Insert into termine (kundenId, AnzahlZeitslot, Datum, Beginn) VALUES (?, ?, ?,?)");
			s.setInt(1, kundenId);
			s.setInt(2, anzahlZeitslot);
			s.setDate(3, datum);
			s.setInt(4, beginn);

			s.executeUpdate();

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
			ResultSet rs = s
					.executeQuery("SELECT vorname, nachname FROM [kunden] Where id = "
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
			ResultSet rs = s
					.executeQuery("SELECT * FROM [Adminwerte] where id = 1");

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

	ArrayList<Integer> kundenIdLaden(String name) {

		ArrayList<Integer> kundenId = new ArrayList<Integer>();

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [kunden] where nachname like '"
							+ name + "%'");

			while (rs.next()) {
				Integer i = rs.getInt("ID");
				kundenId.add(i);
			}

			s.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return kundenId;
	}

}