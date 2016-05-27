
package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import logik.produktverwaltung.Produkt;

public class LagerDB {

	Connection conn;

	public ArrayList<Produkt> produkteLaden() {

		ArrayList<Produkt> pliste = new ArrayList<Produkt>();

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [produkte]");

			while (rs.next()) {
				String name = rs.getString("produktname");
				Double preis = rs.getDouble("preis");
				int menge = rs.getInt("vorratsmenge");
				int untergrenze = rs.getInt("untergrenze");
				boolean isA = rs.getBoolean("istAbfuellmaterial");

				Produkt p = new Produkt(name, preis, menge, untergrenze, isA, 0);

				pliste.add(p);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return pliste;
	}

	public void produkteUpdaten(ArrayList<Produkt> pliste) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;
			int i = 1;

			for (Produkt p : pliste) {
				s = conn.prepareStatement("update produkte set produktname = ?, preis= ?, vorratsmenge = ?, untergrenze = ?, istAbfuellmaterial = ? where id = ? ");
				s.setString(1, p.getName());
				s.setDouble(2, p.getPreis());
				s.setInt(3, p.getVorratsmenge());
				s.setInt(4, p.getUntergrenze());
				s.setBoolean(5, p.isAbfüllmaterial());
				s.setInt(6, i++);

				s.executeUpdate();
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void produktLöschen(Produkt p) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;
			
			s = conn.prepareStatement("delete from produkte where produktname = '" + p.getName() +"' ");	
			
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void produktHinzufügen(Produkt p) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
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

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}