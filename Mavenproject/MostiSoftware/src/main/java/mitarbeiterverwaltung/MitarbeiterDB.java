package mitarbeiterverwaltung;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MitarbeiterDB {

	Connection conn;
	ArrayList<Mitarbeiter> mitarbeiterliste;

	ArrayList<Mitarbeiter> mitarbeiterLaden() throws FileNotFoundException {

		mitarbeiterliste = new ArrayList<Mitarbeiter>();

		try {

			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * From [mitarbeiter] ");

			while (rs.next()) {
				Mitarbeiter m = new Mitarbeiter();
				m.setNachname(rs.getString("Nachname"));
				m.setVorname(rs.getString("Vorname"));
				m.setStrasse(rs.getString("Strasse"));
				m.setHausnummer(rs.getString("Hausnummer"));
				m.setPlz(rs.getString("PLZ"));
				m.setStadt(rs.getString("Stadt"));
				m.setMitarbeiterID(rs.getInt("ID"));
				m.setTelefonnummer(rs.getString("Telefonnummer"));
				mitarbeiterliste.add(m);
				
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return mitarbeiterliste;
	}

	void mitarbeiterSpeichern(ArrayList<Mitarbeiter> mitarbeiterliste) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;

			for (Mitarbeiter m : mitarbeiterliste) {

				s = conn.prepareStatement("update mitarbeiter set nachname = ?, vorname = ?, "
						+ "strasse = ?,  hausnummer = ?, plz = ?, Stadt = ?, "
						+ "telefonnummer = ? where id = ? ");
				s.setString(1, m.getNachname());
				s.setString(2, m.getVorname());
				s.setString(3, m.getStrasse());
				s.setString(4, m.getHausnummer());
				s.setString(5, m.getPlz());
				s.setString(6, m.getStadt());
				s.setString(7, m.getTelefonnummer());
				s.setInt(8, m.getMitarbeiterID());

				s.executeUpdate();
			}

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	void mitarbeiterEinfügen(Mitarbeiter mitarbeiter) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = conn.prepareStatement("INSERT into mitarbeiter (Nachname, Vorname, Strasse, hausnummer, plz, Stadt, telefonnummer, benutzername) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			s.setString(1, mitarbeiter.getNachname());
			s.setString(2, mitarbeiter.getVorname());
			s.setString(3, mitarbeiter.getStrasse());
			s.setString(4, mitarbeiter.getHausnummer());
			s.setString(5, mitarbeiter.getPlz());
			s.setString(6, mitarbeiter.getStadt());
			s.setString(7, mitarbeiter.getTelefonnummer());
			s.setString(8, mitarbeiter.getBenutzername());

			
			s.executeUpdate();
			s.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void mitarbeiterLöschen(int mitarbeiterId){
		
		try{
			conn = DriverManager.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = conn.prepareStatement("Delete from mitarbeiter where Id = ?");
			
			s.setInt(1, mitarbeiterId);
			s.executeUpdate();
			s.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
