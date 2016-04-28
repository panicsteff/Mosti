package terminplanung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class TerminDB {

	public ArrayList<Termin> termineLaden(Date datum) {

		ArrayList<Termin> terminliste = new ArrayList<Termin>();

		try {
			Connection conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Studium/SoSe 2016/Software-praktikum/Glump und zeig/Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [termine] where datum = #04/13/2016# ");
			

			/////////datum = #04/13/2016#
			
			while (rs.next()) {
				Termin termin = new Termin();
				termin.setDatum(rs.getDate("Datum"));
				termin.setKunde(rs.getInt("KundeID"));
				termin.setUhrzeit(rs.getDate("Uhrzeit"));
				
				terminliste.add(termin);
				System.out.println(rs.getString(4));
			}

			s.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return terminliste;
	}

}
