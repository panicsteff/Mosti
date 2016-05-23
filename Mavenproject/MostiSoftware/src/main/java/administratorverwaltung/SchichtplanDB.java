package administratorverwaltung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SchichtplanDB {

	private Connection conn;
	
	ArrayList<Integer> schichtLaden(int schichtId, int mitarbeiteranzahl, int schichtenanzahl){
		
		ArrayList<Integer> mitarbeiterid = new ArrayList<Integer>();
		int schichtid_obergrenze = schichtId + schichtenanzahl -1;
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [schichtplan] Where id between " + schichtId + " and " + schichtid_obergrenze);
			
			while (rs.next()) {
				for(int j=1; j<=mitarbeiteranzahl; j++){
					Integer i = rs.getInt("Mitarbeiter" + j);
					mitarbeiterid.add(i);
				}
					
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return mitarbeiterid;
	}
	
	void schichtSpeichern(ArrayList<Integer> schichtIdListe, ArrayList<Integer> mitarbeiterIdListe, int laufenderTag){
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s=null;
			
			for(int i=0; i<schichtIdListe.size(); i++){
				s = conn.prepareStatement("Update termine set Tag" + laufenderTag + " = ? where id = ?");
				s.setInt(1, mitarbeiterIdListe.get(i));
				s.setInt(2, schichtIdListe.get(i));
				
				s.executeUpdate();
				
			}

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	String mitarbeiterNamenLaden(int mitarbeiterId) {

		String name = new String();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT vorname, nachname FROM [mitarbeiter] Where id = "
					+ mitarbeiterId);

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
			ResultSet rs = s.executeQuery("SELECT * FROM [Adminwerte] where id = 1");

			while (rs.next()) {
				Integer i = rs.getInt("MitarbeiterProSchicht");
				adminwerte.add(i);
				i = rs.getInt("SchichtenProTag");
				adminwerte.add(i);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);

		}

		return adminwerte;
	}
	
	
	

	int mitarbeiterIdLaden(String name){
		
		int mitarbeiterId = 0;
		
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [mitarbeiter] where nachname like '" + name + "%'");

			while(rs.next()){
				mitarbeiterId= rs.getInt("ID");
			}
			
			s.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return mitarbeiterId;
	}
	
	
}