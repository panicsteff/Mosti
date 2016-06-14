package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logik.trester.Tresterabrechnung;

public class TresterDB {

	Connection conn;

	public TresterDB() {
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public double tresterpreisLaden() throws SQLException {

		double preis = 0.0;

		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [tresterpreis]");
		while (rs.next()) {
			preis = rs.getDouble("PreisPro1000L");}
		s.close();

		return preis;
	}

	public void updateTresterpreis(double neupreis) throws SQLException {

			PreparedStatement s = null;
			int i = 1;
			s = conn.prepareStatement("update tresterpreis set preispro1000L = ? where id = ? ");
			s.setDouble(1, neupreis);
			s.setInt(2, i++);
			s.executeUpdate();
			s.close();

	}
	
	public void trestereinkaufHinzufügen(Tresterabrechnung ta) {

		try {
			PreparedStatement s = null;

			s = conn.prepareStatement("insert into tresterverkäufe (kundenid, tresterliter, tresterpreis, tresterverkaufsdatum) values "
					+ "( '"
					+ ta.getTresterkunde().getKundenID() + "', " + ta.getLiterzahl()
					+ ", " + ta.getPreis() + ", {ts '" +ta.getDate()+ " 00:00:00'} )");

			s.executeUpdate();
			s.close();
			System.out.println("Trestereinkauf von Kunde "+ta.getTresterkunde().getNachname() + " wurde abgespeichert.");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
