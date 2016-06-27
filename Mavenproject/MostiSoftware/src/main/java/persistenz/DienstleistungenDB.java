package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logik.dienstleistungverwaltung.Dienstleistung;

public class DienstleistungenDB {

	Connection conn;

	public DienstleistungenDB() {
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Dienstleistung> dienstleistungenLaden() {

		ArrayList<Dienstleistung> dienstleistungenliste = new ArrayList<Dienstleistung>();

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [dienstleistungen]");

			while (rs.next()) {
				String name = rs.getString("dlname");
				Double preis = rs.getDouble("preisProLiter");
				Dienstleistung d = new Dienstleistung(name, preis, 0);
				d.setId(rs.getInt("id"));

				dienstleistungenliste.add(d);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return dienstleistungenliste;
	}

	public void dienstleistungAktualisieren(Dienstleistung d) {

		try {
			PreparedStatement s = null;

			s = conn.prepareStatement("update dienstleistungen set dlname = ?, preisproliter= ? where id = ? ");
			s.setString(1, d.getName());
			s.setDouble(2, d.getPreis());
			s.setInt(3, d.getId());

			s.executeUpdate();

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void dienstleistungLöschen(Dienstleistung d) {

		try {
			PreparedStatement s = null;
			s = conn.prepareStatement("delete from dienstleistungen where dlname = '"
					+ d.getName() + "' ");

			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int dienstleistungHinzufügen(Dienstleistung d) {

		try {
			PreparedStatement s = null;

			s = conn.prepareStatement("insert into dienstleistungen (dlname, preisproliter) values ( '"
					+ d.getName() + "', " + d.getPreis() + ")");

			s.executeUpdate();
			s.close();
			
			int id = 0;
			Statement k = conn.createStatement();
			ResultSet rs = k
					.executeQuery("select id from dienstleistungen where dlname = '"
							+ d.getName() + "'");
			while (rs.next()) {
				id = rs.getInt("ID");
			}

			k.close();
			return id;

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

}
