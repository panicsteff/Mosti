package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logik.produktverwaltung.Produkt;

public class LagerDB {

	Connection conn;

	public LagerDB() {
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Produkt> produkteLaden() {

		ArrayList<Produkt> pliste = new ArrayList<Produkt>();

		try {

			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [produkte]");

			while (rs.next()) {
				String name = rs.getString("produktname");
				Double preis = rs.getDouble("preis");
				int menge = rs.getInt("vorratsmenge");
				int untergrenze = rs.getInt("untergrenze");
				boolean isA = rs.getBoolean("istAbfuellmaterial");

				Produkt p = new Produkt(name, preis, menge, untergrenze, isA, 0);
				p.setId(rs.getInt("id"));

				pliste.add(p);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return pliste;
	}

	public void produktAktualisieren(Produkt p) {

		try {

			PreparedStatement s = null;

			s = conn.prepareStatement("update produkte set produktname = ?, preis= ?, vorratsmenge = ?, untergrenze = ?, istAbfuellmaterial = ? where id = ? ");
			s.setString(1, p.getName());
			s.setDouble(2, p.getPreis());
			s.setInt(3, p.getVorratsmenge());
			s.setInt(4, p.getUntergrenze());
			s.setBoolean(5, p.isAbfüllmaterial());
			s.setInt(6, p.getId());

			s.executeUpdate();

			s.close();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void produktLöschen(Produkt p) {

		try {
			PreparedStatement s = null;
			s = conn.prepareStatement("delete from produkte where produktname = '"
					+ p.getName() + "' ");

			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public int produktHinzufügen(Produkt p) {

		try {
			PreparedStatement s = null;
			s = conn.prepareStatement("insert into produkte (produktname, preis, vorratsmenge, untergrenze, istAbfuellmaterial) values ( '"
					+ p.getName()
					+ "', "
					+ p.getPreis()
					+ ", "
					+ p.getVorratsmenge()
					+ ", "
					+ p.getUntergrenze()
					+ ", "
					+ p.isAbfüllmaterial() + ")");

			s.executeUpdate();
			s.close();

			int id = 0;
			Statement k = conn.createStatement();
			ResultSet rs = k
					.executeQuery("select id from produkte where produktname = '"
							+ p.getName() + "'");
			while (rs.next()) {
				id = rs.getInt("id");
			}

			k.close();
			return id;

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return 0;
		}

	}

	public void verkaufsmengeUpdaten(String name, int neueVerkaufsmenge) {

		try {
			PreparedStatement s = null;

			s = conn.prepareStatement("update produkte set vorratsmenge = ? where produktname = ? ");
			s.setInt(1, neueVerkaufsmenge);
			s.setString(2, name);

			s.executeUpdate();

			s.close();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}
}