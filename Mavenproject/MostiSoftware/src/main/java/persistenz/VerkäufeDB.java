
package persistenz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import kundenverwaltung.Kunde;
import verkaufsverwaltung.Einkaufsposition;
import dienstleistungenverwaltung.Dienstleistung;

public class VerkäufeDB {
		
		Connection conn;
		
		
	public ArrayList<Einkaufsposition> einkaufLaden(Kunde kunde, Date date){
			
			ArrayList<Einkaufsposition> einkaufsliste = new ArrayList<Einkaufsposition>();
			
			try {
				conn = DriverManager
						.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
				Statement s = conn.createStatement();
				ResultSet rs = s
						.executeQuery("SELECT * FROM [verkäufe] WHERE kundenid = " + kunde.getKundenID()+
								"AND verkaufsdatum = " + date + "");

				
				while (rs.next()) {
					String name = rs.getString("verkaufsposition");
					Double preis = rs.getDouble("einzelpreis");
					int menge = rs.getInt("verkaufsmenge");
					int literzahl = rs.getInt("literzahl");
					
					Einkaufsposition einkaufsposition = new Einkaufsposition(name, preis, menge, literzahl);
					einkaufsliste.add(einkaufsposition);
				}
				s.close();

			} catch (Exception e) {
				System.out.println(e);
			}
			
			return einkaufsliste;
		}
	
	public ArrayList<Einkaufsposition> alleEinkäufeVonKundeLaden(Kunde kunde){
		ArrayList<Einkaufsposition> einkaufsliste = new ArrayList<Einkaufsposition>();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verkäufe] WHERE kundenid = " + kunde.getKundenID()+ "");

			while (rs.next()) {
				String name = rs.getString("verkaufsposition");
				Double preis = rs.getDouble("einzelpreis");
				int menge = rs.getInt("verkaufsmenge");
				int literzahl = rs.getInt("literzahl");
				
				Einkaufsposition einkaufsposition = new Einkaufsposition(name, preis, menge, literzahl);
				einkaufsliste.add(einkaufsposition);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return einkaufsliste;
	}
	
	

	public void dlUpdaten (ArrayList<Dienstleistung> dliste){
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s=null;
			
			for(int i=0; i<dliste.size(); i++){
				s = conn.prepareStatement("Update dienstleistungen set dlname = ?, preisProLiter = ? where id = ? ");
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
	
	public void dienstleistungLöschen(Dienstleistung d) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;
			
			s = conn.prepareStatement("delete from dienstleistungen where dlname = '" + d.getName() +"' ");	
			
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void dienstleistungHinzufügen(Dienstleistung d) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;

			s = conn.prepareStatement("insert into dienstleistungen (dlname, preisproliter) values ( '"
					+ d.getName()
					+ "', "
					+ d.getPreis()
					+ ")");

			s.executeUpdate();

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}


}


