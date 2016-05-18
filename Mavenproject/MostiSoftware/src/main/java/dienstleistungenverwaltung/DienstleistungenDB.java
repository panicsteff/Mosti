package dienstleistungenverwaltung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DienstleistungenDB {
		
		Connection conn;
		
		
	public ArrayList<Dienstleistung> dienstleistungenLaden(){
			
			ArrayList<Dienstleistung> dienstleistungenliste = new ArrayList<Dienstleistung>();
			
			try {
				conn = DriverManager
						.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
				Statement s = conn.createStatement();
				ResultSet rs = s
						.executeQuery("SELECT * FROM [dienstleistungen]");

				
				while (rs.next()) {
					String name = rs.getString("dlname");
					Double preis = rs.getDouble("preis pro liter");
					Dienstleistung d = new Dienstleistung(name, preis, 0);
					
					dienstleistungenliste.add(d);
				}
				s.close();

			} catch (Exception e) {
				System.out.println(e);
			}
			
			return dienstleistungenliste;
		}

	public void dlSpeichern (ArrayList<Dienstleistung> dliste){
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s=null;
			
			for(int i=0; i<dliste.size(); i++){
				s = conn.prepareStatement("Update dienstleistungen set dlname = ?, preis pro liter = ? where id = ? ");
				s.setString(1, dliste.get(i).getName());
				s.setDouble(2, dliste.get(i).getPreis());
				s.setInt(3, i);
				
				s.executeUpdate();
				
			}

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
