package lagerverwaltung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LagerDB {
	
	Connection conn;
	
	
	public ArrayList<Produkt> produkteLaden(){
		
		ArrayList<Produkt> pliste = new ArrayList<Produkt>();
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [produkte]");

			
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

public void produkteSpeichern (ArrayList<Produkt> pliste){
	
	try {
		conn = DriverManager
				.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
		PreparedStatement s=null;
		
		for(int i=0; i<pliste.size(); i++){
			s = conn.prepareStatement("Update produkte set produktname = ?, preis= ?, vorratsmenge = ?, untergrenze = ?, istAbfuellmaterial = ? where id = ? ");
			s.setString(1, pliste.get(i).getName());
			s.setDouble(2, pliste.get(i).getPreis());
			s.setInt(3, pliste.get(i).getVorratsmenge());
			s.setInt(4, pliste.get(i).getUntergrenze());
			s.setBoolean(5, pliste.get(i).isAbfüllmaterial());
			s.setInt(6, i);
			
			s.executeUpdate();
			
		}

		s.close();

	} catch (Exception e) {
		System.out.println(e);
	}
}

}
